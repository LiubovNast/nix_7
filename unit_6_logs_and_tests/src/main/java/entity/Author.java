package entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Author {

    private int id;
    private String fullName;
    private int[] idBooks;
    private boolean hasOneBook;
}
