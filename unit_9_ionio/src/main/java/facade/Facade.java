package facade;

import dto.AuthorDto;
import dto.BookDto;

public interface Facade {

    void create(AuthorDto authorDto, BookDto bookDto);
    void update(AuthorDto authorDto, BookDto bookDto);
    void delete(AuthorDto authorDto, BookDto bookDto);
}
