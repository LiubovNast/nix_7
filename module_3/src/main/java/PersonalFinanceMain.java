import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.AddingOperation;
import service.OperationView;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import static console.Console.getInt;
import static console.Console.printMessage;

public class PersonalFinanceMain {

    private static final Logger LOG = LoggerFactory.getLogger(PersonalFinanceMain.class);

    public static void main(String[] args) {
        String phone = args[0];
        String userName = args[1];
        String password = args[2];

        printMessage("Please choose what you want to do:\n" +
                "1 - Adding a new operation;\n" +
                "2 - Export an account statement.");
        int input = getInt();

        switch (input) {
            case 1:
                startHibernate(phone, userName, password);
                break;
            case 2:
                startJDBC(phone, userName, password);
                break;
            default:
                printMessage("We don't have this action. You enter incorrect number.");
                break;
        }
    }

    private static void startJDBC(String phone, String userName, String password) {
        Properties props = new Properties();
        try (InputStream input = PersonalFinanceMain.class.getResourceAsStream("/jdbc.properties")) {
            props.load(input);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
        String url = props.getProperty("url");
        LOG.info("Connecting to {}", url);

        try (Connection connection = DriverManager.getConnection(url, userName, password)) {
            connection.setAutoCommit(false);
            var operation = new OperationView(() -> connection);
            operation.view(phone);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void startHibernate(String phone, String userName, String password) {
        Configuration configuration = new Configuration().configure();
        configuration.setProperty("hibernate.connection.username", userName);
        configuration.setProperty("hibernate.connection.password", password);

        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            LOG.info("Create session");

            var operation = new AddingOperation(() -> session);
            operation.add(phone);
        }
    }
}
