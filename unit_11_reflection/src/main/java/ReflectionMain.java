import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.InitClass;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.util.Properties;

public class ReflectionMain {

    private static final Logger LOG = LoggerFactory.getLogger(ReflectionMain.class);

    public static void main(String[] args) {
        LOG.info("Start application");
        InitClass init = new InitClass();
        Properties props = new Properties();

        try (InputStream input = new FileInputStream("app.properties")) {
            props.load(input);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
        AppProperties app = init.initialisation(AppProperties.class, props);
        LOG.info("Create object {}", app);
    }
}
