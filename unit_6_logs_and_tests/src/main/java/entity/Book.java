package entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;

@Setter
@Getter
public class Book {

    private int id;
    private String title;
    private String genre;
    private int countOfPages;
    private int[] idAuthors;

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", countOfPages=" + countOfPages +
                ", idAuthors=" + Arrays.toString(idAuthors) +
                '}';
    }
}
