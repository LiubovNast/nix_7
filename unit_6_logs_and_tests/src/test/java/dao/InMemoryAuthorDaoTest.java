package dao;

import entity.Author;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import service.AuthorService;

class InMemoryAuthorDaoTest {
    private final static AuthorService authorService = new AuthorService();
    private final static int AUTHOR_SIZE = 10;

    @BeforeAll
    public static void setUp() {
    for (int i = 0; i < AUTHOR_SIZE; i++) {
            Author author = AuthorGenerationUtil.generateAuthor("test" + i);
            authorService.create(author);
        }
        Assertions.assertEquals(AUTHOR_SIZE, authorService.findAllAuthors().length);
    }


    @Test
    void create() {

    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void findAuthorById() {
    }

    @Test
    void findAllUsers() {
    }
}