package MyLibrary;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
public class Author {
    @Id
    @GeneratedValue
    private int id;

    private String lastName;
    private String firstName;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "author")
    private List<Book> books;


    public int getId() {
        return this.id;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public List<Book> getBooks() {
        return this.books;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Author)) return false;
        final Author other = (Author) o;
        if (!other.canEqual((Object) this)) return false;
        if (this.getId() != other.getId()) return false;
        final Object this$lastName = this.getLastName();
        final Object other$lastName = other.getLastName();
        if (this$lastName == null ? other$lastName != null : !this$lastName.equals(other$lastName)) return false;
        final Object this$firstName = this.getFirstName();
        final Object other$firstName = other.getFirstName();
        if (this$firstName == null ? other$firstName != null : !this$firstName.equals(other$firstName)) return false;
        final Object this$books = this.getBooks();
        final Object other$books = other.getBooks();
        if (this$books == null ? other$books != null : !this$books.equals(other$books)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Author;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + this.getId();
        final Object $lastName = this.getLastName();
        result = result * PRIME + ($lastName == null ? 43 : $lastName.hashCode());
        final Object $firstName = this.getFirstName();
        result = result * PRIME + ($firstName == null ? 43 : $firstName.hashCode());
        final Object $books = this.getBooks();
        result = result * PRIME + ($books == null ? 43 : $books.hashCode());
        return result;
    }

    public String toString() {
//        return "Author(id=" + this.getId() + ", lastName=" + this.getLastName() + ", firstName=" + this.getFirstName() + ", books=" + this.getBooks() + ")";
    return id + "";
    }
}
