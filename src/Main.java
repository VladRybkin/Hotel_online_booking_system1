import dao.Dao;
import dao.HotelDao;
import dao.RoomDao;
import entities.Hotel;
import entities.Room;
import enums.Currency;
import enums.RoomType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Администратор on 08.11.2016.
 */
public class Main {
    public static void main(String[] args) {

        HotelDao hotelDao = new HotelDao();
        dataInitializer(hotelDao);

    }

    private static void dataInitializer(HotelDao hotelDao) {

        Room room1 = new Room(3, 200, Currency.UAH, 2, RoomType.Standard);
        Room room2 = new Room(3, 400, Currency.UAH, 2, RoomType.Econom);
        List<Room> rooms = new ArrayList<>();
        rooms.add(room1);
        rooms.add(room2);
        Dao<Room> roomDao = new RoomDao();
        roomDao.add(room1);
        roomDao.add(room2);

        hotelDao.add(new Hotel("ПРЕМЬЕР ПАЛАС", "Kiev", rooms));
//        hotelDao.add(new Hotel("Космополит", "Kharkiv", rooms));

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
