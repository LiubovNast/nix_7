package controller;

import entity.Author;
import entity.Book;
import service.AuthorService;
import service.BookService;

import static controller.Console.*;

public class Actions {

    BookService bookService = new BookService();
    AuthorService authorService = new AuthorService();

    public void addNewBook() {
        printMessage("Input a title of book:");
        String title = getString();
        printMessage("Input a genre of book:");
        String genre = getString();
        printMessage("Input a count of pages of book:");
        int countOfPages = getInt();
        printMessage("Input full Name author of book:");
        String fullName = getString();

        Author author = new Author();
        Book book = new Book();
        book.setTitle(title);
        book.setGenre(genre);
        book.setCountOfPages(countOfPages);
        bookService.create(book);
        author.setFullName(fullName);
        author.setIdBooks(new int[]{book.getId()});
        int authorId = authorService.create(author);
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
        Book[] books = bookService.findAllBooks();
        for (int i = 0; i < books.length; i++) {
            if (books[i].getTitle().equals(title)) {
                int[] idAuthors = books[i].getIdAuthors();
                bookService.delete(books[i].getId());
                for (int j = 0; j < idAuthors.length; j++) {
                    if (idAuthors[j] == 0) continue;
                    author = authorService.findAuthorById(idAuthors[j]);
                    if (author.isHasOneBook()) {
                        authorService.delete(author.getId());
                        break;
                    }
                    else {
                        int[] idBooks = author.getIdBooks();
                        for (int k = 0; k < idBooks.length; k++) {
                            if (idBooks[k] == books[i].getId()) {
                                idBooks[k] = 0;
                                author.setIdBooks(idBooks);
                                break;
                            }
                        }
                    }
                }
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
    }

    public void findBook() {
        boolean isFind = false;
        printMessage("Input a title of a book:");
        String title = getString();
        Book[] books = bookService.findAllBooks();
        for (int i = 0; i < books.length; i++) {
            if (books[i].getTitle().equals(title)) {
                printBook(books[i]);
                isFind = true;
            }
        }
        if (!isFind) {
            printMessage("We cannot find this book.");
        }
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
        boolean isFind = false;
        printMessage("Input full Name author of a book:");
        String fullName = getString();
        Author[] authors = authorService.findAllAuthors();
        for (int i = 0; i < authors.length; i++) {
            if (authors[i].getFullName().equals(fullName)) {
                printAuthor(authors[i]);
                isFind = true;
            }
        }
        if (!isFind) {
            printMessage("We cannot find this author.");
        }
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
        }
        else if (answer.equals("N") || answer.equals("n")) {
            return false;
        }
        else {
            printMessage("Please, enter only Y or N:");
            String newAnswer = getString();
            return isMoreAuthors(newAnswer);
        }
    }
}
