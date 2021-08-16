package controller;

import entity.Author;
import entity.Book;
import org.junit.jupiter.api.*;
import service.AuthorService;
import service.BookService;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ActionsTest {

    private static final int SIZE_BOOK = 10;
    private static final int SIZE_AUTHOR = 1;
    private static Book[] books = new Book[SIZE_BOOK];
    private static Author[] authors = new Author[1];
    private static final BookService bookService = new BookService();
    private static final AuthorService authorService = new AuthorService();
    private static final int ERROR = 0;

    @BeforeAll
    public static void setUp() {
        for (int i = 0; i < books.length; i++) {
            Book book = BookGenerationUtil.generateBook("Test" + i);
            int idBook = bookService.create(book);
            Author author = AuthorGenerationUtil.generateAuthor("Test", new int[]{idBook});
            int idAuthor = authorService.create(author);
        }
        books = bookService.findAllBooks();
        Assertions.assertEquals(books.length, SIZE_BOOK);
    }

    @Test
    @Order(2)
    void addNewBookWithInvalidTitle() {
        Book book = BookGenerationUtil.generateBook("");
        int id = bookService.create(book);
        Assertions.assertEquals(id, ERROR);
    }

    @Test
    @Order(3)
    void addNewBookWithCorrectTitle() {
        Book book = BookGenerationUtil.generateBook("Test, test123!");
        int id = bookService.create(book);
        books = bookService.findAllBooks();
        Assertions.assertEquals(SIZE_BOOK + 1, books.length);
    }

    @Test
    @Order(4)
    void addNewBookWithInvalidCountOfPage() {
        Book book = BookGenerationUtil.generateBook("Test", 10);
        int id = bookService.create(book);
        Assertions.assertEquals(id, ERROR);
    }

    @Test
    @Order(5)
    void addNewBookWithCorrectCountOfPage() {
        Book book = BookGenerationUtil.generateBook("Test", 200);
        int id = bookService.create(book);
        books = bookService.findAllBooks();
        Assertions.assertEquals(SIZE_BOOK + 2, books.length);
    }

    @Test
    @Order(6)
    void addNewBookWithInvalidAuthor() {
        Author author = AuthorGenerationUtil.generateAuthor("1");
        int id = authorService.create(author);
        Assertions.assertEquals(id, ERROR);
    }

    @Test
    @Order(7)
    void addNewBookWithCorrectAuthor() {
        Author author = AuthorGenerationUtil.generateAuthor("Some");
        int id = authorService.create(author);
        authors = authorService.findAllAuthors();
        Assertions.assertEquals(SIZE_AUTHOR + 1, authors.length);
    }

    @Test
    @Order(7)
    void removeBook() {
        bookService.delete(bookService.findIdByTitle("Test2"));
        books = bookService.findAllBooks();
        Assertions.assertEquals(SIZE_BOOK + 1, books.length);
    }
    @Test
    @Order(8)
    void removeAuthor() {
        authorService.delete(authorService.findIdByFullName("Test"));
        authors = authorService.findAllAuthors();
        Assertions.assertEquals(SIZE_AUTHOR, authors.length);
    }
}