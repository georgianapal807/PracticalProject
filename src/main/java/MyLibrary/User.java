package MyLibrary;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue
    private int id;

    @OneToOne(cascade = CascadeType.MERGE, mappedBy = "user")
    private Rent rent;

    private String firstName;
    private String lastName;
    private String address;
    private String mobile;

}
