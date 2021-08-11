package entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;

@Setter
@Getter
public class Author {

    private int id;
    private String fullName;
    private int[] idBooks;
    private boolean hasOneBook;

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", idBooks=" + Arrays.toString(idBooks) +
                ", hasOneBook=" + hasOneBook +
                '}';
    }
}
