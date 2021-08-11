package service;

import dao.InMemoryAuthorDao;
import entity.Author;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AuthorService {

    private static final Logger LOGGER_INFO = LoggerFactory.getLogger("info");
    private static final Logger LOGGER_WARN = LoggerFactory.getLogger("warn");

    private final InMemoryAuthorDao authorDao = new InMemoryAuthorDao();

    public void create(Author author) {
        LOGGER_INFO.info("create new author: " + author.getFullName());
        authorDao.create(author);
    }

    public void update(Author author) {
        authorDao.update(author);
    }

    public void delete(int id) {
        LOGGER_WARN.warn("remove author by id: " + id);
        authorDao.delete(id);
    }

    public Author findAuthorById(int id) {
        return authorDao.findAuthorById(id);
    }

    public Author[] findAllAuthors() {
        return authorDao.findAllAuthors();
    }
}
