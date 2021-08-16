package controller;

import entity.Author;
import entity.Book;

public class BookGenerationUtil {

    public static boolean hasOneAuthor = true;
    public static final int[] ID_Author = new int[1];
    public static final int COUNT_OF_PAGE = 200;
    public static final String TITLE = "Test";
    public static final String GENRE = "DETECTIVE";

    public static Book generateBook() {
        Book book = new Book();
        book.setTitle(TITLE);
        book.setIdAuthors(ID_Author);
        book.setCountOfPages(COUNT_OF_PAGE);
        book.setGenre(GENRE);
        book.setHasOneAuthor(hasOneAuthor);
        return book;
    }

    public static Book generateBook(String title) {
        Book book = new Book();
        book.setTitle(title);
        book.setIdAuthors(ID_Author);
        book.setCountOfPages(COUNT_OF_PAGE);
        book.setGenre(GENRE);
        book.setHasOneAuthor(hasOneAuthor);
        return book;
    }

    public static Book generateBook(String title, int countOfPage) {
        Book book = new Book();
        book.setTitle(title);
        book.setIdAuthors(ID_Author);
        book.setCountOfPages(countOfPage);
        book.setGenre(GENRE);
        book.setHasOneAuthor(hasOneAuthor);
        return book;
    }

    public static Book generateBook(String title, String genre) {
        Book book = new Book();
        book.setTitle(title);
        book.setIdAuthors(ID_Author);
        book.setCountOfPages(COUNT_OF_PAGE);
        book.setGenre(genre);
        book.setHasOneAuthor(hasOneAuthor);
        return book;
    }

    public static Book generateBook(String title, int countOfPage, String genre, String name) {
        Book book = new Book();
        book.setTitle(title);
        book.setIdAuthors(ID_Author);
        book.setCountOfPages(countOfPage);
        book.setGenre(genre);
        book.setHasOneAuthor(hasOneAuthor);
        generateAuthor(name);
        return book;
    }

    private static Author generateAuthor(String name) {
        Author author = new Author();
        author.setFullName(name);
        return author;
    }
}
