package dao;

import entity.Route;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class RouteDao {

    private static final Logger log = LoggerFactory.getLogger(RouteDao.class);
    private final Connection connection;
    private static final List<Route> routes = new ArrayList<>();

    public RouteDao(Supplier<Connection> connection) {
        this.connection = connection.get();
    }

    public List<Route> findAll() {
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM routes")) {
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Route route = new Route();
                route.setId(resultSet.getInt(1));
                route.setFromId(resultSet.getInt(2));
                route.setToId(resultSet.getInt(3));
                route.setCost(resultSet.getInt("cost"));
                routes.add(route);
            }
        } catch (SQLException e) {
            log.error("Cannot create statement");
        }
        return routes;
    }
}
