package service;

import dao.ProblemDao;
import entity.Problem;

import java.sql.Connection;
import java.util.List;
import java.util.function.Supplier;

public class ProblemService {

    private final Connection connection;

    public ProblemService(Supplier<Connection> connection) {
        this.connection = connection.get();
    }

    public List<Problem> findAll() {
        var problemDao = new ProblemDao(() -> connection);
        return problemDao.findAll();
    }
}
