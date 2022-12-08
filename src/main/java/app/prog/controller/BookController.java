package app.prog.controller;

import app.prog.controller.mapper.BookRestMapper;
import app.prog.controller.response.BookResponse;
import app.prog.model.Book;
import app.prog.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class BookController {
    private final BookService service;
    private final BookRestMapper mapper;

    @GetMapping("/books")
    public List<BookResponse> getBooks() {
        return service.getBooks().stream()
                .map(mapper::toRest)
                .toList();
    }

    //TODO: This endpoint does not match with our API. Resolve it in the question-1.
    @PostMapping("/books")
    public List<BookResponse> createBooks(@RequestBody List<BookResponse> toCreate) {
        return service.createBooks(toCreate).stream()
                .map(mapper::toRest)
                .toList();
    }

    //TODO: This endpoint does not match with our API. Resolve it in the question-2-ii.
    @PutMapping("/books")
    public List<BookResponse> updateBooks(@RequestBody List<Book> toUpdate) {
        return service.updateBooks(toUpdate).stream()
                .map(mapper::toRest)
                .toList();
    }

    @DeleteMapping("/books/{bookId}")
    public BookResponse deleteBook(@PathVariable Integer bookId) {
        return mapper.toRest(service.deleteBook(bookId));
    }
}
