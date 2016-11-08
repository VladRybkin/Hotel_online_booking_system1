package entities;

import util.TextUtil;
import enums.Currency;

import java.util.ArrayList;
import java.util.List;

public class Hotel {

    private long id;
    private String name;
    private String city;
    private final List<Room> rooms;

    public Hotel(String name, String city, List<Room> rooms) {
        this(TextUtil.getLastId(TextUtil.HOTEL_FILE_NAME), name, city, rooms);
    }

    public Hotel(long id, String name, String city, List<Room> rooms) throws Error{
        this.id = id;
        this.name = name;
        this.city = city;
        try {
            rooms.forEach(room -> room.setHotel(this));
        } catch (Error e) {
            System.out.println("Hotel is not create becouse" + e.getMessage());
            throw new Error(e);
        }
        this.rooms = rooms;
    }


    public Room findRoomById(long id) {
        Room roomIsfound = null;
        try {

//            roomIsfound = rooms.stream().filter(room -> room.getId() == id).findAny().orElse(null); стрим подсвечен красным
        } catch (NullPointerException e) {
            System.out.println("This room doesn't exist in the list of hotel rooms");
        }
        return roomIsfound;
    }

    public void addRoom(Room room) {
        this.rooms.add(room);
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Hotel hotel = (Hotel) o;

        if (name != null ? !name.equals(hotel.name) : hotel.name != null) return false;
        return city != null ? city.equals(hotel.city) : hotel.city == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (city != null ? city.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Hotel \"" + name + "\" in city " + city;
    }
}