package dao;

import entity.Author;
import util.JsonReader;
import util.JsonWriter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileAuthorDao implements AuthorDao {

    private List<Author> authors;
    private final String FILE_NAME = "Author.json";

    @Override
    public void create(Author author) {
        authors = JsonReader.read(FILE_NAME, author);
        if (authors == null) {
            authors = new ArrayList<>();
        }
        authors.add(author);
        JsonWriter.write(authors.toArray(), FILE_NAME);
    }

    @Override
    public void update(Author author) {
        authors = JsonReader.read(FILE_NAME, author);
        if (authors != null) {
            if (authors.stream().anyMatch(b -> b.getId() == author.getId())) {
                Author inDbAuthor = authors.stream().filter(b -> b.getId() == author.getId()).findFirst().get();
                int index = authors.indexOf(inDbAuthor);
                authors.set(index, author);
                JsonWriter.write(authors, FILE_NAME);
            }
        }
    }

    @Override
    public void delete(int id) {
        authors = JsonReader.read(FILE_NAME, new Author());
        if (authors != null) {
            authors.removeIf(author -> author.getId() == id);
            JsonWriter.write(authors, FILE_NAME);
        }
    }

    @Override
    public Author findAuthorById(int id) {
        authors = JsonReader.read(FILE_NAME, new Author());
        if (authors != null) {
            if (authors.stream().anyMatch(a -> a.getId() == id)) {
                return authors.stream().filter(author -> author.getId() == id).findFirst().get();
            } else return null;
        } else return null;
    }

    @Override
    public List<Author> findAllAuthors() {
        Author author = new Author();
        return authors = JsonReader.read(FILE_NAME, author);
    }

    @Override
    public int findIdByFullName(String fullName) {
        authors = JsonReader.read(FILE_NAME, new Author());
        if (authors != null) {
            if (authors.stream().anyMatch(a -> a.getFullName().equals(fullName))) {
                Author author = authors.stream().filter(a -> a.getFullName().equals(fullName)).findFirst().get();
                return author.getId();
            } else return 0;

        } else return 0;
    }

    @Override
    public void delete(int idBook, Author author) {
        int[] idBooks = author.getIdBooks();
        for (int i = 0; i < idBooks.length; i++) {
            if (idBooks[i] == idBook) {
                idBooks[i] = 0;
                break;
            }
        }
        idBooks = getNotNullIdBooks(idBooks);
        if (idBooks.length == 1)
            author.setHasOneBook(true);
        update(author);
    }

    private int[] getNotNullIdBooks(int[] idBooks) {
        return Arrays.stream(idBooks).filter(id -> id != 0).toArray();
    }

    @Override
    public void updateArrayOfIdBooks(int idBook, Author author) {
        int[] array = author.getIdBooks();
        int index = getNextIndexOfArray(array);
        if (index == array.length) {
            array = changeSizeOfArray(array);
        }
        array[index] = idBook;
        if (array.length > 1) {
            author.setHasOneBook(false);
        }
        author.setIdBooks(array);
        update(author);
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
