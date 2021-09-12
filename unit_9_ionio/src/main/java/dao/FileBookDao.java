package dao;

import entity.Book;
import util.JsonReader;
import util.JsonWriter;

import java.util.ArrayList;
import java.util.List;

public class FileBookDao implements BookDao {

    private List<Book> books;
    private final String FILE_NAME = "Book.json";

    @Override
    public void create(Book book) {
        books = JsonReader.read(FILE_NAME, book);
        if (books == null) {
            books = new ArrayList<>();
        }
        books.add(book);
        JsonWriter.write(books.toArray(), FILE_NAME);
    }

    @Override
    public void update(Book book, int id) {
        JsonWriter.write(findAllBooks(), FILE_NAME);
    }

    @Override
    public void delete(int id) {
        JsonWriter.write(findAllBooks(), FILE_NAME);
    }

    @Override
    public Book findBookById(int id) {
        return null;
    }

    @Override
    public Book[] findAllBooks() {
        Book book = new Book();
        books = JsonReader.read(FILE_NAME, book);
        if (books == null) return new Book[]{new Book()};
        return books.toArray(Book[]::new);
    }

    @Override
    public int findIdByTitle(String title) {
        return 0;
    }
}
