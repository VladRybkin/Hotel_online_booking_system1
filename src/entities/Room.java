package entities;

import src.enums.RoomType;

import java.util.Currency;
import java.util.Random;

public class Room {

    private long id;
    private int roomNumber;
    private int price;
    private Currency currency;
    private int persons;
    private RoomType roomType;
    private User reservedForUser;

    //TODO поле Currency перенести из сущности Рум в сущность Хотел
    //поле hotel убрать, будем доставать обратно зная отель и перебирая его комнаты
    public Room(int number, int price, Currency currency, int persons, RoomType roomType) {
//        this.id = TextUtils.getLastId("Room"); TextUtils пока досвечивается красным
        this.roomNumber = number;
        this.price = price;
        this.currency = currency;
        this.persons = persons;
        this.roomType = roomType;
    }

    public Room(long id, int roomNumber, int price, Currency currency, int persons, RoomType roomType) {
        this.id = id;
        this.roomNumber = roomNumber;
        this.price = price;
        this.currency = currency;
        this.persons = persons;
        this.roomType = roomType;
    }

    //TODO добавить еще один конструктор но уже с возможностью передавать в него айдишник,
    //а не используя метод getLastId, чтобы создавать обьекты из под ДАО

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
    }

    public Currency getCurrency() {return currency;}

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
        if (roomType != room.roomType) return false;
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
//    @Override
//    public String toString() {
//        return  "" + hotel + ", " +"room " + "№" + roomNumber +
//                ", " + roomType.getTranslate() +
//                ", " + persons + " persons" +
//                ", price: " + price + currency.getName();
//    }

//  Под измененные поля
    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", roomNumber=" + roomNumber +
                ", price=" + price +
                ", persons=" + persons +
                ", roomType=" + roomType +
                ", reservedForUser=" + reservedForUser +
                '}';
    }
}
