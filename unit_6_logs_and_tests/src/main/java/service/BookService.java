package service;

import bd.ArrayBD;
import dao.InMemoryBookDao;
import entity.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static controller.Console.printMessage;

public class BookService {

    private static final Logger LOGGER_INFO = LoggerFactory.getLogger("info");
    private static final Logger LOGGER_WARN = LoggerFactory.getLogger("warn");
    private static final int ERROR = 0;

    private final InMemoryBookDao bookDao = new InMemoryBookDao();

    public int create(Book book) {
        LOGGER_INFO.info("create new book: " + book.getTitle());
        if (!checkTitleOfBooK(book.getTitle())) {
            LOGGER_WARN.warn("invalid book's title: " + book.getTitle());
        } else if (!checkGenreOfBook(book.getGenre())) {
            LOGGER_WARN.warn("Indefinite genre: " + book.getGenre());
        } else if (!checkCountOfPage(book.getCountOfPages())) {
            LOGGER_WARN.warn("invalid book's count of page: " + book.getCountOfPages());
        } else {
            bookDao.create(book);
            return book.getId();
        }
        return ERROR;
    }

    public void update(Book book, int id) {
        LOGGER_INFO.info("update book's info: " + book.getTitle());
        if (!checkTitleOfBooK(book.getTitle())) {
            LOGGER_WARN.warn("invalid book's title: " + book.getTitle());
        } else if (!checkGenreOfBook(book.getGenre())) {
            LOGGER_WARN.warn("Indefinite genre: " + book.getGenre());
        } else if (!checkCountOfPage(book.getCountOfPages())) {
            LOGGER_WARN.warn("invalid book's count of page: " + book.getCountOfPages());
        } else bookDao.update(book, id);
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

    public int findIdByTitle(String title) {
        return bookDao.findIdByTitle(title);
    }

    private boolean checkGenreOfBook(String genre) {
        return bookDao.checkGenreOfBook(genre.toUpperCase());
    }

    private boolean checkCountOfPage(int countOfPages) {
        if (countOfPages < 100) {
            printMessage("This is not book, very thin.");
            return false;
        }
        else if (countOfPages > 1000) {
            printMessage("This is not a book, very thick.");
            return false;
        }
        return true;
    }

    private boolean checkTitleOfBooK(String title) {
        if (title.length() < 1 || title.length() > 255) {
            printMessage("Bad book title, too long.");
            return false;
        } else if (!title.matches("^[A-Za-z0-9\\s\\-!_,\\.:;()''\"\"]+$")) {
            printMessage("Invalid book's title");
            return false;
        }
        return true;
    }
}
