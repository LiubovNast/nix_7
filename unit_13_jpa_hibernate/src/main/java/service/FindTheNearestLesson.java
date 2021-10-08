package service;

import entity.Lesson;
import entity.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FindTheNearestLesson {

    private final Supplier<EntityManager> persistence;
    private final static Pattern pattern = Pattern.compile("\\d{10,13}");
    private static final Logger LOG = LoggerFactory.getLogger(FindTheNearestLesson.class);

    public FindTheNearestLesson(Supplier<EntityManager> persistence) {
        this.persistence = persistence;
    }

    public void nextLessonForStudent(String number) {
        Matcher matcher = pattern.matcher(number);
        if (!matcher.matches()) {
            LOG.error("You input incorrect phone number");
            throw new IllegalArgumentException("Input incorrect phone number");
        }
        EntityManager entityManager = persistence.get();
        entityManager.getTransaction().begin();
        try {
            LOG.info("Create transaction");

            TypedQuery<Student> queryStudent = entityManager.createQuery("FROM Student s WHERE s.phoneNumber LIKE :phoneNumber", Student.class)
                    .setParameter("phoneNumber", number);

            Student student = queryStudent.getSingleResult();

            if (student == null) {
                LOG.warn("We don't have Student with phoneNumber {}", number);
            } else {
                String studentName = student.getFirstName() + " " + student.getLastName();

                long indexGroup = student.getGroup().getId();

                TypedQuery<Lesson> queryLesson = entityManager.createQuery("FROM Lesson l WHERE l.group = :indexGroup "+
                        "AND l.startTime > now() ORDER BY l.startTime ASC", Lesson.class)
                        .setParameter("indexGroup", indexGroup).setMaxResults(1);
                List<Lesson> lessons = queryLesson.getResultList();
                if (!lessons.isEmpty()) {
                    Lesson lesson = lessons.get(0);
                    LOG.info("{} has lesson - {} with topic {}, at {} with teacher {} {}",
                            studentName, lesson.getSubject(), lesson.getTopic().getName(), lesson.getStartTime(),
                            lesson.getTeacher().getFirstName(), lesson.getTeacher().getLastName());
                } else {
                    LOG.info("You don't have any lessons");
                }
            }

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            LOG.error("Transaction rollback");
            entityManager.getTransaction().rollback();
        }
    }
}
