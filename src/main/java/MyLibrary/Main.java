package MyLibrary;

import MyLibrary.Management.AuthorManagement;
import MyLibrary.Management.BookManagement;
import MyLibrary.Management.RentManagement;
import MyLibrary.Management.UserManagement;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.time.LocalDate;
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
        System.out.println("Select your option:\n" +
                "1.New rent\n" +
                "2.Update rent\n" +
                "3.Delete rent\n" +
                "4.Show your rent");
        String chosen = scanner.nextLine();
//-------------------------INSERTING NEW RENT-----------------------------------------------------------
        if (chosen.equals("1")) {
            boolean checkUser = false;
            int chosen2 = 0;
            while (checkUser == false) {
                System.out.println("Please insert your user_id");
                chosen2 = scanner.nextInt();
                List<User> userList = userManagement.getUsers();
                for (User u : userList) {
                    if (chosen2 == u.getId()) {
                        checkUser = true;
                    }
                }
                if (checkUser == false) {
                    System.out.println("Wrong user_ID!");
                }
            }
            boolean ok = false;
//            System.out.println("Please write your user_id");
//            int chosen2 = scanner.nextInt();
            List<Rent> rentList = rentManagement.getAllRents(); //incepem verificarea sa vedem daca e pe Lista de rent
            for (Rent r : rentList) {
                if (chosen2 == r.getUser().getId()) {
                    ok = true;
                }
            }
            if (ok == true) {
                System.out.println("You already rented books, please return them and you can rent again!"); //daca e il trimitem la plimbare
            } else {
                Rent rent = new Rent();
                rent.setUser(userManagement.getById(chosen2));
                rent.setStartDate(LocalDate.now());
                rent.setEndDate(rent.getStartDate().plusDays(14));
                boolean check = false;
                int chosen3 = 0;
                while (check == false) {
                    System.out.println("Please insert the id_book you want to borrow");
                    chosen3 = scanner.nextInt();
                    List<Book> bookList = bookManagement.getAllBooks();
                    for (Book b : bookList) {
                        if (chosen3 == b.getId()) {
                            check = true;
                        }
                    }
                    if (check == false) {
                        System.out.println("Wrong book ID!");
                    }
                }
                rent.setBooks(List.of(bookManagement.getById(chosen3)));
                rentManagement.insert(rent);
            }

        }
//-------------------------Update RENT-----------------------------------------------------------
//        if (chosen.equals("2")) {
//            boolean ok = false;
//            System.out.println("Please write your user_id");
//            int chosen2 = scanner.nextInt();
//            List<Rent> rentList = rentManagement.getAllRents();
//            for (Rent r : rentList) {
//                if (chosen2 == r.getUser().getId()) {
//                    ok = true;
//                }
//            }
//            // If ok = true, the user has already rented some books and he wants to add more books at his rent_id
//            int rentId = 0;
//            for (Rent r : rentList) {
//                if (chosen2 == r.getUser().getId()) {
//                    rentId = r.getId();
//                }
//            }
//            Rent updatedRent = rentManagement.getById(rentId);
//            boolean check = false;
//            int chosen3 = 0;
//            while (check == false) { //Check if the id_book exists
//                System.out.println("Please insert the id_book you want to borrow");
//                chosen3 = scanner.nextInt();
//                List<Book> bookList = bookManagement.getAllBooks();
//                for (Book b : bookList) {
//                    if (chosen3 == b.getId()) {
//                        check = true;
//                    }
//                }
//                if (check == false) {
//                    System.out.println("Wrong book ID!");
//                }
//            }
////          chosen3 - has the id of the book we need to add
//            List<Book> bookList = updatedRent.getBooks();
//            for (Book b : bookList) {
//                System.out.println(b.getId() + " ");
//            }
////            bookList.add(bookManagement.getById(chosen3));
////            updatedRent.setBooks(bookList);
////            rentManagement.update(updatedRent);
//        }
//
// -------------------------Delete RENT-----------------------------------------------------------
        if (chosen.equals("3")) {
            boolean checkUser = false;
            int chosen2 = 0;
            while (checkUser == false) {
                System.out.println("Please insert your user_id");
                chosen2 = scanner.nextInt();
                List<User> userList = userManagement.getUsers();
                for (User u : userList) {
                    if (chosen2 == u.getId()) {
                        checkUser = true;
                    }
                }
                if (checkUser == false) {
                    System.out.println("Wrong user_ID!");
                }
            }
            boolean ok = false;
            int rentIDToDelete = 0;
            List<Rent> rentList = rentManagement.getAllRents(); //incepem verificarea sa vedem daca e pe Lista de rent
            for (Rent r : rentList) {
                if (chosen2 == r.getUser().getId()) {
                    ok = true;
                    rentIDToDelete = r.getId();
                }
            }
            if (ok == true) {
                rentManagement.delete(rentIDToDelete);
            } else {
                System.out.println("You don't have any rented books ongoing");
            }
        }
// -------------------------Show your RENT-----------------------------------------------------------
        if (chosen.equals("4")) {
            boolean checkUser = false;
            int chosen2 = 0;
            while (checkUser == false) {
                System.out.println("Please insert your user_id");
                chosen2 = scanner.nextInt();
                List<User> userList = userManagement.getUsers();
                for (User u : userList) {
                    if (chosen2 == u.getId()) {
                        checkUser = true;
                    }
                }
                if (checkUser == false) {
                    System.out.println("Wrong user_ID!");
                }
            }
            boolean ok = false;
            int rentIDToShow = 0;
            List<Rent> rentList = rentManagement.getAllRents(); //incepem verificarea sa vedem daca e pe Lista de rent
            for (Rent r : rentList) {
                if (chosen2 == r.getUser().getId()) {
                    ok = true;
                    rentIDToShow = r.getId();
                }
            }
            if (ok == true) {
                rentManagement.showRentById(rentIDToShow);
            } else {
                System.out.println("You don't have any rented books ongoing");
            }
        }
    }
}
