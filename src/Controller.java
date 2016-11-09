import com.sun.xml.internal.bind.v2.util.CollisionCheckStack;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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

    public List<Hotel> findHotelByCity(String city) {
        if (!isUserRegistered()) {
            return new ArrayList<>();
        }
        List<Hotel> hotels = hotelDao.getAll();
        List<Hotel> hotelsByCity = hotels.stream().filter(hotel -> hotel.getCity().equals(city)).collect(Collectors.toList());
        if (hotelsByCity == null) {
            return new ArrayList<>();
        }
        if (hotelsByCity.size() == 0) {
            System.out.println("There is no hotels in city: \'" + city + "\'");
        }
        return hotelsByCity;
    }

    public List<Hotel> findHotelByName(String name) {
        if (!isUserRegistered()) {
            System.out.println("There is no hotels with name: \'" + name + "\'");
            return new ArrayList<>();
        }

        List<Hotel> hotels = hotelDao.getAll();
        List<Hotel> hotelsByName = hotels.stream().filter(hotel -> hotel.getName().equals(name)).collect(Collectors.toList());
        if (hotelsByName == null) {
            return new ArrayList<>();
        }
        if (hotelsByName.size() == 0) {
            System.out.println("There is no hotels with name: \'" + name + "\'");
        }
        return hotelsByName;
    }

    public void bookRoom(long roomId, long userId, long hotelId) {

        if (!isUserRegistered()) {
            return;
        }
        Hotel hotel = hotelDao.findByID(hotelId);
        if (hotel == null) {
            System.out.println("Hotel with Id " + hotelId + " not found.  Room did not book");
            return;
        }
        Room room = roomDao.findByID(roomId);
        if (room == null) {
            System.out.println("Room with Id " + roomId + " not found.  Room did not book");
            return;
        }
        User user = userDao.findByID(userId);
        if (user == null) {
            System.out.println("User with Id " + userId + " not found. Room did not book");
            return;
        }
        room.setReservedForUser(user);
        System.out.println(room + " successfully booked" + " for " + user);
    }

    public void cancelReservation(long roomId, long userId, long hotelId) {
        if (!isUserRegistered()) {
            return;
        }
        Hotel hotel = hotelDao.findByID(hotelId);
        if (hotel == null) {
            System.out.println("Hotel with Id " + hotelId + " not found.");
            return;
        }
        Room room = roomDao.findByID(roomId);
        if (room == null) {
            System.out.println("Room with Id " + roomId + " not found.");
            return;
        }
        User user = userDao.findByID(userId);
        if (user == null) {
            System.out.println("User with Id " + userId + " not found.");
            return;
        }
        if (user.equals(room.getReservedForUser())) {
            room.setReservedForUser(null);
            System.out.println("Reservation successfully canceled for " + room);
        }
    }

    public List<Room> getAllNotReservedRooms() {
        if (!isUserRegistered()) {
            return new ArrayList<>();
        }
        List<Room> rooms = roomDao.getAll();
        return rooms.stream().filter(room -> !room.isReserved()).collect(Collectors.toList());
    }

    public List<Room> findRoom(Map<String, String> params) {
        if (!isUserRegistered()) {
            return new ArrayList<>();
        }
        if (params == null) {
            System.out.println("Nothing found");
            return new ArrayList<>();
        }
        List<Room> foundRooms = new ArrayList<>();
        List<Room> allNotReservedRooms = getAllNotReservedRooms();

        for (Room room : allNotReservedRooms) {
            boolean roomIsFound = checkParams(params, room);
            if (roomIsFound) {
                foundRooms.add(room);
            }
        }
        if (foundRooms.size() == 0) {
            System.out.println("Nothing found");
        }
        return foundRooms;
    }

    private boolean checkParams(Map<String, String> params, Room room) {
        List<Boolean> flags = new ArrayList<>();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (entry.getKey().equals("id")) {
                long id = Long.parseLong(entry.getValue());
                if (id == room.getId()) {
                    flags.add(true);
                } else {
                    flags.add(false);
                }
            }
            if (entry.getKey().equals("number")) {
                int number = Integer.parseInt(entry.getValue());
                if (number == room.getRoomNumber()) {
                    flags.add(true);
                } else {
                    flags.add(false);
                }
            }
            if (entry.getKey().equals("price")) {
                int price = Integer.parseInt(entry.getValue());
                if (price == room.getPrice()) {
                    flags.add(true);
                } else {
                    flags.add(false);
                }
            }
            if (entry.getKey().equals("currency")) {
                try {

                    Currency currency = Currency.valueOf(entry.getValue());
                    if (currency.equals(room.getCurrency())) {
                        flags.add(true);
                    } else {
                        flags.add(false);
                    }
                } catch (IllegalArgumentException e) {
                    flags.add(false);
                }
            }
            if (entry.getKey().equals("persons")) {
                int persons = Integer.parseInt(entry.getValue());
                if (persons == room.getPersons()) {
                    flags.add(true);
                } else {
                    flags.add(false);
                }
            }
            if (entry.getKey().equals("roomType")) {
                try {
                    RoomType roomType = RoomType.valueOf(entry.getValue());
                    if (roomType.equals(room.getRoomType())) {
                        flags.add(true);
                    } else {
                        flags.add(false);
                    }
                } catch (IllegalArgumentException e) {
                    flags.add(false);
                }
            }
            if (entry.getKey().equals("hotel")) {
                Hotel hotel = findHotelByName(entry.getValue()).stream().findFirst().orElse(null);
                if (hotel.equals(room.getHotel())) {
                    flags.add(true);
                } else {
                    flags.add(false);
                }
            }
        }
        if (flags.size() > 0) {
            return flags.stream().allMatch(flag -> flag == true);
        } else {
            return false;
        }
    }

    public User findUserByName(String fullName) {
        User user = userDao.getAll().stream().filter(user1 -> user1.getFullName().equals(fullName)).findFirst().orElse(null);
        if (user == null) {
            System.out.println("User with fullName: " + fullName + " not found");
        }
        return user;
    }

    public void registerUser(User user) {
        if (user == null) {
            System.out.println("System can't register user, User is null");
            return;
        }
        CurrentUser.setCurrentUser(user);
    }

    private boolean isUserRegistered() {
        if (CurrentUser.getCurrentUser() == null) {
            System.out.println("User not registered");
            return false;
        } else {
            return true;
        }
    }
}

