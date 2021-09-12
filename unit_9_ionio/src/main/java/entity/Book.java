package entity;

import lombok.*;

@NoArgsConstructor
@Setter
@Getter
public class Book {

    private int id;
    private String title;
    private String genre;
    private int countOfPages;
    private int[] idAuthors;
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
