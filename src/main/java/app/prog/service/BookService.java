package app.prog.service;

import app.prog.controller.response.BookResponse;
import app.prog.model.Book;
import app.prog.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BookService {
    private final BookRepository repository;

    public List<Book> getBooks() {
        return repository.findAll();
    }

    /*
    TODO-1: Only title and author should be provided during the creation of a new book.
    The ID is created by default by the database so it should not be provided at all.
    Therefore, the pageNumber and the releaseDate exists also in the Book model.
    A solution to create a book without the ID, the pageNumber and the releaseDate ?
     */
    public List<Book> createBooks(List<BookResponse> toCreate) {
        List<Book> books = new ArrayList<>();
        for ( BookResponse bookResponse : toCreate
             ) {
            Book newBook = new Book();
            newBook.setTitle(bookResponse.getTitle());
            newBook.setAuthor(bookResponse.getAuthor());
            books.add(newBook);

        }

        return books;
    }

    /*
    TODO-2-i: Why the createBooks and the updateBooks use the same repository method saveAll ?
    allows us to save multiple entities to the DB even it already exists
    TODO-2-ii : Only ID, title and author should be provided during the update.
    Therefore, the pageNumber and the release date exists also in the Book model.
    A solution to update a book without the pageNumber and the releaseDate ?
     */
    public List<Book> updateBooks(List<Book> toUpdate) {
        List<Book> books = new ArrayList<>();
        for ( Book book: toUpdate
        ) {
            Book newBook = new Book();
            newBook.setId(book.getId());
            if (book.getAuthor()!=null){
                newBook.setAuthor(book.getAuthor());
            }
            if (book.getTitle()!=null){
                newBook.setTitle(book.getTitle());
            }
            books.add(newBook);
        }
        return repository.saveAll(books);
    }

    //TODO-3: should I use Integer here or int ? Why ?
    // int cuz the given id should not be null as int doesn't take null value
    public Book deleteBook(int bookId) {
        /*
        TIPS: From the API, the Class Optional<T> is :
        A container object which may or may not contain a non-null value.
        If a value is present, isPresent() returns true.
        If no value is present, the object is considered empty and isPresent() returns false.

        T is the type of the value, for example : here the class type is Book
         */
        Optional<Book> optional = repository.findById(String.valueOf(bookId));
        if (optional.isPresent()) {
            repository.delete(optional.get());
            return optional.get();
        } else {
        /*
        TODO-5 : The exception appears as an internal server error, status 500.
        We all know that the appropriate error status is the 404 Not Found.
        Any solution to do this ?
        These links may help you :
        Link 1 : https://www.baeldung.com/spring-response-entity
        Link 2 : https://www.baeldung.com/exception-handling-for-rest-with-spring
         */
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Book." + bookId + " not found");
        }
    }
}
