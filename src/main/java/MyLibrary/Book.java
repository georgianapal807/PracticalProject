package MyLibrary;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue
    private int id;

    private String title;
    private String shortDescription;
    private int total;


    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "books")
    private List<Rent> rents;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

}
