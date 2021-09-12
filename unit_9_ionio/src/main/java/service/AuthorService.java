package service;

import dao.AuthorDao;
import dao.FileAuthorDao;
import entity.Author;

import static controller.Console.printMessage;

public class AuthorService {

    private final AuthorDao authorDao = new FileAuthorDao();

    public void create(Author author) {
        if (!checkAuthorFullName(author.getFullName())) {
            return;
        }
        Author[] authors = findAllAuthors();
        if (authors != null) {
            for (int i = 0; i < authors.length; i++) {
                if (authors[i] != null) {
                    if (authors[i].getFullName().equals(author.getFullName())) {
                        updateArrayOfIdBooks(author.getIdBooks()[0], authors[i]);
                        authors[i].setHasOneBook(false);
                    }
                }
            }
        }
        authorDao.create(author);
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

    private void updateArrayOfIdBooks(int idBook, Author authorInBD) {
//        int[] array = authorInBD.getIdBooks();
//        array = ArrayBD.getInstance().changeSizeOfArray(array);
//        array[ArrayBD.getInstance().getNextIndexOfArray(array)] = idBook;
//        authorInBD.setIdBooks(array);
    }

    public void update(Author author) {
        authorDao.update(author);
    }

    public void delete(int id) {
        authorDao.delete(id);
    }

    public Author findAuthorById(int id) {
        return authorDao.findAuthorById(id);
    }

    public Author[] findAllAuthors() {
        return authorDao.findAllAuthors();
    }

    public int findIdByFullName(String fullName) {
        return authorDao.findIdByFullName(fullName);
    }

    public void delete(int id, Author author) {
        int[] idBooks = author.getIdBooks();
        authorDao.delete(id, idBooks);
        idBooks = authorDao.getNotNullIdBooks(idBooks);
        if (idBooks.length == 1)
            author.setHasOneBook(true);
    }
}
