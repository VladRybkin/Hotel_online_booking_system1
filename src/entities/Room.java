package entities;

import dao.Dao;
import dao.RoomDao;
import dao.UserDao;
import enums.RoomType;
import util.TextUtil;
import enums.Currency;


public class Room {

    private long id;
    private int roomNumber;
    private int price;
    private Currency currency;
    private int persons;
    private RoomType roomType;
    private User reservedForUser;
    private Hotel hotel;

    public Room(int roomNumber, int price, Currency currency, int persons, RoomType roomType, Hotel hotel) {
        this(TextUtil.getLastId(TextUtil.ROOM_FILE_NAME), roomNumber, price, currency, persons, roomType, hotel);
    }

    public Room(long id, int roomNumber, int price, Currency currency, int persons, RoomType roomType, Hotel hotel) {
        this.id = id;
        this.roomNumber = roomNumber;
        this.price = price;
        this.currency = currency;
        this.persons = persons;
        this.roomType = roomType;
        this.hotel = hotel;
    }

    public long getId() {
        return id;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public int getPrice() {
        return price;
    }

    public int getPersons() {
        return persons;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public User getReservedForUser() {
        return reservedForUser;
    }

    public void setReservedForUser(User reservedForUser) {
        this.reservedForUser = reservedForUser;
        Dao<Room> roomDao = new RoomDao();
        roomDao.update(this);
    }

    public Currency getCurrency() {
        return currency;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public boolean isReserved() {
        if (reservedForUser != null) {
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Room room = (Room) o;

        if (id != room.id) return false;
        if (roomNumber != room.roomNumber) return false;
        if (price != room.price) return false;
        if (persons != room.persons) return false;
        if (!currency.equals(room.currency)) return false;
        if (!roomType.equals(room.roomType)) return false;
        return reservedForUser.equals(room.reservedForUser);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + roomNumber;
        result = 31 * result + price;
        result = 31 * result + currency.hashCode();
        result = 31 * result + persons;
        result = 31 * result + roomType.hashCode();
        result = 31 * result + reservedForUser.hashCode();
        return result;
    }

//    Под старое поле
    @Override
    public String toString() {
        return  "" + hotel + ", " +
                "room " + "№" + roomNumber +
                ", " + roomType.getTranslate() +
                ", " + persons + " persons" +
                ", price: " + price + currency.getName();
    }

    //  Под измененные поля
//    @Override
//    public String toString() {
//        return "Room{" +
//                "id=" + id +
//                ", roomNumber=" + roomNumber +
//                ", price=" + price +
//                ", persons=" + persons +
//                ", roomType=" + roomType +
//                ", reservedForUser=" + reservedForUser +
//                '}';
//    }
}
