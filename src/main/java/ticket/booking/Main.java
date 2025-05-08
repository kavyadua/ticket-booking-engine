package ticket.booking;


import ticket.booking.services.UserBookingService;
import ticket.booking.entities.Train;
import ticket.booking.entities.User;
import ticket.booking.utils.UserServiceUtil;

import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Running Train Booking System");
        Scanner scanner = new Scanner(System.in);

        int option = 0;

        UserBookingService userBookingService;

        try {
            userBookingService = new UserBookingService();
        }catch(IOException ex) {
            System.out.print(ex);
            System.out.println("There is something wrong");
            return;
        }

        while(option!=7) {
            System.out.println("Choose option");
            System.out.println("1. Sign up");
            System.out.println("2. Login");
            System.out.println("3. Fetch Bookings");
            System.out.println("4. Search Trains");
            System.out.println("5. Book a Seat");
            System.out.println("6. Cancel my Booking");
            System.out.println("7. Exit the App");
            option = scanner.nextInt();
            switch (option){
                case 1:
                    System.out.println("Enter the username to signup");
                    String nameToSignUp = scanner.next();
                    System.out.println("Enter the password to signup");
                    String passwordToSignUp = scanner.next();
                    User userToSignup = new User(nameToSignUp, passwordToSignUp, UserServiceUtil.hashPassword(passwordToSignUp), new ArrayList<>(), UUID.randomUUID().toString());
                    userBookingService.signUp(userToSignup);
                    break;
                case 2:
                    System.out.println("Enter the username to Login");
                    String nameToLogin = scanner.next();
                    System.out.println("Enter the password to signup");
                    String passwordToLogin = scanner.next();
                    User userToLogin = new User(nameToLogin, passwordToLogin, UserServiceUtil.hashPassword(passwordToLogin), new ArrayList<>(), UUID.randomUUID().toString());
                    try{
                        userBookingService = new UserBookingService(userToLogin);
                        if (userBookingService.loginUser()) {
                            System.out.println("Login successful!");
                        } else {
                            System.out.println("Invalid username or password. Please try again.");
                        }
                    }catch (IOException ex){
                        System.out.print(ex);
                        return;
                    }
                    break;
                case 3:
                    System.out.println("Fetching your bookings");
                    userBookingService.fetchBookings();
                    break;
                default:
                    break;
            }
        }
    }
}