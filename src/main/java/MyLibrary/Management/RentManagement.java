package MyLibrary.Management;

import MyLibrary.Book;
import MyLibrary.Rent;
import MyLibrary.User;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.criteria.JpaCriteriaQuery;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RentManagement implements ManagementCrud<Rent> {

    private SessionFactory sessionFactory;

    public RentManagement(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void insert(Rent item) {
        System.out.println("Rent issued");
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(item);
            transaction.commit();
        }
    }

    @Override
    public void update(Rent item) {
        System.out.println("Rent updated");
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(item);
            transaction.commit();
        }
    }

    @Override
    public Rent getById(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Rent.class, id);
        }
    }

    @Override
    public void delete(int id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Rent e = getById(id);
            if (e == null) {
                System.out.println("Rent with id: " + id + " doesn't exist");
            } else {
                System.out.println("Rent was deleted");
                session.remove(e);
            }
            session.getTransaction().commit();
        }
    }

    public void showRentById(int id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Rent r = getById(id);
            System.out.println("Id: " + r.getId() + "\nUser: " + r.getUser().getFirstName() + " " + r.getUser().getLastName());
            List<Book> bookList = r.getBooks();
            for (Book b : bookList) {
                System.out.println("Title: " + b.getTitle() + " written by " + b.getAuthor().getFirstName() + " " + b.getAuthor().getLastName());
            }
            System.out.println("StartDate: " + r.getStartDate() + "\nEndDate: " + r.getEndDate());


        }
    }

    public List<Rent> getAllRents() {
        try (Session session = sessionFactory.openSession()) {
            JpaCriteriaQuery<Rent> jpaCriteriaQuery = session.getCriteriaBuilder()
                    .createQuery(Rent.class);//result object will be of type Rent

            jpaCriteriaQuery.from(Rent.class); //*FROM Rent

            //Create query
            TypedQuery<Rent> typedQuery = session.createQuery(jpaCriteriaQuery);

            //Execute query -> get ResultSet(JDBC analogy) -> transform resultSet into List<Rent>
            return typedQuery.getResultList();
        }
    }
}
