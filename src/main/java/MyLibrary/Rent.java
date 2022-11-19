package MyLibrary;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Rent {

    @Id
    @GeneratedValue
    private int id;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name = "books",
            joinColumns = {@JoinColumn(name = "rent_id")},
            inverseJoinColumns = {@JoinColumn(name = "book_id")}
    )
    private List<Book> books;

    private LocalDate startDate;

    private LocalDate endDate;

    public Rent() {
    }


    public int getId() {
        return this.id;
    }

    public User getUser() {
        return this.user;
    }

    public List<Book> getBooks() {
        return this.books;
    }

    public LocalDate getStartDate() {
        return this.startDate;
    }

    public LocalDate getEndDate() {
        return this.endDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Rent)) return false;
        final Rent other = (Rent) o;
        if (!other.canEqual((Object) this)) return false;
        if (this.getId() != other.getId()) return false;
        final Object this$user = this.getUser();
        final Object other$user = other.getUser();
        if (this$user == null ? other$user != null : !this$user.equals(other$user)) return false;
        final Object this$books = this.getBooks();
        final Object other$books = other.getBooks();
        if (this$books == null ? other$books != null : !this$books.equals(other$books)) return false;
        final Object this$startDate = this.getStartDate();
        final Object other$startDate = other.getStartDate();
        if (this$startDate == null ? other$startDate != null : !this$startDate.equals(other$startDate)) return false;
        final Object this$endDate = this.getEndDate();
        final Object other$endDate = other.getEndDate();
        if (this$endDate == null ? other$endDate != null : !this$endDate.equals(other$endDate)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Rent;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + this.getId();
        final Object $user = this.getUser();
        result = result * PRIME + ($user == null ? 43 : $user.hashCode());
        final Object $books = this.getBooks();
        result = result * PRIME + ($books == null ? 43 : $books.hashCode());
        final Object $startDate = this.getStartDate();
        result = result * PRIME + ($startDate == null ? 43 : $startDate.hashCode());
        final Object $endDate = this.getEndDate();
        result = result * PRIME + ($endDate == null ? 43 : $endDate.hashCode());
        return result;
    }

    public String toString() {
//        return "Rent(id=" + this.getId() + ", user=" + this.getUser() + ", books=" + this.getBooks() + ", startDate=" + this.getStartDate() + ", endDate=" + this.getEndDate() + ")";
        return id + "";
    }
}
