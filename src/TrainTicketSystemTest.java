import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;


public class TrainTicketSystemTest {
    @Test
    public void testPurchaseTicket() {
        TrainTicketSystem ticketSystem = new TrainTicketSystem();
        User user = new User("Akshay", "Kumar", "akshay@kumar.com");
        TrainTicket ticket = ticketSystem.purchaseTicket("London", "France", user, 5.0, TrainTicket.SeatSection.A);
        assertEquals(user, ticket.getUser());
    }

    @Test
    public void testGetReceipt() {
        TrainTicketSystem ticketSystem = new TrainTicketSystem();
        User user = new User("Akshay", "Kumar", "akshay@kumar.com");
        TrainTicket ticket = ticketSystem.purchaseTicket("London", "France", user, 5.0, TrainTicket.SeatSection.A);
        assertEquals(ticket, ticketSystem.getReceipt("akshay@kumar.com"));
    }

    @Test
    public void testRemoveUser() {
        TrainTicketSystem ticketSystem = new TrainTicketSystem();
        User user = new User("Akshay", "Kumar", "akshay@kumar.com");
        ticketSystem.purchaseTicket("London", "France", user, 5.0, TrainTicket.SeatSection.A);
        ticketSystem.removeUser("akshay@kumar.com");
        assertTrue(ticketSystem.getUsersBySection(TrainTicket.SeatSection.A).isEmpty());
    }

    @Test
    public void testModifyUserSeat() {
        TrainTicketSystem ticketSystem = new TrainTicketSystem();
        User user = new User("Akshay", "Kumar", "akshay@kumar.com");
        ticketSystem.purchaseTicket("London", "France", user, 5.0, TrainTicket.SeatSection.A);
        ticketSystem.modifyUserSeat("akshay@kumar.com", TrainTicket.SeatSection.B);
        assertTrue(ticketSystem.getUsersBySection(TrainTicket.SeatSection.A).isEmpty());
        assertTrue(ticketSystem.getUsersBySection(TrainTicket.SeatSection.B).containsKey("akshay@kumar.com"));
    }

    @Test
    public void testGetUsersBySection() {
        TrainTicketSystem ticketSystem = new TrainTicketSystem();
        User user1 = new User("Akshay", "Kumar", "akshay@kumar.com");
        User user2 = new User("Ravi", "Teja", "ravi@teja.com");
        ticketSystem.purchaseTicket("London", "France", user1, 5.0, TrainTicket.SeatSection.A);
        ticketSystem.purchaseTicket("London", "France", user2, 5.0, TrainTicket.SeatSection.B);
        assertEquals(1, ticketSystem.getUsersBySection(TrainTicket.SeatSection.A).size());
        assertEquals(1, ticketSystem.getUsersBySection(TrainTicket.SeatSection.B).size());
    }
}
