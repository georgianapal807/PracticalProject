package MyLibrary;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue
    private int id;

    private String title;
    private String shortDescription;
    private int total;


    @ManyToMany(cascade = CascadeType.MERGE, mappedBy = "books")
    private List<Rent> rents;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

    public int getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getShortDescription() {
        return this.shortDescription;
    }

    public int getTotal() {
        return this.total;
    }

    public List<Rent> getRents() {
        return this.rents;
    }

    public Author getAuthor() {
        return this.author;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setRents(List<Rent> rents) {
        this.rents = rents;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Book)) return false;
        final Book other = (Book) o;
        if (!other.canEqual((Object) this)) return false;
        if (this.getId() != other.getId()) return false;
        final Object this$title = this.getTitle();
        final Object other$title = other.getTitle();
        if (this$title == null ? other$title != null : !this$title.equals(other$title)) return false;
        final Object this$shortDescription = this.getShortDescription();
        final Object other$shortDescription = other.getShortDescription();
        if (this$shortDescription == null ? other$shortDescription != null : !this$shortDescription.equals(other$shortDescription))
            return false;
        if (this.getTotal() != other.getTotal()) return false;
        final Object this$rents = this.getRents();
        final Object other$rents = other.getRents();
        if (this$rents == null ? other$rents != null : !this$rents.equals(other$rents)) return false;
        final Object this$author = this.getAuthor();
        final Object other$author = other.getAuthor();
        if (this$author == null ? other$author != null : !this$author.equals(other$author)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Book;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + this.getId();
        final Object $title = this.getTitle();
        result = result * PRIME + ($title == null ? 43 : $title.hashCode());
        final Object $shortDescription = this.getShortDescription();
        result = result * PRIME + ($shortDescription == null ? 43 : $shortDescription.hashCode());
        result = result * PRIME + this.getTotal();
        final Object $rents = this.getRents();
        result = result * PRIME + ($rents == null ? 43 : $rents.hashCode());
        final Object $author = this.getAuthor();
        result = result * PRIME + ($author == null ? 43 : $author.hashCode());
        return result;
    }

    public String toString() {
//        return "Book(id=" + this.getId() + ", title=" + this.getTitle() + ", shortDescription=" + this.getShortDescription() + ", total=" + this.getTotal() + ", rents=" + this.getRents() + ", author=" + this.getAuthor() + ")";
    return id + "";
    }
}
