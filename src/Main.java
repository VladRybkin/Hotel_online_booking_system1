import dao.HotelDao;
import entities.Hotel;
import enums.Currency;

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

        hotelDao.add(new Hotel("ПРЕМЬЕР ПАЛАС", "Kiev"));
        hotelDao.add(new Hotel("Космополит", "Kharkiv"));

        List<Hotel> hotels = hotelDao.getAll();
        System.out.println(hotels);
//        Hotel hotel2 = (new Hotel("ОТЕЛЬ ХАЯТТ", "Kiev"));
//        Hotel hotel3 = (new Hotel("Космополит", "Kharkiv"));
//        Hotel hotel4 = (new Hotel("Гостинный двор", "Kharkiv"));
//        Hotel hotel5 = (new Hotel("Astoria", "Lviv"));
//        Hotel hotel6 = (new Hotel("Nobilis", "Lviv"));
    }
}
