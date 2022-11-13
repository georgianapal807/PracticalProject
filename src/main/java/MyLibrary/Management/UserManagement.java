package MyLibrary.Management;

import MyLibrary.Author;
import MyLibrary.Book;
import MyLibrary.User;
import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.criteria.JpaCriteriaQuery;

import java.util.List;

public class UserManagement implements ManagementCrud<User> {

    private SessionFactory sessionFactory;

    public UserManagement(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void insert(User item) {
        System.out.println("Persist person");
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(item);
            transaction.commit();
        }
    }

    @Override
    public void update(User item) {
        System.out.println("Person updated");
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(item);
            transaction.commit();
        }
    }

    @Override
    public User getById(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(User.class, id);
        }
    }

    @Override
    public void delete(int id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            User e = getById(id);
            if (e == null) {
                System.out.println("The person with id: " + id + " doesn't exist");
            } else {
                System.out.println("The person was deleted");
                session.remove(e);
            }
            session.getTransaction().commit();
        }
    }

    public void showUserById(int id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            User p = getById(id);
            System.out.println("ID: " + p.getId() + "\nFull Name: " + p.getFirstName() + " " + p.getLastName());
            System.out.println("Address: " + p.getAddress() + "\nMobile: " + p.getMobile());
        }
    }

    public List<User> getUsers() {
        try (Session session = sessionFactory.openSession()) {
            JpaCriteriaQuery<User> jpaCriteriaQuery = session.getCriteriaBuilder()
                    .createQuery(User.class);//result object will be of type User

            jpaCriteriaQuery.from(User.class); //*FROM User

            //Create query
            TypedQuery<User> typedQuery = session.createQuery(jpaCriteriaQuery);

            //Execute query -> get ResultSet(JDBC analogy) -> transform resultSet into List<Employee>
            return typedQuery.getResultList();
        }
    }

    public boolean checkUser(int item){
        boolean ok = false;
        List<User> userList = getUsers();
        for (User u : userList) {
            if (item == u.getId()) {
               ok = true;
            }
        }
        return ok;
    }
}
