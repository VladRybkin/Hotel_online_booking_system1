import dao.Dao;
import dao.HotelDao;
import dao.RoomDao;
import entities.Hotel;
import entities.Room;
import entities.User;
import enums.Currency;
import enums.RoomType;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        dataInitializer();

        User user1 = new User("Mark", "555-55-55", "mark@mail.com");
        User user2 = new User("Stiv", "222-22-22", "stiv@mail.com");
        User user3 = new User("Bill", "333-33-33", "bill@mail.com");

        Controller controller = new Controller();

        controller.addUser(user1);
        controller.addUser(user2);
        controller.addUser(user3);

        controller.registerUser(user1);

        controller.getAllNotReservedRooms().forEach(System.out::println);
    }

    private static void dataInitializer() {

        try (BufferedReader brHotel = new BufferedReader(new FileReader("Hotel"));
             BufferedReader brRoom = new BufferedReader(new FileReader("Room"))){
        } catch (IOException e) {

            HotelDao hotelDao = new HotelDao();
            Dao<Room> roomDao = new RoomDao();

            Hotel hotel1 = (new Hotel("ПРЕМЬЕР ПАЛАС", "Kiev"));
            Hotel hotel2 = (new Hotel("ОТЕЛЬ ХАЯТТ", "Kiev"));
            Hotel hotel3 = (new Hotel("Космополит", "Kharkiv"));
            Hotel hotel4 = (new Hotel("Гостинный двор", "Kharkiv"));
            Hotel hotel5 = (new Hotel("Astoria", "Lviv"));
            Hotel hotel6 = (new Hotel("Nobilis", "Lviv"));

            hotelDao.add(hotel1);
            hotelDao.add(hotel2);
            hotelDao.add(hotel3);
            hotelDao.add(hotel4);
            hotelDao.add(hotel5);
            hotelDao.add(hotel6);

            roomDao.add(new Room(1, 2500, Currency.UAH, 1, RoomType.Lux, hotel1));
            roomDao.add(new Room(2, 3300, Currency.UAH, 2, RoomType.Lux, hotel1));
            roomDao.add(new Room(3, 1500, Currency.UAH, 1, RoomType.Standard, hotel1));
            roomDao.add(new Room(4, 1800, Currency.UAH, 2, RoomType.Standard, hotel1));
            roomDao.add(new Room(5, 2200, Currency.UAH, 3, RoomType.Standard, hotel1));
            roomDao.add(new Room(6, 1000, Currency.UAH, 1, RoomType.Econom, hotel1));
            roomDao.add(new Room(7, 1300, Currency.UAH, 2, RoomType.Econom, hotel1));
            roomDao.add(new Room(8, 1300, Currency.UAH, 2, RoomType.Econom, hotel1));
            roomDao.add(new Room(9, 1600, Currency.UAH, 3, RoomType.Econom, hotel1));
            roomDao.add(new Room(10, 1600, Currency.UAH, 3, RoomType.Econom, hotel1));

            roomDao.add(new Room(1, 2500, Currency.USD, 2, RoomType.Lux, hotel2));
            roomDao.add(new Room(2, 2500, Currency.USD, 2, RoomType.Lux, hotel2));
            roomDao.add(new Room(3, 2500, Currency.USD, 2, RoomType.Lux, hotel2));
            roomDao.add(new Room(4, 2500, Currency.USD, 2, RoomType.Lux, hotel2));
            roomDao.add(new Room(5, 2500, Currency.USD, 2, RoomType.Lux, hotel2));
            roomDao.add(new Room(6, 2500, Currency.USD, 2, RoomType.Lux, hotel2));
            roomDao.add(new Room(7, 2500, Currency.USD, 2, RoomType.Lux, hotel2));
            roomDao.add(new Room(8, 2500, Currency.USD, 2, RoomType.Lux, hotel2));
            roomDao.add(new Room(9, 2500, Currency.USD, 2, RoomType.Lux, hotel2));
            roomDao.add(new Room(10, 2500, Currency.USD, 2, RoomType.Lux, hotel2));
            roomDao.add(new Room(11, 2500, Currency.USD, 2, RoomType.Lux, hotel2));

            roomDao.add(new Room(1, 2500, Currency.UAH, 2, RoomType.Lux, hotel3));
            roomDao.add(new Room(2, 2500, Currency.UAH, 2, RoomType.Lux, hotel3));
            roomDao.add(new Room(3, 2500, Currency.UAH, 2, RoomType.Lux, hotel3));
            roomDao.add(new Room(4, 2500, Currency.UAH, 2, RoomType.Lux, hotel3));
            roomDao.add(new Room(5, 2500, Currency.UAH, 2, RoomType.Lux, hotel3));
            roomDao.add(new Room(6, 2500, Currency.UAH, 2, RoomType.Lux, hotel3));
            roomDao.add(new Room(7, 2500, Currency.UAH, 2, RoomType.Lux, hotel3));
            roomDao.add(new Room(8, 2500, Currency.UAH, 2, RoomType.Lux, hotel3));
            roomDao.add(new Room(9, 2500, Currency.UAH, 2, RoomType.Lux, hotel3));
            roomDao.add(new Room(10, 2500, Currency.UAH, 2, RoomType.Lux, hotel3));
            roomDao.add(new Room(11, 2500, Currency.UAH, 2, RoomType.Lux, hotel3));
            roomDao.add(new Room(12, 2500, Currency.UAH, 2, RoomType.Lux, hotel3));

            roomDao.add(new Room(1, 2500, Currency.USD, 2, RoomType.Lux, hotel4));
            roomDao.add(new Room(2, 2500, Currency.USD, 2, RoomType.Lux, hotel4));
            roomDao.add(new Room(3, 2500, Currency.USD, 2, RoomType.Lux, hotel4));
            roomDao.add(new Room(4, 2500, Currency.USD, 2, RoomType.Lux, hotel4));
            roomDao.add(new Room(5, 2500, Currency.USD, 2, RoomType.Lux, hotel4));
            roomDao.add(new Room(6, 2500, Currency.USD, 2, RoomType.Lux, hotel4));
            roomDao.add(new Room(7, 2500, Currency.USD, 2, RoomType.Lux, hotel4));
            roomDao.add(new Room(8, 2500, Currency.USD, 2, RoomType.Lux, hotel4));
            roomDao.add(new Room(9, 2500, Currency.USD, 2, RoomType.Lux, hotel4));
            roomDao.add(new Room(10, 2500, Currency.USD, 2, RoomType.Lux, hotel4));

            roomDao.add(new Room(1, 2500, Currency.UAH, 2, RoomType.Lux, hotel5));
            roomDao.add(new Room(2, 2500, Currency.UAH, 2, RoomType.Lux, hotel5));
            roomDao.add(new Room(3, 2500, Currency.UAH, 2, RoomType.Lux, hotel5));
            roomDao.add(new Room(4, 2500, Currency.UAH, 2, RoomType.Lux, hotel5));
            roomDao.add(new Room(5, 2500, Currency.UAH, 2, RoomType.Lux, hotel5));
            roomDao.add(new Room(6, 2500, Currency.UAH, 2, RoomType.Lux, hotel5));
            roomDao.add(new Room(7, 2500, Currency.UAH, 2, RoomType.Lux, hotel5));
            roomDao.add(new Room(8, 2500, Currency.UAH, 2, RoomType.Lux, hotel5));
            roomDao.add(new Room(9, 2500, Currency.UAH, 2, RoomType.Lux, hotel5));
            roomDao.add(new Room(10, 2500, Currency.UAH, 2, RoomType.Lux, hotel5));
            roomDao.add(new Room(11, 2500, Currency.UAH, 2, RoomType.Lux, hotel5));

            roomDao.add(new Room(1, 2500, Currency.UAH, 2, RoomType.Lux, hotel6));
            roomDao.add(new Room(2, 2500, Currency.UAH, 2, RoomType.Lux, hotel6));
            roomDao.add(new Room(3, 2500, Currency.UAH, 2, RoomType.Lux, hotel6));
            roomDao.add(new Room(4, 2500, Currency.UAH, 2, RoomType.Lux, hotel6));
            roomDao.add(new Room(5, 2500, Currency.UAH, 2, RoomType.Lux, hotel6));
            roomDao.add(new Room(6, 2500, Currency.UAH, 2, RoomType.Lux, hotel6));
            roomDao.add(new Room(7, 2500, Currency.UAH, 2, RoomType.Lux, hotel6));
            roomDao.add(new Room(8, 2500, Currency.UAH, 2, RoomType.Lux, hotel6));
            roomDao.add(new Room(9, 2500, Currency.UAH, 2, RoomType.Lux, hotel6));
            roomDao.add(new Room(10, 2500, Currency.UAH, 2, RoomType.Lux, hotel6));
            roomDao.add(new Room(11, 2500, Currency.UAH, 2, RoomType.Lux, hotel6));
            roomDao.add(new Room(12, 2500, Currency.UAH, 2, RoomType.Lux, hotel6));
        }
//        List<Hotel> hotels = hotelDao.getAll();
//
//        hotels.forEach(System.out::println);
//        hotels.forEach(hotel -> hotel.getRooms().forEach(System.out::println));
    }
}
