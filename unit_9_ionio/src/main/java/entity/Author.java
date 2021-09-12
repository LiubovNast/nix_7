package entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Author {

    private int id;
    private String fullName;
    private int[] idBooks;
    private boolean hasOneBook = true;

    @Override
    public String toString() {
        return "Author{" +
                " fullName='" + fullName + '\'' +
                ", hasOneBook=" + hasOneBook +
                '}';
    }
}
