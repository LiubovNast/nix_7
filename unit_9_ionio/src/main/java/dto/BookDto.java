package dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class BookDto {

    private String title;
    private String genre;
    private int countOfPages;
    private boolean hasOneAuthor = true;
}
