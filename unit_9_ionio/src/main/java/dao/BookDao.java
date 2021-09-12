package dao;

import entity.Book;

public interface BookDao {

    void create(Book book);

    void update(Book book, int id);

    void delete(int id);

    Book findBookById(int id);

    Book[] findAllBooks();

    int findIdByTitle(String title);
}
