package entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Author {

    private int id;
    private String fullName;
    private int[] idBooks = new int[5];
    private boolean hasOneBook = true;

    @Override
    public String toString() {
        return "Author{" +
                " fullName='" + fullName + '\'' +
                ", hasOneBook=" + hasOneBook +
                '}';
    }
}
