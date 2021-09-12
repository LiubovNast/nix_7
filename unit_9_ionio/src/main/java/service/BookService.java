package service;

import dao.BookDao;
import dao.FileBookDao;
import entity.Book;

import static controller.Console.printMessage;

public class BookService {

    private final BookDao bookDao = new FileBookDao();

    public void create(Book book) {
        if (!checkTitleOfBooK(book.getTitle())) {
        } else if (!checkCountOfPage(book.getCountOfPages())) {
        } else {
            bookDao.create(book);
        }
    }

    public void update(Book book, int id) {
        if (!checkTitleOfBooK(book.getTitle())) {
        } else if (!checkCountOfPage(book.getCountOfPages())) {
        } else bookDao.update(book, id);
    }

    public void delete(int id) {
        bookDao.delete(id);
    }

    public Book findBookById(int id) {
        return bookDao.findBookById(id);
    }

    public Book[] findAllBooks() {
        return bookDao.findAllBooks();
    }

    public void updateArrayOfIdAuthors(int id, Book book) {
//        int[] array = book.getIdAuthors();
//        array = ArrayBD.getInstance().changeSizeOfArray(array);
//        array[ArrayBD.getInstance().getNextIndexOfArray(array)] = id;
//        book.setIdAuthors(array);
    }

    public int findIdByTitle(String title) {
        return bookDao.findIdByTitle(title);
    }

    private boolean checkCountOfPage(int countOfPages) {
        if (countOfPages < 100) {
            printMessage("This is not book, very thin.");
            return false;
        } else if (countOfPages > 1000) {
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
