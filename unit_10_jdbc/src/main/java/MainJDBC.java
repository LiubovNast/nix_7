import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.JDBCService;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MainJDBC {

    private static final Logger log = LoggerFactory.getLogger(MainJDBC.class);

    public static void main(String[] args) {
        JDBCService jdbcService = new JDBCService();
        Properties props = loadProperties();
        String url = props.getProperty("url");
        log.info("Connecting to {}", url);

        try (Connection connection = DriverManager.getConnection(url, props)) {
            connection.setAutoCommit(false);
            jdbcService.findTheShortestWayInDB(connection);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static Properties loadProperties() {

        Properties props = new Properties();

        try (InputStream input = MainJDBC.class.getResourceAsStream("/jdbc.properties")) {
            props.load(input);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
        return props;
    }
}
