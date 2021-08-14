package entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Book {

    private int id;
    private String title;
    private String genre;
    private int countOfPages;
    private int[] idAuthors = new int[5];
    private boolean hasOneAuthor = true;

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", countOfPages=" + countOfPages +
                ", Authors: ";
    }
}
