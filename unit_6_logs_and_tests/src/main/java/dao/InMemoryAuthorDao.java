package dao;

import bd.ArrayBD;
import entity.Author;

public class InMemoryAuthorDao {

    public void create(Author author) {
        ArrayBD.getInstance().create(author);
    }

    public void update(Author author) {
        ArrayBD.getInstance().update(author);
    }

    public void delete(int id) {
        ArrayBD.getInstance().delete(id, ArrayBD.Entity.AUTHOR);
    }

    public Author findAuthorById(int id) {
        return ArrayBD.getInstance().findAuthorById(id);
    }

    public Author[] findAllAuthors() {
        return ArrayBD.getInstance().findAllAuthors();
    }
}
