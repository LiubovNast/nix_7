package dao;

import entity.Location;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class LocationDao {

    private static final Logger log = LoggerFactory.getLogger(LocationDao.class);
    private final Connection connection;
    private static final List<Location> locations = new ArrayList<>();

    public LocationDao(Supplier<Connection> connection) {
        this.connection = connection.get();
    }

    public List<Location> findAll() {
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM locations")) {
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Location location = new Location();
                location.setId(resultSet.getInt(1));
                location.setName(resultSet.getString("name"));
                locations.add(location);
                // log.info("Database location id {} and name {} ", location.getId(), location.getName());
            }
        } catch (SQLException e) {
            log.error("Cannot create statement");
        }
        return locations;
    }
}
