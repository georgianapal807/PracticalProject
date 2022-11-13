package MyLibrary.Management;

import MyLibrary.Author;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class AuthorManagement implements ManagementCrud<Author> {

    private SessionFactory sessionFactory;

    public AuthorManagement(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void insert(Author item) {
        System.out.println("Author inserted");
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(item);
            transaction.commit();

        }
    }

    @Override
    public void update(Author item) {
        System.out.println("Author updated");
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(item);
            transaction.commit();
        }
    }

    @Override
    public Author getById(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Author.class, id);
        }
    }

    @Override
    public void delete(int id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Author e = getById(id);
            if (e == null) {
                System.out.println("Author with id: " + id + " doesn't exist");
            } else {
                System.out.println("Author was deleted");
                session.remove(e);
            }
            session.getTransaction().commit();
        }
    }

    public void showAuthorById(int id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Author a = getById(id);
            System.out.println("Id: " + a.getId() + "\nFull Name " + a.getFirstName() + " " + a.getLastName());
        }
    }
}

