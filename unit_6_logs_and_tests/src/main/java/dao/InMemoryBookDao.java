package dao;

import entity.Book;

import java.util.Random;

public class InMemoryBookDao {

    Book[] books = new Book[2];

    public void create(Book book) {
        books = changeSizeOfArray(books);
        book.setId(generateId());
        books[getNextIndexOfArray(books)] = book;
    }

    public void update(Book book) {
        Book inDbBook = findBookById(book.getId());
        inDbBook.setTitle(book.getTitle());
        inDbBook.setGenre(book.getGenre());
        inDbBook.setCountOfPages(book.getCountOfPages());
        inDbBook.setIdAuthors(book.getIdAuthors());
    }

    public void delete(int id) {
        for (int i = 0; i < books.length; i++) {
            if (books[i].getId() == id) {
                books[i] = null;
                break;
            }
        }
        books = getArrayWithoutNull(books);
    }

    public Book findBookById(int id) {
        for (int i = 0; i < books.length; i++) {
            if (books[i].getId() == id) return books[i];
        }
        return null;
    }

    public Book[] findAllBooks() {
        return getArrayWithoutNull(books);
    }

    private Book[] getArrayWithoutNull(Book[] books) {
        int count = 0;
        for (int i = 0; i < books.length; i++) {
            if (books[i] != null) count++;
        }
        if (count == books.length) return books;
        Book[] temp = new Book[count];
        int j = 0;
        for (int i = 0; i < books.length; i++) {
            if (books[i] != null) {
                temp[j] = books[i];
                j++;
            }
        }
        return temp;
    }

    private int generateId() {
        int id = new Random().nextInt();
        for (int i = 0; i < books.length; i++) {
            if (books[i] == null) continue;
            if (books[i].getId() == id) return generateId();
        }
        return id;
    }

    private Book[] changeSizeOfArray(Book[] array) {
        if (getNextIndexOfArray(array) == array.length) {
            int newSize = (int) (array.length * 1.5);
            Book[] temp = new Book[newSize];
            for (int i = 0; i < array.length; i++) {
                temp[i] = array[i];
            }
            return temp;
        } else return array;
    }

    private int getNextIndexOfArray(Book[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == null) return i;
        }
        return array.length;
    }
}