package dao;

import entity.Book;

import java.util.Random;

public class InMemoryBookDao {

    Book[] books = new Book[5];

    public void create(Book book) {
        book.setId(generateId());
        books[0] = book;
    }

    public void update(Book book) {
        Book inDbBook = findBookById(book.getId());
        inDbBook.setTitle(book.getTitle());
        inDbBook.setGenre(book.getGenre());
        inDbBook.setCountOfPages(book.getCountOfPages());
        inDbBook.setIdAuthors(book.getIdAuthors());
    }

    public void delete(int id) {
        books[id] = null;
    }

    public Book findBookById(int id) {
        for (int i = 0; i < books.length; i++) {
            if (books[i].getId() == id) return books[i];
        }
        return null;
    }

    public Book[] findAllUsers() {
        return books;
    }

    private int generateId() {
        int id = new Random().nextInt();
        for (int i = 0; i < books.length; i++) {
            if (books[i].getId() == id) return generateId();
        }
        return id;
    }
}
