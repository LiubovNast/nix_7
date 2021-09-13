package dao;

import entity.Book;

import java.util.List;

public interface BookDao {

    void create(Book book);

    void update(Book book, int id);

    void delete(int id);

    Book findBookById(int id);

    List<Book> findAllBooks();

    int findIdByTitle(String title);

    void updateArrayOfIdAuthors(int id, Book book);
}
