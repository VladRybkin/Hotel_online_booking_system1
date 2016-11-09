import dao.Dao;
import dao.HotelDao;
import dao.RoomDao;
import dao.UserDao;
import entities.CurrentUser;
import entities.Hotel;
import entities.Room;
import entities.User;
import enums.Currency;
import enums.RoomType;
import util.TextUtil;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        dataInitializer();

        Controller controller = new Controller();

        System.out.println("\nTrying to search with anonymous user:");
        controller.findHotelByCity("Kiev");
        System.out.println("\n----------------------------------------------\n");

        User user = controller.findUserByName("Mark");
        controller.registerUser(user);

        System.out.println("Empty rooms in city Kiev:");
        List<Hotel> hotels = controller.findHotelByCity("Kiev");
        for (Hotel hotel : hotels) {
            hotel.getRooms().stream().filter((room -> room.getReservedForUser() == null)).forEach(System.out::println);
        }
        System.out.println("\n----------------------------------------------\n");

        System.out.println("Empty rooms in hotel ПРЕМЬЕР ПАЛАС:");
        List<Hotel> hotels2 = controller.findHotelByName("ПРЕМЬЕР ПАЛАС");
        for (Hotel hotel : hotels2) {
            hotel.getRooms().stream().filter((room -> room.getReservedForUser() == null)).forEach(System.out::println);
        }
        System.out.println("\n----------------------------------------------\n");

//        ---------------------------------------------------------------------------------------------


        System.out.println("Book room by id`s:");
        try {
            Hotel hotel11 = null;
            User user11 = null;
            Room room11 = null;
            try {
                hotel11 = controller.findHotelByName("ПРЕМЬЕР ПАЛАС").stream().findFirst().orElse(null);
            } catch (NullPointerException e) {
                System.out.println("Hotel not found, booking impossible");
                throw e;
            }
            try{
                user11 = CurrentUser.getCurrentUser();
            } catch (NullPointerException e) {
                System.out.println("User not found, booking impossible");
                throw e;
            }
            try {
                room11 = hotel11.getRooms().stream().filter(room -> !room.isReserved()).findFirst().orElse(null);
            } catch (NullPointerException e) {
                System.out.println("Room not found, booking impossible");
                throw e;
            }
            controller.bookRoom(room11.getId(), user11.getId(), hotel11.getId());
            System.out.println("\n----------------------------------------------\n");

            System.out.println("Cancel reservation id`s:");
            controller.cancelReservation(room11.getId(), user11.getId(), hotel11.getId());
            System.out.println("\n----------------------------------------------\n");

        } catch (NullPointerException e) {

        }

        System.out.println("Find rooms by different parameters:");
        Map<String, String> param = new HashMap<>();
//        param.put("roomNumber", "4");
//        param.put("price", "1800");
        param.put("currency", "UAH");
        param.put("persons", "2");
        param.put("roomType", "Econom");
//        param.put("hotel", "ПРЕМЬЕР ПАЛАС");
        param.put("city", "Kiev");
//        param.put("id", "0");
        param.put("country", "");

        List<Room> foundRooms = controller.findRoom(param);
        foundRooms.stream().forEach(System.out::println);
        System.out.println("\n----------------------------------------------\n");
        System.out.println("\n----------------------------------------------\n");
        System.out.println("\n----------------------------------------------\n");

        System.out.println("Wrong input data tests");
        User userWrong = controller.findUserByName("Mark22");


    }

    private static void dataInitializer() {

        try (BufferedReader brHotel = new BufferedReader(new FileReader(TextUtil.HOTEL_FILE_NAME));
             BufferedReader brRoom = new BufferedReader(new FileReader(TextUtil.ROOM_FILE_NAME));
             BufferedReader brUser = new BufferedReader(new FileReader(TextUtil.USER_FILE_NAME))){
        } catch (IOException e) {

            Dao hotelDao = new HotelDao();
            Dao<Room> roomDao = new RoomDao();
            Dao<User> userDao = new UserDao();


            Hotel hotel1 = new Hotel("ПРЕМЬЕР ПАЛАС", "Kiev");
            Hotel hotel2 = new Hotel("ОТЕЛЬ ХАЯТТ", "Kiev");
            Hotel hotel3 = new Hotel("Космополит", "Kharkiv");
            Hotel hotel4 = new Hotel("Гостинный двор", "Kharkiv");
            Hotel hotel5 = new Hotel("Astoria", "Lviv");
            Hotel hotel6 = new Hotel("Nobilis", "Lviv");

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

            userDao.add(new User("Mark", "555-55-55", "mark@mail.com"));
            userDao.add(new User("Stiv", "222-22-22", "stiv@mail.com"));
            userDao.add(new User("Bill", "333-33-33", "bill@mail.com"));
        }
    }
}
