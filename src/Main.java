import java.util.*;

public class Main {
    // Unit tests
    public static void main(String[] args) {
        TrainTicketSystem ticketSystem = new TrainTicketSystem();
        User user1 = new User("Akshay", "Kumar", "akshay@kumar.com");
        User user2 = new User("Hritik", "Roshan", "hritik@roshan.com");
        User user3 = new User("Pankaj", "Tripathi", "pankaj@tripathi.com");
        User user4 = new User("Ravi", "Teja", "ravi@teja.com");

        // Test purchase ticket and receipt
        ticketSystem.purchaseTicket("London", "France", user1, 5.0, TrainTicket.SeatSection.A);
        TrainTicket receipt1 = ticketSystem.getReceipt(user1.getEmail());
        System.out.println("Receipt for User 1: " + receipt1);

        // Test view users by section
        ticketSystem.purchaseTicket("London", "France", user2, 5.0, TrainTicket.SeatSection.B);
        ticketSystem.purchaseTicket("London", "France", user3, 5.0, TrainTicket.SeatSection.B);
        ticketSystem.purchaseTicket("London", "France", user4, 5.0, TrainTicket.SeatSection.A);
        Map<String, String> usersInSectionA = ticketSystem.getUsersBySection(TrainTicket.SeatSection.A);
        System.out.println("Users in Section A: " + usersInSectionA);

        // Test remove user
        ticketSystem.removeUser(user1.getEmail());
        System.out.println("After removing User 1: " + ticketSystem.getUsersBySection(TrainTicket.SeatSection.A));

        // Test modify user seat
        ticketSystem.modifyUserSeat(user2.getEmail(), TrainTicket.SeatSection.A);
        System.out.println("After modifying User 2's seat: " + ticketSystem.getUsersBySection(TrainTicket.SeatSection.A));
    }
}
