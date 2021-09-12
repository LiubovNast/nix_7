package dao;

import entity.Author;

public interface AuthorDao {

    void create(Author author);

    void update(Author author);

    void delete(int id);

    Author findAuthorById(int id);

    Author[] findAllAuthors();

    int findIdByFullName(String fullName);

    void delete(int id, int[] idArray);

    int[] getNotNullIdBooks(int[] idBooks);
}
