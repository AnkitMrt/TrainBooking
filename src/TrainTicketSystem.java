import java.util.*;


public class TrainTicketSystem {
    private Map<String, TrainTicket> tickets = new HashMap<>();
    private Map<String, String> seatAllocations = new HashMap<>();
    private Map<TrainTicket.SeatSection, Map<String, Boolean>> seatOccupancy = new HashMap<>(); // Map of section to seat occupancy

    public TrainTicketSystem() {
        // Initialize seat occupancy for each section
        seatOccupancy.put(TrainTicket.SeatSection.A, new HashMap<>());
        seatOccupancy.put(TrainTicket.SeatSection.B, new HashMap<>());
        
        // Populate seat occupancy maps with initial values (all seats vacant)
        initializeSeatOccupancy(TrainTicket.SeatSection.A);
        initializeSeatOccupancy(TrainTicket.SeatSection.B);
    }

    // Helper method to initialize seat occupancy for a section
    private void initializeSeatOccupancy(TrainTicket.SeatSection section) {
        Map<String, Boolean> seatMap = seatOccupancy.get(section);
        for (int i = 1; i <= 50; i++) { // Assuming 50 seats per section
            seatMap.put(section.toString() + i, false); // Seat is initially vacant
        }
    }

    // API to purchase a ticket
    public TrainTicket purchaseTicket(String from, String to, User user, double price, TrainTicket.SeatSection seatSection) {
        TrainTicket ticket = new TrainTicket(from, to, user, price, seatSection);
        tickets.put(user.getEmail(), ticket);
        allocateSeat(user.getEmail(), seatSection);
        return ticket;
    }

    // API to show details of a receipt for a user
    public TrainTicket getReceipt(String userEmail) {
        return tickets.get(userEmail);
    }

    // Method to get users and their allocated seats by section
    public Map<String, String> getUsersBySection(TrainTicket.SeatSection seatSection) {
        Map<String, String> usersAndSeats = new HashMap<>();
        for (Map.Entry<String, String> entry : seatAllocations.entrySet()) {
            if (entry.getValue() != null && entry.getValue().startsWith(seatSection.toString())) {
                usersAndSeats.put(entry.getKey(), entry.getValue());
            }
        }
        return usersAndSeats;
    }

    // Method to remove a user and free their seat
    public void removeUser(String userEmail) {
        String seatNumber = seatAllocations.remove(userEmail);
        if (seatNumber != null) { // Check if user had a seat
            Map<String, Boolean> seatMap = getSeatMapBySeatNumber(seatNumber);
            seatMap.put(seatNumber, false); // Mark seat as vacant
        }
        tickets.remove(userEmail);
    }

    // Helper method to get seat map by seat number
    private Map<String, Boolean> getSeatMapBySeatNumber(String seatNumber) {
        TrainTicket.SeatSection section = seatNumber.charAt(0) == 'A' ? TrainTicket.SeatSection.A : TrainTicket.SeatSection.B;
        return seatOccupancy.get(section);
    }
    
    // Method to modify a user's seat
    public void modifyUserSeat(String userEmail, TrainTicket.SeatSection newSeatSection) {
        removeUser(userEmail); // Free current seat
        allocateSeat(userEmail, newSeatSection); // Allocate new seat
    }

    // Method to allocate a seat for a user
    private String allocateSeat(String userEmail, TrainTicket.SeatSection seatSection) {
        Map<String, Boolean> seatMap = seatOccupancy.get(seatSection);
        for (Map.Entry<String, Boolean> entry : seatMap.entrySet()) {
            if (!entry.getValue()) { // Check if seat is vacant
                entry.setValue(true); // Mark seat as occupied
                seatAllocations.put(userEmail, entry.getKey()); // Store seat allocation for user
                return "Seat allocated successfully: " + entry.getKey();
            }
        }
        // If no vacant seat found
        return "No vacant seat found in section " + seatSection;
    }
    
}
