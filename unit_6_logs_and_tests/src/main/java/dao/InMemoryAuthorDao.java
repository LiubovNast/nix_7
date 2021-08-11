package dao;

import entity.Author;

import java.util.Random;

public class InMemoryAuthorDao {

    Author[] authors = new Author[2];

    public void create(Author author) {
        authors = changeSizeOfArray(authors);
        author.setId(generateId());
        authors[getNextIndexOfArray(authors)] = author;

    }

    public void update(Author author) {
        Author inDbAuthor = findAuthorById(author.getId());
        inDbAuthor.setFullName(author.getFullName());
        inDbAuthor.setHasOneBook (author.isHasOneBook());
        inDbAuthor.setIdBooks(author.getIdBooks());
    }

    public void delete(int id) {
        for (int i = 0; i < authors.length; i++) {
            if (authors[i].getId() == id) {
                authors[i] = null;
                break;
            }
        }
        authors = getArrayWithoutNull(authors);
    }

    public Author findAuthorById(int id) {
        for (int i = 0; i < authors.length; i++) {
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

    private int generateId() {
        int id = new Random().nextInt();
        for (int i = 0; i < authors.length; i++) {
            if (authors[i] == null) continue;
            if (authors[i].getId() == id) return generateId();
        }
        return id;
    }

        private Author[] changeSizeOfArray(Author[] array) {
            if (getNextIndexOfArray(array) == array.length) {
                int newSize = (int) (array.length * 1.5);
                Author[] temp = new Author[newSize];
                for (int i = 0; i < array.length; i++) {
                    temp[i] = array[i];
                }
                return temp;
            } else return array;
        }

        private int getNextIndexOfArray(Author[] array) {
            for (int i = 0; i < array.length; i++) {
                if (array[i] == null) return i;
            }
            return array.length;
        }
}
