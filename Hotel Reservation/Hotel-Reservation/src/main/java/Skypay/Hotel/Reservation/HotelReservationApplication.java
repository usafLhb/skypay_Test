package Skypay.Hotel.Reservation;

import Skypay.Hotel.Reservation.entity.RoomType;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@SpringBootApplication
public class HotelReservationApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotelReservationApplication.class, args);
	Service service = new Service();

        service.setRoom(1, RoomType.STANDARD, 1000);
        service.setRoom(2, RoomType.JUNIOR, 2000);
        service.setRoom(3, RoomType.SUITE, 3000);

        service.setUser(1, 5000);
        service.setUser(2, 10000);

	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        service.bookRoom(1, 2, LocalDate.parse("30/06/2026", formatter), LocalDate.parse("07/07/2026", formatter));

        service.bookRoom(1, 2, LocalDate.parse("07/07/2026", formatter), LocalDate.parse("30/06/2026", formatter));

        service.bookRoom(1, 1, LocalDate.parse("07/07/2026", formatter), LocalDate.parse("08/07/2026", formatter));

        service.bookRoom(2, 1, LocalDate.parse("07/07/2026", formatter), LocalDate.parse("09/07/2026", formatter));

        service.bookRoom(2, 3, LocalDate.parse("07/07/2026", formatter), LocalDate.parse("08/07/2026", formatter));

	// Test setRoom avec modif non impactante
        service.setRoom(1, RoomType.SUITE, 10000);

        service.printAllUsers();
        service.printAll();
}

}