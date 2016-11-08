package dao;

import entities.Hotel;
import entities.Room;
import util.TextUtil;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

public class HotelDao implements Dao<Hotel> {

    @Override
    public void add(Hotel hotel) {
        TextUtil.writeToFile(TextUtil.HOTEL_FILE_NAME, hotelToLine(hotel));
    }

    @Override
    public void update(Hotel hotel) {
        TextUtil.updateInFile(TextUtil.HOTEL_FILE_NAME, hotelToLine(hotel));
    }

    @Override
    public void delete(long id) {
//        TextUtil.deleteFromFile("Hotel", id);
        TextUtil.deleteFromFile(TextUtil.HOTEL_FILE_NAME, id);
    }

    @Override
    public Hotel findByID(long id) {
        List<Hotel> hotels = getAll();
        return hotels.stream().filter(hotel -> hotel.getId() == id).findFirst().orElse(null);
    }

    @Override
    public List<Hotel> getAll() {
        List<String> lines = TextUtil.readFromFile(TextUtil.HOTEL_FILE_NAME);
        List<Hotel> hotels = new ArrayList<>();

        for (String line : lines) {
            Hotel hotel = lineToHotel(line);
            if (hotel == null) {
                continue;
            }
            hotels.add(hotel);
        }
        return hotels;
    }

    public List<Hotel> findByName(String name) {

        List<Hotel> hotels = getAll();
        ArrayList<Hotel> duplicatedNames = new ArrayList<Hotel>();

        for (Hotel hotel : hotels) {

            if (name.equals(hotel.getName())) {
                duplicatedNames.add(hotel);
            }
        }
        //TODO Перебрать все записи Листа, сравнивая имя, совпадения записать в эрейлист и вывести.

        return hotels;
    }


    public List<Hotel> findByCity(String city) {

        List<Hotel> hotels = getAll();
        ArrayList<Hotel> duplicatedCities = new ArrayList<Hotel>();

        for (Hotel hotel : hotels) {

            if (city.equals(hotel.getCity())) {
                duplicatedCities.add(hotel);
            }
        }

        //TODO Перебрать все записи Листа, сравнивая город, совпадения записать в эрейлист и вывести.

        return hotels;
    }


    public List<Room> getAllNotReservedRooms() {
        List<Hotel> hotels = getAll();

        //TODO взять лист комнат. Перебрать все записи листа комнат, проверяя reservedForUser на null
        // совпадения записать в новый лист и вывести.

        return null;
    }

    private String hotelToLine(Hotel hotel) {
        StringBuffer stringHotel = new StringBuffer();

        stringHotel.append(hotel.getId()).append(TextUtil.getSeparator());
        stringHotel.append(hotel.getName()).append(TextUtil.getSeparator());
        stringHotel.append(hotel.getCity());

        return stringHotel.toString();
    }

    private Hotel lineToHotel(String line) {
        if (line.isEmpty()) {
            return null;
        }
        String[] fields = line.split(TextUtil.getSeparator());
        long id = Long.parseLong(fields[0]);
        String name = fields[1];
        String city = fields[0];
        Hotel hotel = new Hotel(id, name, city);
        return hotel;
    }
}
