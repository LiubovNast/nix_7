package service;

import dao.AuthorDao;
import dao.FileAuthorDao;
import entity.Author;

import java.util.List;

import static controller.Console.printMessage;

public class AuthorService {

    private final AuthorDao authorDao = new FileAuthorDao();

    public void create(Author author) {
        if (!checkAuthorFullName(author.getFullName())) {
            return;
        }
        List<Author> authors = findAllAuthors();
        if (authors != null) {
            for (Author authorInDb : authors) {
                if (authorInDb.getFullName().equals(author.getFullName())) {
                    authorDao.updateArrayOfIdBooks(author.getIdBooks()[0], authorInDb);
                    return;
                }
            }
        }
        authorDao.create(author);
    }

    public void delete(int id) {
        authorDao.delete(id);
    }

    public Author findAuthorById(int id) {
        return authorDao.findAuthorById(id);
    }

    public List<Author> findAllAuthors() {
        return authorDao.findAllAuthors();
    }

    public int findIdByFullName(String fullName) {
        return authorDao.findIdByFullName(fullName);
    }

    public void delete(int id, Author author) {
        authorDao.delete(id, author);
    }

    public void updateArrayOfIdBooks(int idBook, Author author) {
        authorDao.updateArrayOfIdBooks(idBook, author);
    }

    private boolean checkAuthorFullName(String fullName) {
        if (fullName.length() < 1 || fullName.length() > 100) {
            printMessage("Bad name author, too long.");
            return false;
        } else if (!fullName.matches("[A-Za-z\\s\\-\\.]+$")) {
            printMessage("Invalid author's name");
            return false;
        }
        return true;
    }
}
