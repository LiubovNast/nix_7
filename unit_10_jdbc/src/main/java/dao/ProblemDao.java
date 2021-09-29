package dao;

import entity.Problem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class ProblemDao {

    private static final Logger log = LoggerFactory.getLogger(ProblemDao.class);
    private final Connection connection;
    private static final List<Problem> problems = new ArrayList<>();

    public ProblemDao(Supplier<Connection> connection) {
        this.connection = connection.get();
    }

    public List<Problem> findAll() {
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM problems")) {
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Problem problem = new Problem();
                problem.setId(resultSet.getInt(1));
                problem.setFromId(resultSet.getInt(2));
                problem.setToId(resultSet.getInt(3));
                problems.add(problem);
            }
        } catch (SQLException e) {
            log.error("Cannot create statement");
        }
        return problems;
    }
}
