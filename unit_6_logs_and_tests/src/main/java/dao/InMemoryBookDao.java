package dao;

import bd.ArrayBD;
import entity.Book;

public class InMemoryBookDao {

    public void create(Book book) {
        ArrayBD.getInstance().create(book);
    }

    public void update(Book book, int id) {
        ArrayBD.getInstance().update(book, id);
    }

    public void delete(int id) {
        ArrayBD.getInstance().delete(id, ArrayBD.Entity.BOOK);
    }

    public Book findBookById(int id) {
        return ArrayBD.getInstance().findBookById(id);
    }

    public Book[] findAllBooks() {
        return ArrayBD.getInstance().findAllBooks();
    }

    public int findIdByTitle(String title) {
        return ArrayBD.getInstance().findIdOfBookOrAuthor(title, ArrayBD.Entity.BOOK);
    }

    public boolean checkGenreOfBook(String genre) {
        return ArrayBD.getInstance().checkGenreOfBook(genre);
    }
}