package app.prog.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    /*
    TODO-4-a: Note that i use serial type for ID in database. What does serial do ?
    Il est utilisé pour générer une séquence d'int comme clé primaire d'une table.
    TODO-4-b: Should I map it with int ? Fix it if there is a problem
       non
     */
    @Id
    private Integer id;
    private String title;
    private String author;
    private Integer pageNumber;
    private LocalDate releaseDate;

    public boolean hasAuthor() {
        return author != null;
    }

    public Book(String title, String author){
        this.title = title;
        this.author = author;
    }
}
