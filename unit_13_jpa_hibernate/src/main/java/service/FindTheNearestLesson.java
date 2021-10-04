package service;

import entity.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.function.Supplier;

public class FindTheNearestLesson {

    private final Supplier<EntityManager> persistence;

    private static final Logger LOG = LoggerFactory.getLogger(FindTheNearestLesson.class);

    public FindTheNearestLesson(Supplier<EntityManager> persistence) {
        this.persistence = persistence;
    }

    public void nextLessonForStudent(long phoneNumber) {
        EntityManager entityManager = persistence.get();
        try {
            entityManager.getTransaction().begin();
            LOG.info("Create transaction");

            String queryString = "from Student where phoneNumber = " + phoneNumber;
            TypedQuery<Student> query = entityManager.createQuery(queryString, Student.class);
            if (query == null) {
                LOG.warn("Student not found");
            } else {
                Student student = query.getSingleResult();
                LOG.info("Student {}", student.getFirstName());
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            LOG.error("Transaction rollback");
            entityManager.getTransaction().rollback();
        }
    }
}
