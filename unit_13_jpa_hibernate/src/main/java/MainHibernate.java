import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.FindTheNearestLesson;

public class MainHibernate {

    private static final Logger LOG = LoggerFactory.getLogger(MainHibernate.class);

    public static void main(String[] args) {
        Configuration configuration = new Configuration().configure();

        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            LOG.info("Create session");
            FindTheNearestLesson theNearestLesson = new FindTheNearestLesson(() -> session);
            theNearestLesson.nextLessonForStudent(1245);
        }
    }
}
