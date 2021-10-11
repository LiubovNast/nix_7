package service;

import au.com.bytecode.opencsv.CSVWriter;
import exception.IllegalInputException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.regex.Pattern;

import static console.Console.getString;
import static console.Console.printMessage;

public class OperationView {

    private final Connection connection;
    private static final Logger LOG = LoggerFactory.getLogger(OperationView.class);
    private static final DateTimeFormatter format = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
    private static final Pattern pattern = Pattern.compile("\\d{0,4}-\\d{2}-\\d{2}");

    public OperationView(Supplier<Connection> connection) {
        this.connection = connection.get();
    }

    public void view(String phone) {

        int userId = getUserId(phone);
        List<Integer> accounts = getAccountsList(userId);
        List<String[]> csv = new ArrayList<>();
        String[] header = new String[5];
        header[0] = "id";
        header[1] = "date";
        header[2] = "amount";
        header[3] = "account";
        header[4] = "category";
        csv.add(header);

        printMessage("Please enter period from to (with space in current date format \"yyyy-MM-dd\"):");
        String dateBetween = getString();
        String[] dates = dateBetween.split(" ");
        checkDate(dates);

        Instant from = LocalDate.parse(dates[0].trim() + "T00:00:00", format).atStartOfDay().toInstant(ZoneOffset.UTC);
        Instant to = LocalDate.parse(dates[1].trim() + "T00:00:00", format).atStartOfDay().toInstant(ZoneOffset.UTC);

        accounts.forEach(a -> csv.addAll(getOperationList(a, from, to)));

        String fileName = "period_between_" + dateBetween + ".csv";
        String path = "./files_csv";

        try {
            if (Files.notExists(Path.of(path))) {
                Files.createDirectories(Path.of(path));
            }
            String allPath = path + "\\" + fileName;
            if (Files.notExists(Path.of(allPath))) {
                Files.createFile(Path.of(allPath));
            }
        } catch (IOException e) {
            LOG.error("Cannot find or create file {}", fileName);
            throw new RuntimeException(e.getCause());
        }

        try (CSVWriter writer = new CSVWriter(new FileWriter(path + "\\" + fileName))) {
            writer.writeAll(csv);
        } catch (IOException e) {
            LOG.error("Cannot write to file {}", fileName);
            throw new RuntimeException(e.getCause());
        }
    }

    private void checkDate(String[] dates) {
        try {
            if (dates.length != 2) {
                LOG.error("Input wrong date format {}", dates);
                throw new IllegalInputException("Wrong date input");
            }
            for (String date : dates) {
                if (!date.matches(pattern.pattern())) {
                    LOG.error("Input wrong date format {}", date);
                    throw new IllegalInputException("Wrong date input");
                }
            }
        } catch (IllegalInputException e) {
            throw new RuntimeException(e.getCause());
        }

    }

    private List<Integer> getAccountsList(int userId) {

        try (PreparedStatement ps = connection.prepareStatement("SELECT id FROM accounts WHERE user_id = ?")) {
            ps.setLong(1, userId);
            ResultSet resultSet = ps.executeQuery();
            List<Integer> list = new ArrayList<>();
            while (resultSet.next()) {
                list.add(resultSet.getInt("id"));
            }
            return list;
        } catch (SQLException e) {
            LOG.error("Cannot create statement");
            throw new RuntimeException(e.getCause());
        }
    }

    private List<String[]> getOperationList(int accountID, Instant from, Instant to) {
        try (PreparedStatement ps = connection.prepareStatement("SELECT " +
                "operations.id, operations.date, operations.amount," +
                " accounts.name as account, categories.name as category " +
                "FROM (operations JOIN accounts ON accounts.id = operations.account_id) " +
                "JOIN categories ON categories.id = operations.category_id " +
                "WHERE operations.account_id = ? AND (operations.date > ?) AND (operations.date < ?)")) {
            ps.setLong(1, accountID);
            ps.setTimestamp(2, Timestamp.from(from));
            ps.setTimestamp(3, Timestamp.from(to));
            ResultSet resultSet = ps.executeQuery();
            List<String[]> operations = new ArrayList<>();
            while (resultSet.next()) {
                String[] operation = new String[5];
                operation[0] = String.valueOf(resultSet.getInt(1));
                Timestamp time = resultSet.getObject("date", Timestamp.class);
                operation[1] = String.valueOf(time);
                operation[2] = String.valueOf(resultSet.getInt("amount"));
                operation[3] = resultSet.getString("account");
                operation[4] = resultSet.getString("category");
                operations.add(operation);
            }
            return operations;
        } catch (SQLException e) {
            LOG.error("Cannot create statement");
            throw new RuntimeException(e.getCause());
        }
    }

    private int getUserId(String phone) {

        try (PreparedStatement getUserWithPhoneNumber = connection.prepareStatement(
                "SELECT id FROM users WHERE phone LIKE ?")) {

            getUserWithPhoneNumber.setString(1, phone);
            ResultSet resultSet = getUserWithPhoneNumber.executeQuery();
            LOG.info("Find user with phone number {}", phone);
            resultSet.next();
            return resultSet.getInt(1);
        } catch (SQLException e) {
            LOG.error("Cannot create statement");
            throw new RuntimeException(e.getCause());
        }
    }
}
