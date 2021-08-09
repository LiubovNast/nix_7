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
    private int[] idAuthors;
}
