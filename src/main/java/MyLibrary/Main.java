package MyLibrary;

import MyLibrary.Management.AuthorManagement;
import MyLibrary.Management.BookManagement;
import MyLibrary.Management.RentManagement;
import MyLibrary.Management.UserManagement;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static SessionFactory sessionFactory = new Configuration()
            .configure("hibernate.cfg.xml")
            .buildSessionFactory();


    public static void main(String[] args) {

        AuthorManagement authorManagement = new AuthorManagement(sessionFactory);
        BookManagement bookManagement = new BookManagement(sessionFactory);
        UserManagement userManagement = new UserManagement(sessionFactory);
        RentManagement rentManagement = new RentManagement(sessionFactory);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select your option:\n" + //Here is the main menu for RentManagement section
                "1.New rent\n" +
                "2.Update rent\n" +
                "3.Delete rent\n" +
                "4.Show your rent");
        String chosen = scanner.nextLine();
//-------------------------INSERTING NEW RENT-----------------------------------------------------------
        if (chosen.equals("1")) {
            int newUserRent = 0; //Here we start to check if the given user is in our database
            while (userManagement.checkUser(newUserRent) == false) {
                System.out.print("Please insert your user_id: ");
                newUserRent = scanner.nextInt();
                if (userManagement.checkUser(newUserRent) == false) {
                    System.out.println("Wrong user_ID!");
                }
            }
            boolean ok = false; //Here we start to check if the given user already rented something or not
            List<Rent> rentList = rentManagement.getAllRents();
            for (Rent r : rentList) {
                if (newUserRent == r.getUser().getId()) {
                    ok = true;
                }
            }
            if (ok == true) { //Fully booked
                System.out.println("You already rented books, please return them and you can rent again!"); //If he already rented show a msg
            }
            if (ok == false) { //If we are here it means the user_id given has no rented books. we will add a new one
                Rent rent = new Rent();
                rent.setUser(userManagement.getById(newUserRent));
                rent.setStartDate(LocalDate.now());
                rent.setEndDate(rent.getStartDate().plusDays(14));
                int newBookID = 0; //Here we start to check if the id given is in our database
                while (bookManagement.checkBook(newBookID) == false) {
                    System.out.println("Please insert the id_book you want to borrow");
                    newBookID = scanner.nextInt();
                    if (bookManagement.checkBook(newBookID) == false) {
                        System.out.println("Wrong book ID!");
                    }
                }
                if (bookManagement.getById(newBookID).getTotal() > 0) {
                    rent.setBooks(List.of(bookManagement.getById(newBookID))); //Issue a new rent
                    Book book = bookManagement.getById(newBookID);
                    book.setTotal(bookManagement.getById(newBookID).getTotal() - 1);
                    bookManagement.update(book);
                    rentManagement.insert(rent);
                } else {
                    System.out.println("Sorry the book is unavailable right now!");
                }
            }

        }
//------------------------Update RENT-----------------------------------------------------------
        if (chosen.equals("2")) {
            int updateUser = 0; //Here we start to check if the given user is in our database
            while (userManagement.checkUser(updateUser) == false) {
                System.out.print("Please insert your user_id: ");
                updateUser = scanner.nextInt();
                if (userManagement.checkUser(updateUser) == false) {
                    System.out.println("Wrong user_ID!");
                }
            }
            boolean ok = false; //Here we start to check if the given user already rented something or not
            List<Rent> rentList = rentManagement.getAllRents();
            for (Rent r : rentList) {
                if (updateUser == r.getUser().getId()) {
                    ok = true;
                }
            }
            if (ok == false) {
                System.out.println("You don't have any rented books, please go to insert menu!");
            }
            if (ok == true) {
                // If ok = true, the user has already rented some books, but he wants to add more books at his rent_id
                int rentId = 0; //will keep the rentId of the user_id given
                for (Rent r : rentList) {
                    if (updateUser == r.getUser().getId()) {
                        rentId = r.getId();
                    }
                }
                Rent updatedRent = rentManagement.getById(rentId);
                int newBookID = 0; //Here we start to check if the id given is in our database
                while (bookManagement.checkBook(newBookID) == false) {
                    System.out.println("Please insert the id_book you want to borrow");
                    newBookID = scanner.nextInt();
                    if (bookManagement.checkBook(newBookID) == false) {
                        System.out.println("Wrong book ID!");
                    }
                }
                boolean checking = false;
                List<Book> usersBookList = new ArrayList<>();
                usersBookList = updatedRent.getBooks();
                for (Book b : usersBookList) {
                    if (b.getId() == newBookID) {
                        checking = true;
                    }
                }
                if (checking == true) {
                    System.out.println("You already rented this book!");
                } else {
                    updatedRent.getBooks().add(bookManagement.getById(newBookID));
                    rentManagement.update(updatedRent);
                }
            }
        }
// -------------------------Delete RENT-----------------------------------------------------------
        if (chosen.equals("3")) {
            int newUserToDelete = 0; //Here we start to check if the given user is in our database
            while (userManagement.checkUser(newUserToDelete) == false) {
                System.out.print("Please insert your user_id: ");
                newUserToDelete = scanner.nextInt();
                if (userManagement.checkUser(newUserToDelete) == false) {
                    System.out.println("Wrong user_ID!");
                }
            }
            boolean ok = false;
            int rentIDToDelete = 0;
            List<Rent> rentList = rentManagement.getAllRents(); //Get the Renting ID by a given user_id
            for (Rent r : rentList) {
                if (newUserToDelete == r.getUser().getId()) {
                    ok = true;
                    rentIDToDelete = r.getId();
                }
            }
            if (ok == true) {
                rentManagement.delete(rentIDToDelete);
            } else {
                System.out.println("You don't have any rented books! Maybe you want to read a book?. :)");
            }
        }
// -------------------------Show your RENT-----------------------------------------------------------
        if (chosen.equals("4")) { //Showing the renting details for one user
            int newUserToShow = 0; //Here we start to check if the given user is in our database
            while (userManagement.checkUser(newUserToShow) == false) {
                System.out.print("Please insert your user_id: ");
                newUserToShow = scanner.nextInt();
                if (userManagement.checkUser(newUserToShow) == false) {
                    System.out.println("Wrong user_ID!");
                }
            }
            boolean ok = false;
            int rentIDToShow = 0;
            List<Rent> rentList = rentManagement.getAllRents(); //Here we start to check if the user already have something rented
            for (Rent r : rentList) {
                if (newUserToShow == r.getUser().getId()) {
                    ok = true;
                    rentIDToShow = r.getId();
                }
            }
            if (ok == true) {
                rentManagement.showRentById(rentIDToShow);
            } else {
                System.out.println("You don't have any rented books! Maybe you want to read a book?. :)");
            }
        }
    }
}

