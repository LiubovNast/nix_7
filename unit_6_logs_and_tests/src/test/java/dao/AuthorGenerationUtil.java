package dao;

import entity.Author;

public class AuthorGenerationUtil {

    public static boolean hasOneBook = true;
    public static final int[] ID_BOOKS = new int[1];
    public static final String FULL_NAME = "test";

    public static Author generateAuthor() {
        Author author = new Author();
        author.setFullName(FULL_NAME);
        author.setIdBooks(ID_BOOKS);
        author.setHasOneBook(hasOneBook);
        return author;
    }

    public static Author generateAuthor(String name, int[] books, boolean hasOneBook) {
        Author author = new Author();
        author.setFullName(name);
        author.setIdBooks(books);
        author.setHasOneBook(hasOneBook);
        return author;
    }
    public static Author generateAuthor(String name, int[] books) {
        Author author = new Author();
        author.setFullName(name);
        author.setIdBooks(books);
        author.setHasOneBook(books.length == 1);
        return author;
    }

    public static Author generateAuthor(String name) {
        Author author = new Author();
        author.setFullName(name);
        author.setIdBooks(ID_BOOKS);
        author.setHasOneBook(hasOneBook);
        return author;
    }
}
