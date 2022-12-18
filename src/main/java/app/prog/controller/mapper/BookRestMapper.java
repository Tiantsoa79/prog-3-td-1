package app.prog.controller.mapper;

import app.prog.controller.response.BookResponse;
import app.prog.controller.response.CreateBookResponse;
import app.prog.controller.response.UpdateBookResponse;
import app.prog.model.BookEntity;
import app.prog.service.AuthorService;
import org.springframework.stereotype.Component;

@Component
public class BookRestMapper {

    private AuthorService authorService;
    public BookResponse toRest(BookEntity domain) {
        return BookResponse.builder()
                .id(domain.getId())
                .title(domain.getTitle())
                .author(domain.getAuthor().getName())
                .categories(domain.getCategories())
                .hasAuthor(domain.hasAuthor())
                .build();
    }

    public BookEntity toDomain(CreateBookResponse rest) {
        return BookEntity.builder()
                .author(authorService.getByName(rest.getAuthor()))
                .title(rest.getTitle())
                .categories(rest.getCategories())
                .pageNumber(0) //Constraint not null in database, default value is 0
                .build();
    }

    public BookEntity toDomain(UpdateBookResponse rest) {
        return BookEntity.builder()
                .id(rest.getId())
                .author(authorService.getByName(rest.getAuthor()))
                .title(rest.getTitle())
                .categories(rest.getCategories())
                .pageNumber(0) //Constraint not null in database, default value is 0
                .build();
    }
}
