package facade;

import dto.AuthorDto;
import dto.BookDto;

public interface Facade {

    void create(AuthorDto authorDto, BookDto bookDto);

    void createNewAuthorToBook(AuthorDto authorDto, BookDto bookDto);

    void update(int idBook, BookDto bookDto);

    void delete(BookDto bookDto);
}
