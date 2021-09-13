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
        JsonWriter.write(books, FILE_NAME);
    }

    @Override
    public void update(Book book, int id) {
        books = JsonReader.read(FILE_NAME, new Book());
        if (books != null) {
            if (books.stream().anyMatch(b -> b.getId() == id)) {
                Book inDbBook = books.stream().filter(b -> b.getId() == id).findFirst().get();
                int index = books.indexOf(inDbBook);
                books.set(index, book);
                JsonWriter.write(books, FILE_NAME);
            }
        }
    }

    @Override
    public void delete(int id) {
        books = JsonReader.read(FILE_NAME, new Book());
        if (books != null) {
            books.removeIf(book -> book.getId() == id);
            JsonWriter.write(books, FILE_NAME);
        }
    }

    @Override
    public Book findBookById(int id) {
        books = JsonReader.read(FILE_NAME, new Book());
        if (books != null) {
            if (books.stream().anyMatch(book -> book.getId() == id)) {
                return books.stream().filter(book -> book.getId() == id).findFirst().get();
            }
        }
        return null;
    }

    @Override
    public List<Book> findAllBooks() {
        Book book = new Book();
        return JsonReader.read(FILE_NAME, book);
    }

    @Override
    public int findIdByTitle(String title) {
        books = JsonReader.read(FILE_NAME, new Book());
        if (books != null) {
            if (books.stream().anyMatch(b -> b.getTitle().equals(title))) {
                Book book = books.stream().filter(b -> b.getTitle().equals(title)).findFirst().get();
                return book.getId();
            }
        }
        return 0;
    }

    @Override
    public void updateArrayOfIdAuthors(int id, Book book) {
        int[] array = book.getIdAuthors();
        int index = getNextIndexOfArray(array);
        if (index == array.length) {
            array = changeSizeOfArray(array);
        }
        array[index] = id;
        book.setIdAuthors(array);
        update(book, book.getId());
    }

    private int[] changeSizeOfArray(int[] array) {
        int size = (int) (array.length * 1.5 + 1);
        int[] newArray = new int[size];
        for (int i = 0; i < array.length; i++) {
            newArray[i] = array[i];
        }
        return newArray;
    }

    private int getNextIndexOfArray(int[] array) {
        int index = array.length;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == 0) return i;
        }
        return index;
    }
}
