package Skypay.Hotel.Reservation;

import Skypay.Hotel.Reservation.entity.Booking;
import Skypay.Hotel.Reservation.entity.Room;
import Skypay.Hotel.Reservation.entity.RoomType;
import Skypay.Hotel.Reservation.entity.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Service {
    private final List<Room> rooms = new ArrayList<>();
    private final List<User> users = new ArrayList<>();
    private final List<Booking> bookings = new ArrayList<>();

    public void setRoom(int roomNumber, RoomType roomType, int roomPricePerNight) {
        // Vérifie si la chambre existe déjà par numéro
        for (Room r : rooms) {
            if (r.getRoomNumber() == roomNumber) {
                // Ne modifie pas les chambres existantes (pas d'impact sur les anciennes réservations)
                return;
            }
        }
        rooms.add(new Room(roomNumber, roomType, roomPricePerNight));
    }

    public void setUser(int userId, int balance) {
        for (User u : users) {
            if (u.getUserId() == userId) {
                return;
            }
        }
        users.add(new User(userId, balance));
    }

    public void bookRoom(int userId, int roomNumber, LocalDate checkIn, LocalDate checkOut) {
        User user = users.stream().filter(u -> u.getUserId() == userId).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("User introuvable"));
        Room room = rooms.stream().filter(r -> r.getRoomNumber() == roomNumber).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Room introuvable"));

        if (checkOut.isBefore(checkIn) || checkOut.equals(checkIn)) {
            System.out.println("Erreur : check-out avant ou égal à check-in");
            return;
        }

        // Vérifier si la chambre est libre sur la période
        for (Booking b : bookings) {
            if (b.getRoom().getRoomNumber() == roomNumber && b.overlaps(checkIn, checkOut)) {
                System.out.println("Erreur : chambre déjà réservée sur cette période");
                return;
            }
        }

        int nights = (int) (checkOut.toEpochDay() - checkIn.toEpochDay());
        int totalPrice = nights * room.getPricePerNight();

        if (user.getBalance() < totalPrice) {
            System.out.println("Erreur : solde insuffisant pour cette réservation");
            return;
        }

        // Tout ok, déduire le solde
        user.debit(totalPrice);

        Booking booking = new Booking(user, room, checkIn, checkOut);
        bookings.add(booking);

        System.out.println("Réservation confirmée : " + booking);
    }

    public void printAll() {
        System.out.println("=== Toutes les chambres et réservations (du plus récent au plus ancien) ===");
        // Imprimer chambres (du plus récent au plus ancien)
        for (int i = rooms.size() - 1; i >= 0; i--) {
            System.out.println(rooms.get(i));
        }
        // Imprimer réservations (du plus récent au plus ancien)
        for (int i = bookings.size() - 1; i >= 0; i--) {
            System.out.println(bookings.get(i));
        }
    }

    public void printAllUsers() {
        System.out.println("=== Tous les utilisateurs (du plus récent au plus ancien) ===");
        for (int i = users.size() - 1; i >= 0; i--) {
            System.out.println(users.get(i));
        }
    }
}
