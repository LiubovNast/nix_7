package controller;

import dto.AuthorDto;
import dto.BookDto;
import entity.Author;
import entity.Book;
import facade.Facade;
import facade.FacadeImpl;
import service.AuthorService;
import service.BookService;

import java.util.List;

import static controller.Console.*;

public class Actions {

    BookService bookService = new BookService();
    AuthorService authorService = new AuthorService();
    Facade facade = new FacadeImpl(authorService, bookService);

    public void addNewBook() {
        AuthorDto author = new AuthorDto();
        BookDto book = new BookDto();
        printMessage("Input a title of book:");
        String title = getString();
        book.setTitle(title);
        String genre = getGenre();
        book.setGenre(genre);
        printMessage("Input a count of pages of book:");
        int countOfPages = getInt();
        book.setCountOfPages(countOfPages);
        printMessage("Input full Name author of book:");
        String fullName = getString();
        author.setFullName(fullName);
        facade.create(author, book);

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

    private String getGenre() {
        printMessage("Choose a genre of book:\n" +
                "1.DETECTIVE; \n" +
                "2.SCIENCE;\n" +
                "3.ADVENTURES;\n" +
                "4.NOVEL;\n" +
                "5.FAIRY_TALES;\n" +
                "6.HUMOR;\n" +
                "7.REFERENCE.\n");
        int choice = getInt();
        switch (choice) {
            case 1:
                return "DETECTIVE";
            case 2:
                return "SCIENCE";
            case 3:
                return "ADVENTURES";
            case 4:
                return "NOVEL";
            case 5:
                return "FAIRY_TALES";
            case 6:
                return "HUMOR";
            case 7:
                return "REFERENCE";
            default:
                printMessage("Sorry, we don't have this genre.");
                return getGenre();
        }
    }

    private void addMoreAuthors(BookDto book) {
        printMessage("Input full Name author of book:");
        String fullName = getString();
        AuthorDto author = new AuthorDto();
        author.setFullName(fullName);
        book.setHasOneAuthor(false);
        facade.createNewAuthorToBook(author, book);
    }

    public void removeBook() {
        printMessage("Input a title of a book:");
        String title = getString();
        BookDto bookDto = new BookDto();
        bookDto.setTitle(title);
        facade.delete(bookDto);
    }

    public void takeListAllBooks() {
        printMessage("This is list of all our books:");
        List<Book> books = bookService.findAllBooks();
        for (Book book : books) {
            printBook(book);
        }
    }

    public void takeListAllAuthors() {
        printMessage("This is list of all our authors:");
        List<Author> authors = authorService.findAllAuthors();
        for (Author author : authors) {
            printAuthor(author);
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
            String genre = getGenre();
            printMessage("Input a new count of pages of book:");
            int countOfPages = getInt();
            BookDto book = new BookDto();
            book.setTitle(newTitle);
            book.setGenre(genre);
            book.setCountOfPages(countOfPages);
            facade.update(id, book);
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
        List<Book> books = bookService.findAllBooks();
        for (Book book : books) {
            if (book.getGenre().equals(genre)) {
                printBook(book);
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
        for (int id : idAuthors) {
            if (id == 0) continue;
            Author author = authorService.findAuthorById(id);
            if (author != null) {
                printMessage("- author's full name: " + author.getFullName());
            }
        }
        printMessage("");
    }

    private void printAuthor(Author author) {
        printMessage(author.toString());
        int[] idBooks = author.getIdBooks();
        for (int id : idBooks) {
            if (id == 0) continue;
            Book book = bookService.findBookById(id);
            if (book != null) {
                printMessage("- title: " + book.getTitle());
            }
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
