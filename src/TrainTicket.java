public class TrainTicket {
    private String from;
    private String to;
    private User user;
    private double price;
    private SeatSection seatSection; // Using enum for seat section


    public TrainTicket(String from, String to, User user, double price, SeatSection seatSection) {
        this.from = from;
        this.to = to;
        this.user = user;
        this.price = price;
        this.seatSection = seatSection;
    }

    // Getters and setters
    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public SeatSection getSeatSection() {
        return seatSection;
    }

    public void setSeatSection(SeatSection seatSection) {
        this.seatSection = seatSection;
    }

    public enum SeatSection {
        A, B // Enum constants representing the two sections
    }
}
