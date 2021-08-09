package dao;

import entity.Author;

import java.util.Random;

public class InMemoryAuthorDao {

    Author[] authors = new Author[5];

    public void create(Author author) {
        author.setId(generateId());
        authors[0] = author;
    }

    public void update(Author author) {
        Author inDbAuthor = findAuthorById(author.getId());
        inDbAuthor.setFullName(author.getFullName());
        inDbAuthor.setHasOneBook (author.isHasOneBook());
        inDbAuthor.setIdBooks(author.getIdBooks());
    }

    public void delete(int id) {
        authors[id] = null;
    }

    public Author findAuthorById(int id) {
        for (int i = 0; i < authors.length; i++) {
            if (authors[i].getId() == id) return authors[i];
        }
        return null;
    }

    public Author[] findAllUsers() {
        return authors;
    }

    private int generateId() {
        int id = new Random().nextInt();
        for (int i = 0; i < authors.length; i++) {
            if (authors[i].getId() == id) return generateId();
        }
        return id;
    }
}
