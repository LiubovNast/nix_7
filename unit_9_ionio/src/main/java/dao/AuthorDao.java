package dao;

import entity.Author;

import java.util.List;

public interface AuthorDao {

    void create(Author author);

    void update(Author author);

    void delete(int id);

    Author findAuthorById(int id);

    List<Author> findAllAuthors();

    int findIdByFullName(String fullName);

    void delete(int id, Author author);

    void updateArrayOfIdBooks(int idBook, Author authorInBD);
}
