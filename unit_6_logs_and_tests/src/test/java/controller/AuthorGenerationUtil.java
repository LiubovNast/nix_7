package controller;

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

    public static Author generateAuthor(String name) {
        Author author = new Author();
        author.setFullName(name);
        author.setIdBooks(ID_BOOKS);
        author.setHasOneBook(hasOneBook);
        return author;
    }

    public static Author generateAuthor(String name, int[] idBooks) {
        Author author = new Author();
        author.setFullName(name);
        author.setIdBooks(idBooks);
        author.setHasOneBook(hasOneBook);
        return author;
    }
}
