package dao;

import entity.Solution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class SolutionDao {

    private static final Logger log = LoggerFactory.getLogger(SolutionDao.class);
    private final Connection connection;
    private static final List<Solution> solutions = new ArrayList<>();

    public SolutionDao(Supplier<Connection> connection) {
        this.connection = connection.get();
    }

    public List<Solution> findAll() {
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM solutions")) {
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Solution solution = new Solution();
                solution.setProblemId(resultSet.getInt(1));
                solution.setCost(resultSet.getInt("cost"));
                solutions.add(solution);
            }
        } catch (SQLException e) {
            log.error("Cannot create statement");
        }
        return solutions;
    }

    public void create(List<Solution> solutions) {
        try (PreparedStatement insert = connection.prepareStatement(
                "INSERT INTO solutions (problem_id, cost) VALUES (?, ?) ON CONFLICT DO NOTHING"
        )) {

            for (Solution solution : solutions) {
                insert.setInt(1, solution.getProblemId());
                insert.setInt(2, solution.getCost());

                insert.addBatch();
            }
            insert.executeBatch();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }
    }
}
