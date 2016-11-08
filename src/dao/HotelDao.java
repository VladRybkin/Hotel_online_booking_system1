package dao;

import entities.Hotel;
import entities.Room;
import util.TextUtil;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    private String hotelToLine(Hotel hotel) {
        StringBuffer stringHotel = new StringBuffer();

        stringHotel.append(hotel.getId()).append(TextUtil.DB_FIELDS_SEPARATOR);
        stringHotel.append(hotel.getName()).append(TextUtil.DB_FIELDS_SEPARATOR);
        stringHotel.append(hotel.getCity()).append(TextUtil.DB_FIELDS_SEPARATOR);
        stringHotel.append(listToField(hotel.getRooms())).append(TextUtil.DB_FIELDS_SEPARATOR);

        return stringHotel.toString();
    }

    private Hotel lineToHotel(String line) {
        if (line.isEmpty()) {
            return null;
        }
        String[] fields = line.split(TextUtil.DB_FIELDS_SEPARATOR);
        long id = Long.parseLong(fields[0]);
        String name = fields[1];
        String city = fields[2];
        List<Room> rooms = fieldToList(fields[3]);
        Hotel hotel = new Hotel(id, name, city, rooms);
        return hotel;
    }

    private String listToField(List<Room> rooms) {
        return rooms.stream()
                .map(room -> Long.toString(room.getId()))
                .collect(Collectors.joining(TextUtil.LIST_FIELDS_SEPARATOR));
    }

    private List<Room> fieldToList(String field) {
        if (field.isEmpty()) {
            return null;
        }
        List<Room> rooms = new ArrayList<>();
        String[] roomsIds = field.split(TextUtil.LIST_FIELDS_SEPARATOR);
        for (String roomId : roomsIds) {
            Dao<Room> roomDao = new RoomDao();
            Room room = roomDao.findByID(Long.parseLong(roomId));
            rooms.add(room);
        }
        return rooms;
    }
}
