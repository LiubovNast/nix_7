package service;

import dao.SolutionDao;
import entity.Solution;

import java.sql.Connection;
import java.util.List;
import java.util.function.Supplier;

public class SolutionService {

    private final Connection connection;
    private final SolutionDao solutionDao;

    public SolutionService(Supplier<Connection> connection) {
        this.connection = connection.get();
        this.solutionDao = new SolutionDao(() -> this.connection);
    }

    public List<Solution> findAll() {
        return solutionDao.findAll();
    }

    public void create(List<Solution> solutions) {
        solutionDao.create(solutions);
    }
}
