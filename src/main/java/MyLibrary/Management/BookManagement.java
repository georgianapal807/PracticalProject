package MyLibrary.Management;

import MyLibrary.Author;
import MyLibrary.Book;
import MyLibrary.Rent;
import MyLibrary.User;
import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.criteria.JpaCriteriaQuery;

import java.util.List;

public class BookManagement implements ManagementCrud<Book> {

    private SessionFactory sessionFactory;

    public BookManagement(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void insert(Book item) {
        System.out.println("Persist book");
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(item);
            transaction.commit();
        }
    }

    @Override
    public void update(Book item) {
        System.out.println("Book updated");
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(item);
            transaction.commit();
        }
    }

    @Override
    public Book getById(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Book.class, id);
        }
    }

    @Override
    public void delete(int id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Book e = getById(id);
            if (e == null) {
                System.out.println("The book with id: " + id + " doesn't exist");
            } else {
                System.out.println("The book was deleted");
                session.remove(e);
            }
            session.getTransaction().commit();
        }
    }

    public void showBookById(int id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Book b = getById(id);
            System.out.println("Id: " + b.getId() + "\nTitle: " + b.getTitle() + "\nShort Description " + b.getShortDescription() + "\nDisponibilitate: " + b.getTotal());
            Author a = b.getAuthor();
            System.out.println("Author: " + a.getFirstName() + " " + a.getLastName());
        }
    }
    public List<Book> getAllBooks() {
        try (Session session = sessionFactory.openSession()) {
            JpaCriteriaQuery<Book> jpaCriteriaQuery = session.getCriteriaBuilder()
                    .createQuery(Book.class);//result object will be of type Book

            jpaCriteriaQuery.from(Book.class); //*FROM Book

            //Create query
            TypedQuery<Book> typedQuery = session.createQuery(jpaCriteriaQuery);

            //Execute query -> get ResultSet(JDBC analogy) -> transform resultSet into List<Book>
            return typedQuery.getResultList();
        }
    }
    public boolean checkBook(int item){
        boolean ok = false;
        List<Book> bookList = getAllBooks();
        for (Book u : bookList) {
            if (item == u.getId()) {
                ok = true;
            }
        }
        return ok;
    }
}
