package dao;

import bd.ArrayBD;
import entity.Book;

public class InMemoryBookDao {

    public void create(Book book) {
        ArrayBD.getInstance().create(book);
    }

    public void update(Book book) {
        ArrayBD.getInstance().update(book);
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
}