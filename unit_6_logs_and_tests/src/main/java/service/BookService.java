package service;

import bd.ArrayBD;
import dao.InMemoryBookDao;
import entity.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BookService {

    private static final Logger LOGGER_INFO = LoggerFactory.getLogger("info");
    private static final Logger LOGGER_WARN = LoggerFactory.getLogger("warn");

    private final InMemoryBookDao bookDao = new InMemoryBookDao();

    public void create(Book book) {
        LOGGER_INFO.info("create new book: " + book.getTitle());
        bookDao.create(book);
    }

    public void update(Book book) {
        bookDao.update(book);
    }

    public void delete(int id) {
        LOGGER_WARN.warn("remove book by id: " + id);
        bookDao.delete(id);
    }

    public Book findBookById(int id) {
        return bookDao.findBookById(id);
    }

    public Book[] findAllBooks() {
        return bookDao.findAllBooks();
    }

    public void updateArrayOfIdAuthors(int id, Book book) {
        int[] array = book.getIdAuthors();
        array = ArrayBD.getInstance().changeSizeOfArray(array);
        array[ArrayBD.getInstance().getNextIndexOfArray(array)] = id;
        book.setIdAuthors(array);
    }
}
