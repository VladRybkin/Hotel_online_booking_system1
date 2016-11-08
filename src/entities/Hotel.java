package entities;

import dao.Dao;
import dao.RoomDao;
import util.TextUtil;

import java.util.List;
import java.util.stream.Collectors;

public class Hotel {

    private long id;
    private String name;
    private String city;

    public Hotel(String name, String city) {
        this(TextUtil.getLastId(TextUtil.HOTEL_FILE_NAME), name, city);
    }

    public Hotel(long id, String name, String city) throws Error{
        this.id = id;
        this.name = name;
        this.city = city;
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
        Dao<Room> roomDao = new RoomDao();
        try {
            return roomDao.getAll().stream().filter(room -> room.getHotel().getId() == id).collect(Collectors.toList());
        } catch (NullPointerException e) {
            return null;
        }
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