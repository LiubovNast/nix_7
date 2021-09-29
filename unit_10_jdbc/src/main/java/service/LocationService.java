package service;

import dao.LocationDao;
import entity.Location;

import java.sql.Connection;
import java.util.List;
import java.util.function.Supplier;

public class LocationService {

    private final Connection connection;

    public LocationService(Supplier<Connection> connection) {
        this.connection = connection.get();
    }

    public List<Location> findAll() {
        LocationDao locationDao = new LocationDao(() -> connection);
        return locationDao.findAll();
    }
}
