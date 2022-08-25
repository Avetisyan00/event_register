import management.EventManagement;
import management.UserManagement;
import model.Event;
import model.EventType;
import model.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        UserManagement userManagement = new UserManagement();
        EventManagement eventManagement = new EventManagement();
        boolean runnable = true;
        while (runnable) {
            printCommands();
        }
    }

    public static void printCommands() {
        System.out.println();
        System.out.println("Input 1 to add new user");
        System.out.println("Input 2 to show all users");
        System.out.println("Input 3 to add new event");
        System.out.println("Input 4 to show all events");
        Scanner scanner = new Scanner(System.in);
        int command = scanner.nextInt();
        switch (command) {
            case 1:
                addUser();
                break;
            case 2:
                showUsers();
                break;
            case 3:
                addEvent();
                break;
            case 4:
                showAllEvents();
                break;
            default:
                System.out.println("invalid index");
                printCommands();
                break;
        }
    }

    private static void showAllEvents() {
        EventManagement eventManagement = new EventManagement();
        try {
            List<Event> events = eventManagement.getAllEvents();
            for (Event event : events) {
                System.out.println(event);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void addEvent() {
        try {
            Scanner scanner = new Scanner(System.in);
            EventManagement eventManagement = new EventManagement();
            System.out.println("input name");
            String name = scanner.nextLine();
            System.out.println("Input place");
            String place = scanner.nextLine();
            System.out.println("Input true if it's online, and false if it's not");
            boolean isOnline = scanner.nextBoolean();
            System.out.println("Input the price");
            double price = scanner.nextDouble();
            System.out.println("input the event type");
            EventType eventType = EventType.valueOf(scanner.next());

            Event event = new Event(name, place, isOnline, price, eventType);
            eventManagement.addEvent(event);
            System.out.println(event);
        } catch (SQLException | IllegalArgumentException e) {
            System.err.println("Something went wrong! Try again");
        }
    }

    private static void showUsers() {
        UserManagement userManagement = new UserManagement();
        try {
            List<User> users = userManagement.getAllUsers();
            for (User user : users) {
                System.out.println(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void addUser() {
        try {
            Scanner scanner = new Scanner(System.in);
            UserManagement userManagement = new UserManagement();
            System.out.println("Input name");
            String name = scanner.nextLine();
            System.out.println("Input surname");
            String surname = scanner.nextLine();
            System.out.println("Input email");
            String email = scanner.nextLine();
            System.out.println("Input event id");
            int eventId = scanner.nextInt();
            System.out.println("Input password");
            String password = scanner.nextLine();

            User user = new User(name, surname, email, eventId, password);
            userManagement.addUser(user);
        } catch (SQLException | RuntimeException e) {
            e.printStackTrace();
            addUser();
        }
    }
}
