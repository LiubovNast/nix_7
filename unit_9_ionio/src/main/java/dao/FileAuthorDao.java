package dao;

import entity.Author;
import util.JsonReader;
import util.JsonWriter;

import java.util.ArrayList;
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
        JsonWriter.write(authors.toArray(), FILE_NAME);
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public Author findAuthorById(int id) {
        return null;
    }

    @Override
    public Author[] findAllAuthors() {
        Author author = new Author();
        authors = JsonReader.read(FILE_NAME, author);
        if (authors == null) return new Author[]{new Author()};
        return authors.toArray(Author[]::new);
    }

    @Override
    public int findIdByFullName(String fullName) {
        return 0;
    }

    @Override
    public void delete(int id, int[] idArray) {

    }

    @Override
    public int[] getNotNullIdBooks(int[] idBooks) {
        return new int[]{0};
    }
}
