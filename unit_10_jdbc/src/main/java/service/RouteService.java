package service;

import dao.RouteDao;
import entity.Route;

import java.sql.Connection;
import java.util.List;
import java.util.function.Supplier;

public class RouteService {

    private final Connection connection;

    public RouteService(Supplier<Connection> connection) {
        this.connection = connection.get();
    }

    public List<Route> findAll() {
        RouteDao routeDao = new RouteDao(() -> connection);
        return routeDao.findAll();
    }
}
