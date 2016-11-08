import dao.Dao;
import dao.HotelDao;
import dao.RoomDao;
import dao.UserDao;
import entities.Hotel;
import entities.Room;
import entities.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Controller {

    private static Dao<Hotel> hotelDao;
    private static Dao<Room> roomDao;
    private static Dao<User> userDao;

    public Controller() {
        this.hotelDao = new HotelDao();
        this.roomDao = new RoomDao();
        this.userDao = new UserDao();
    }

    public List<Hotel> findByCity(String city) {
        List<Hotel> hotels = hotelDao.getAll();
        ArrayList<Hotel> citiesMatchingList = new ArrayList<>();
        for (Hotel hotel : hotels) {
            if (city.equals(hotel.getCity())) {
                citiesMatchingList.add(hotel);
            }
        }
        return citiesMatchingList;
    }

    public List<Hotel> findByName(String name) {
        List<Hotel> hotels = hotelDao.getAll();
        ArrayList<Hotel> namesMatchingList = new ArrayList<>();
        for (Hotel hotel : hotels) {
            if (name.equals(hotel.getName())) {
                namesMatchingList.add(hotel);
            }
        }
        return namesMatchingList;
    }

    public List<Room> getAllNotReservedRooms() {
        List<Room> rooms = roomDao.getAll();
        return rooms.stream().filter(room -> room.isReserved() == false).collect(Collectors.toList());
    }
}
