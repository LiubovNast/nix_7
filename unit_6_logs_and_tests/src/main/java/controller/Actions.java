package controller;

import entity.Author;
import entity.Book;
import service.AuthorService;
import service.BookService;

import static controller.Console.*;

public class Actions {

    BookService bookService = new BookService();
    AuthorService authorService = new AuthorService();
    private static final int ERROR = 0;

    public void addNewBook() {
        printMessage("Input a title of book:");
        String title = getString();
        printMessage("Input a genre of book (we have only - DETECTIVE, SCIENCE, ADVENTURES, NOVEL, FAIRY_TALES, HUMOR, REFERENCE):");
        String genre = getString();
        printMessage("Input a count of pages of book:");
        int countOfPages = getInt();

        Author author = new Author();
        Book book = new Book();
        book.setTitle(title);
        book.setGenre(genre);
        book.setCountOfPages(countOfPages);
        int bookId = bookService.create(book);
        if (bookId == ERROR) {
            addNewBook();
        }
        printMessage("Input full Name author of book:");
        String fullName = getString();
        author.setFullName(fullName);
        author.setIdBooks(new int[]{bookId});
        int authorId = authorService.create(author);
        if (authorId == ERROR) {
            bookService.delete(book.getId());
            addNewBook();
        }
        book.setIdAuthors(new int[]{authorId});

        printMessage("This book has more authors? Y/N?");
        String answer = getString();
        boolean isMoreAuthors = isMoreAuthors(answer);
        while (isMoreAuthors) {
            addMoreAuthors(book);
            printMessage("This book has more authors? Y/N?");
            answer = getString();
            isMoreAuthors = isMoreAuthors(answer);
        }
    }

    private void addMoreAuthors(Book book) {
        printMessage("Input full Name author of book:");
        String fullName = getString();
        Author author = new Author();
        author.setFullName(fullName);
        author.setIdBooks(new int[]{book.getId()});
        int authorId = authorService.create(author);
        bookService.updateArrayOfIdAuthors(authorId, book);
        book.setHasOneAuthor(false);
    }

    public void removeBook() {
        Author author;
        printMessage("Input a title of a book:");
        String title = getString();
        int id = bookService.findIdByTitle(title);
        int[] idAuthors = bookService.findBookById(id).getIdAuthors();
        bookService.delete(id);
        for (int j = 0; j < idAuthors.length; j++) {
            if (idAuthors[j] == 0) continue;
            author = authorService.findAuthorById(idAuthors[j]);
            if (author.isHasOneBook()) {
                authorService.delete(idAuthors[j]);
            } else {
                authorService.delete(id, author);
            }
        }
    }

    public void takeListAllBooks() {
        printMessage("This is list of all our books:");
        Book[] books = bookService.findAllBooks();
        for (int i = 0; i < books.length; i++) {
            printBook(books[i]);
        }
    }

    public void takeListAllAuthors() {
        printMessage("This is list of all our authors:");
        Author[] authors = authorService.findAllAuthors();
        for (int i = 0; i < authors.length; i++) {
            printAuthor(authors[i]);
        }
    }

    public void changeInfoAboutBook() {
        printMessage("Input a title of a book, which book's info you want to change:");
        String title = getString();
        int id = bookService.findIdByTitle(title);
        if (id == 0) {
            printMessage("We cannot find this book.");
        } else {
            printMessage("Input a new title of book:");
            String newTitle = getString();
            printMessage("Input a new genre of book:");
            String genre = getString();
            printMessage("Input a new count of pages of book:");
            int countOfPages = getInt();
            Book book = new Book();
            book.setTitle(title);
            book.setGenre(genre);
            book.setCountOfPages(countOfPages);
            bookService.update(book, id);
        }

    }

    public void findBook() {
        printMessage("Input a title of a book:");
        String title = getString();
        int id = bookService.findIdByTitle(title);
        if (id == 0) {
            printMessage("We cannot find this book.");
        } else
            printBook(bookService.findBookById(id));
    }

    public void findGenre() {
        boolean isFind = false;
        printMessage("Input a genre of book:");
        String genre = getString();
        Book[] books = bookService.findAllBooks();
        for (int i = 0; i < books.length; i++) {
            if (books[i].getGenre().equals(genre)) {
                printBook(books[i]);
                isFind = true;
            }
        }
        if (!isFind) {
            printMessage("We cannot find this book.");
        }
    }

    public void findAuthor() {
        printMessage("Input full Name author of a book:");
        String fullName = getString();
        int id = authorService.findIdByFullName(fullName);
        if (id == 0) {
            printMessage("We cannot find this author.");
        } else
            printAuthor(authorService.findAuthorById(id));
    }

    private void printBook(Book book) {
        int[] idAuthors = book.getIdAuthors();
        printMessage(book.toString());
        for (int j = 0; j < idAuthors.length; j++) {
            if (idAuthors[j] == 0) continue;
            printMessage("- author's full name: " + authorService.findAuthorById(idAuthors[j]).getFullName());
        }
        printMessage("");
    }

    private void printAuthor(Author author) {
        printMessage(author.toString());
        int[] idBooks = author.getIdBooks();
        for (int j = 0; j < idBooks.length; j++) {
            if (idBooks[j] == 0) continue;
            printMessage("- title: " + bookService.findBookById(idBooks[j]).getTitle());
        }
        printMessage("");
    }

    private boolean isMoreAuthors(String answer) {
        if (answer.equals("Y") || answer.equals("y")) {
            return true;
        } else if (answer.equals("N") || answer.equals("n")) {
            return false;
        } else {
            printMessage("Please, enter only Y or N:");
            String newAnswer = getString();
            return isMoreAuthors(newAnswer);
        }
    }
}
