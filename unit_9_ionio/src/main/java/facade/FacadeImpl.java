package facade;

import dto.AuthorDto;
import dto.BookDto;
import entity.Author;
import entity.Book;
import service.AuthorService;
import service.BookService;

import java.util.Random;

public class FacadeImpl implements Facade {

    private final AuthorService authorService;
    private final BookService bookService;

    public FacadeImpl(AuthorService authorService, BookService bookService) {
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @Override
    public void create(AuthorDto authorDto, BookDto bookDto) {
        Author author = new Author();
        author.setFullName(authorDto.getFullName());
        Book book = new Book();
        book.setTitle(bookDto.getTitle());
        int idBook = generateId(Entity.BOOK);
        book.setId(idBook);
        book.setGenre(bookDto.getGenre());
        book.setCountOfPages(bookDto.getCountOfPages());
        book.setHasOneAuthor(bookDto.isHasOneAuthor());
        author.setIdBooks(new int[]{idBook});
        int idAuthor = authorService.findIdByFullName(author.getFullName());
        if (idAuthor == 0) {
            idAuthor = generateId(Entity.AUTHOR);
            author.setId(idAuthor);
            authorService.create(author);
        } else {
            author.setHasOneBook(false);
            authorService.update(author);
        }
        book.setIdAuthors(new int[]{idAuthor});
        bookService.create(book);
    }

    @Override
    public void update(AuthorDto authorDto, BookDto bookDto) {

    }

    @Override
    public void delete(AuthorDto authorDto, BookDto bookDto) {

    }

    private enum Entity {
        BOOK, AUTHOR
    }

    private int generateId(Entity entity) {
        int id = new Random().nextInt();
        switch (entity) {
            case BOOK:
                Book[] books = bookService.findAllBooks();
                if (books == null) return id;
                for (int i = 0; i < books.length; i++) {
                    if (books[i] == null) continue;
                    if (books[i].getId() == id) {
                        return generateId(Entity.BOOK);
                    }
                }
                break;
            case AUTHOR:
                Author[] authors = authorService.findAllAuthors();
                if (authors == null) return id;
                for (int i = 0; i < authors.length; i++) {
                    if (authors[i] == null) continue;
                    if (authors[i].getId() == id) {
                        return generateId(Entity.AUTHOR);
                    }
                }
                break;
        }
        return id;
    }
}
