package Skypay.Hotel.Reservation.entity;

public class Room {
    private final int roomNumber;
    private final RoomType roomType;
    private final int pricePerNight;

    public Room(int roomNumber, RoomType roomType, int pricePerNight) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.pricePerNight = pricePerNight;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public int getPricePerNight() {
        return pricePerNight;
    }

    @Override
    public String toString() {
        return "Room{" + "number=" + roomNumber + ", type=" + roomType + ", pricePerNight=" + pricePerNight + '}';
    }
}
