import dao.Dao;
import dao.HotelDao;
import dao.RoomDao;
import entities.Hotel;
import entities.Room;
import enums.Currency;
import enums.RoomType;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        dataInitializer();
    }

    private static void dataInitializer() {
        HotelDao hotelDao = new HotelDao();
        Dao<Room> roomDao = new RoomDao();

        Hotel hotel1 = new Hotel("ПРЕМЬЕР ПАЛАС", "Kiev");
        hotelDao.add(hotel1);

        Room room1 = new Room(3, 200, Currency.UAH, 2, RoomType.Standard, hotel1);
        Room room2 = new Room(3, 400, Currency.UAH, 2, RoomType.Econom, hotel1);
        roomDao.add(room1);
        roomDao.add(room2);


//        hotelDao.add(new Hotel("Космополит", "Kharkiv"));

        List<Hotel> hotels = hotelDao.getAll();

        hotels.forEach(System.out::println);
        hotels.forEach(hotel -> hotel.getRooms().forEach(System.out::println));


//        Hotel hotel2 = (new Hotel("ОТЕЛЬ ХАЯТТ", "Kiev"));
//        Hotel hotel3 = (new Hotel("Космополит", "Kharkiv"));
//        Hotel hotel4 = (new Hotel("Гостинный двор", "Kharkiv"));
//        Hotel hotel5 = (new Hotel("Astoria", "Lviv"));
//        Hotel hotel6 = (new Hotel("Nobilis", "Lviv"));
    }
}
