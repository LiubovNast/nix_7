package facade;

import dto.AuthorDto;
import dto.BookDto;
import entity.Author;
import entity.Book;
import service.AuthorService;
import service.BookService;

import java.util.List;
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
            author = authorService.findAuthorById(idAuthor);
            authorService.updateArrayOfIdBooks(idBook, author);
        }
        book.setIdAuthors(new int[]{idAuthor});
        bookService.create(book);
    }

    @Override
    public void createNewAuthorToBook(AuthorDto authorDto, BookDto bookDto) {
        Author author = new Author();
        author.setFullName(authorDto.getFullName());
        int idBook = bookService.findIdByTitle(bookDto.getTitle());
        Book book = bookService.findBookById(idBook);
        book.setHasOneAuthor(bookDto.isHasOneAuthor());
        int idAuthor = authorService.findIdByFullName(author.getFullName());
        if (idAuthor == 0) {
            idAuthor = generateId(Entity.AUTHOR);
            author.setId(idAuthor);
            author.setIdBooks(new int[]{idBook});
            authorService.create(author);
        } else {
            author = authorService.findAuthorById(idAuthor);
            authorService.updateArrayOfIdBooks(idBook, author);
        }
        bookService.updateArrayOfIdAuthors(idAuthor, book);
    }

    @Override
    public void update(int idBook, BookDto bookDto) {
        Book book = bookService.findBookById(idBook);
        book.setTitle(bookDto.getTitle());
        book.setGenre(bookDto.getGenre());
        book.setCountOfPages(bookDto.getCountOfPages());
        bookService.update(book, idBook);
    }

    @Override
    public void delete(BookDto bookDto) {
        int idBook = bookService.findIdByTitle(bookDto.getTitle());
        Book book = bookService.findBookById(idBook);
        int[] idAuthors = book.getIdAuthors();
        bookService.delete(idBook);
        for (int id : idAuthors) {
            if (id != 0) {
                Author author = authorService.findAuthorById(id);
                if (author.isHasOneBook()) {
                    authorService.delete(id);
                } else {
                    authorService.delete(idBook, author);
                }
            }
        }
    }

    private enum Entity {
        BOOK, AUTHOR
    }

    private int generateId(Entity entity) {
        int id = new Random().nextInt();
        switch (entity) {
            case BOOK:
                List<Book> books = bookService.findAllBooks();
                if (books == null) return id;
                for (Book book : books) {
                    if (book.getId() == id) {
                        return generateId(Entity.BOOK);
                    }
                }
                break;
            case AUTHOR:
                List<Author> authors = authorService.findAllAuthors();
                if (authors == null) return id;
                for (Author author : authors) {
                    if (author.getId() == id) {
                        return generateId(Entity.AUTHOR);
                    }
                }
                break;
        }
        return id;
    }
}
