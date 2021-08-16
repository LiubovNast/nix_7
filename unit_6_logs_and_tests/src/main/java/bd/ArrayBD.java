package bd;

import entity.Author;
import entity.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

import static controller.Console.printMessage;

public class ArrayBD {

    private static final Logger LOGGER_ERROR = LoggerFactory.getLogger("error");
    private static final int SIZE = 5;
    private static final ArrayBD instance = new ArrayBD();
    Author[] authors = new Author[SIZE];
    Book[] books = new Book[SIZE];

    private ArrayBD() {
    }

    public static ArrayBD getInstance() {
        return instance;
    }

    public void create(Book book) {
        books = changeSizeOfArray(books);
        book.setId(generateId(Entity.BOOK));
        books[getNextIndexOfArray(Entity.BOOK)] = book;
    }

    public void update(Book book, int id) {
        Book currentBook = findBookById(id);
        currentBook.setTitle(book.getTitle());
        currentBook.setGenre(book.getGenre());
        currentBook.setCountOfPages(book.getCountOfPages());
    }

    public void delete(int id, Entity entity) {
        switch (entity) {
            case BOOK:
                for (int i = 0; i < books.length; i++) {
                    if (books[i].getId() == id) {
                        books[i] = null;
                        break;
                    }
                }
                break;
            case AUTHOR:
                for (int i = 0; i < authors.length; i++) {
                    if (authors[i].getId() == id) {
                        authors[i] = null;
                        break;
                    }
                }
                break;
        }
    }

    public Book findBookById(int id) {
        for (int i = 0; i < books.length; i++) {
            if (books[i] == null) continue;
            if (books[i].getId() == id) return books[i];
        }
        return null;
    }

    public Book[] findAllBooks() {
        return getArrayWithoutNull(books);
    }

    public void create(Author author) {
        authors = changeSizeOfArray(authors);
        author.setId(generateId(Entity.AUTHOR));
        authors[getNextIndexOfArray(Entity.AUTHOR)] = author;
    }

    public void update(Author author) {
        Author current = findAuthorById(author.getId());
        current.setFullName(author.getFullName());
    }

    public Author findAuthorById(int id) {
        for (int i = 0; i < authors.length; i++) {
            if (authors[i] == null) continue;
            if (authors[i].getId() == id) return authors[i];
        }
        return null;
    }

    public Author[] findAllAuthors() {
        return getArrayWithoutNull(authors);
    }

    private Author[] getArrayWithoutNull(Author[] authors) {
        int count = 0;
        for (int i = 0; i < authors.length; i++) {
            if (authors[i] != null) count++;
        }
        if (count == authors.length) return authors;
        Author[] temp = new Author[count];
        int j = 0;
        for (int i = 0; i < authors.length; i++) {
            if (authors[i] != null) {
                temp[j] = authors[i];
                j++;
            }
        }
        return temp;
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

    private int getNextIndexOfArray(Entity entity) {
        switch (entity) {
            case BOOK:
                for (int i = 0; i < books.length; i++) {
                    if (books[i] == null) return i;
                }
                break;
            case AUTHOR:
                for (int i = 0; i < authors.length; i++) {
                    if (authors[i] == null) return i;
                }
                break;
        }
        return 0;
    }

    private int generateId(Entity entity) {
        int id = new Random().nextInt();
        switch (entity) {
            case BOOK:
                for (int i = 0; i < books.length; i++) {
                    if (books[i] == null) continue;
                    if (books[i].getId() == id) return generateId(Entity.BOOK);
                }
                break;
            case AUTHOR:
                for (int i = 0; i < authors.length; i++) {
                    if (authors[i] == null) continue;
                    if (authors[i].getId() == id) return generateId(Entity.AUTHOR);
                }
                break;
        }
        return id;
    }

    private Book[] changeSizeOfArray(Book[] array) {
        if (getNextIndexOfArray(Entity.BOOK) == array.length - 1) {
            int newSize = (int) (array.length * 1.5);
            Book[] temp = new Book[newSize];
            for (int i = 0; i < array.length; i++) {
                temp[i] = array[i];
            }
            return temp;
        } else return array;
    }

    private Author[] changeSizeOfArray(Author[] array) {
        if (getNextIndexOfArray(Entity.AUTHOR) == array.length - 1) {
            int newSize = (int) (array.length * 1.5);
            Author[] temp = new Author[newSize];
            for (int i = 0; i < array.length; i++) {
                temp[i] = array[i];
            }
            return temp;
        } else return array;
    }

    public boolean checkGenreOfBook(String genre) {
        try {
            switch (Book.Genre.valueOf(genre)) {
                case ADVENTURES:
                case DETECTIVE:
                case HUMOR:
                case NOVEL:
                case FAIRY_TALES:
                case REFERENCE:
                case SCIENCE: return true;
                default: return false;
            }
        } catch (IllegalArgumentException e) {
            LOGGER_ERROR.error(e.getMessage());
            printMessage("Indefinite genre.");
            return false;
        }
    }

    public enum Entity {
        AUTHOR, BOOK
    }

    public void delete(int id, int[] idArray) {
        for (int i = 0; i < idArray.length; i++) {
            if (idArray[i] == id) {
                idArray[i] = 0;
                break;
            }
        }
    }

    public int[] getNotNullIntArray(int[] array) {
        int count = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] != 0) count++;
        }
        if (count == array.length) return array;
        int[] temp = new int[count];
        int j = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] != 0) {
                temp[j] = array[i];
                j++;
            }
        }
        return temp;
    }

    public int[] changeSizeOfArray(int[] array) {
        if (getNextIndexOfArray(array) == array.length) {
            int newSize = (int) (array.length * 1.5 + 1);
            int[] temp = new int[newSize];
            for (int i = 0; i < array.length; i++) {
                temp[i] = array[i];
            }
            return temp;
        } else return array;
    }

    public int getNextIndexOfArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == 0) return i;
        }
        return array.length;
    }

    public int findIdOfBookOrAuthor(String titleOrFullName, Entity entity) {
        int id = 0;
        switch (entity) {
            case AUTHOR:
                for (int i = 0; i < authors.length; i++) {
                    if (authors[i] == null) continue;
                    if (authors[i].getFullName().equals(titleOrFullName)) {
                        id = authors[i].getId();
                        break;
                    }
                }
                break;
            case BOOK:
                for (int i = 0; i < books.length; i++) {
                    if (books[i] == null) continue;
                    if (books[i].getTitle().equals(titleOrFullName)) {
                        id = books[i].getId();
                        break;
                    }
                }
                break;
        }
        return id;
    }
}
