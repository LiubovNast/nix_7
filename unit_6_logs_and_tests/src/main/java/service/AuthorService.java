package service;

import bd.ArrayBD;
import dao.InMemoryAuthorDao;
import entity.Author;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AuthorService {

    private static final Logger LOGGER_INFO = LoggerFactory.getLogger("info");
    private static final Logger LOGGER_WARN = LoggerFactory.getLogger("warn");

    private final InMemoryAuthorDao authorDao = new InMemoryAuthorDao();

    public int create(Author author) {
        Author[] authors = findAllAuthors();
        for (int i = 0; i < authors.length; i++) {
            if (authors[i] != null) {
                if (authors[i].getFullName().equals(author.getFullName())) {
                    LOGGER_INFO.info("add new book to author.IdBooks: " + author.getFullName());
                    updateArrayOfIdBooks(author.getIdBooks()[0], authors[i]);
                    authors[i].setHasOneBook(false);
                    return authors[i].getId();
                }
            }
        }
        LOGGER_INFO.info("create new author: " + author.getFullName());
        authorDao.create(author);
        return author.getId();
    }

    private void updateArrayOfIdBooks(int idBook, Author authorInBD) {
        int[] array = authorInBD.getIdBooks();
        array = ArrayBD.getInstance().changeSizeOfArray(array);
        array[ArrayBD.getInstance().getNextIndexOfArray(array)] = idBook;
        authorInBD.setIdBooks(array);
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
