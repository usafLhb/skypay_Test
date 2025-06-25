package Skypay.Hotel.Reservation.entity;

import java.time.LocalDate;

public class Booking {
    private final User user;
    private final Room room;
    private final LocalDate checkIn;
    private final LocalDate checkOut;

    public Booking(User user, Room room, LocalDate checkIn, LocalDate checkOut) {
        if (checkOut.isBefore(checkIn) || checkOut.equals(checkIn)) {
            throw new IllegalArgumentException("Date de départ doit être après la date d'arrivée");
        }
        this.user = user;
        this.room = room;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public User getUser() {
        return user;
    }

    public Room getRoom() {
        return room;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    // Vérifie si cette réservation chevauche une autre période
    public boolean overlaps(LocalDate start, LocalDate end) {
        return !(checkOut.isBefore(start) || checkIn.isAfter(end) || checkOut.equals(start) || checkIn.equals(end));
    }

    public int getNumberOfNights() {
        return (int) (checkOut.toEpochDay() - checkIn.toEpochDay());
    }

    @Override
    public String toString() {
        return "Booking{" +
                "user=" + user +
                ", room=" + room +
                ", checkIn=" + checkIn +
                ", checkOut=" + checkOut +
                '}';
    }
}
