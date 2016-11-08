import com.sun.xml.internal.bind.v2.util.CollisionCheckStack;
import dao.Dao;
import dao.HotelDao;
import dao.RoomDao;
import dao.UserDao;
import entities.Hotel;
import entities.Room;
import entities.User;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Controller {

    private Dao<Hotel> hotelDao;
    private Dao<Room> roomDao;
    private Dao<User> userDao;

    public Controller() {
        this.hotelDao = new HotelDao();
        this.roomDao = new RoomDao();
        this.userDao = new UserDao();
    }

    public List<Room> getAllNotReservedRooms() {
        List<Room> rooms = roomDao.getAll();
        return rooms.stream().filter(room -> room.isReserved() == false).collect(Collectors.toList());
    }
}
