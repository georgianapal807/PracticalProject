package MyLibrary;

import MyLibrary.Book;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Author {
    @Id
    @GeneratedValue
    private int id;

    private String lastName;
    private String firstName;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "author")
    private List<Book> books;


}
