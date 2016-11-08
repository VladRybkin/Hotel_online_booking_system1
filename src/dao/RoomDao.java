package dao;

import entities.Room;
import entities.User;
import enums.Currency;
import enums.RoomType;
import util.TextUtil;

import java.util.ArrayList;
import java.util.List;

public class RoomDao implements Dao<Room>{
    @Override
    public void add(Room room) {
        TextUtil.writeToFile(TextUtil.ROOM_FILE_NAME, roomToLine(room));
    }

    @Override
    public void update(Room room) {
        TextUtil.updateInFile(TextUtil.ROOM_FILE_NAME, roomToLine(room));
    }

    @Override
    public void delete(long id) {
        TextUtil.deleteFromFile(TextUtil.ROOM_FILE_NAME, id);

    }

    @Override
    public Room findByID(long id) {
        List<Room> rooms = getAll();
        return rooms.stream().filter(room -> room.getId() == id).findFirst().orElse(null);
    }

    @Override
    public List<Room> getAll() {
        List<String> lines = TextUtil.readFromFile(TextUtil.ROOM_FILE_NAME);
        List<Room> rooms = new ArrayList<>();

        for (String line : lines) {
            Room hotel = lineToRoom(line);
            if (hotel == null) {
                continue;
            }
            rooms.add(hotel);
        }
        return rooms;
    }

    private String roomToLine(Room room) {
        StringBuffer stringRoom = new StringBuffer();

        stringRoom.append(room.getId()).append(TextUtil.DB_FIELDS_SEPARATOR);
        stringRoom.append(room.getRoomNumber()).append(TextUtil.DB_FIELDS_SEPARATOR);
        stringRoom.append(room.getPersons()).append(TextUtil.DB_FIELDS_SEPARATOR);
        stringRoom.append(room.getRoomType().name()).append(TextUtil.DB_FIELDS_SEPARATOR);
        stringRoom.append(room.getPrice()).append(TextUtil.DB_FIELDS_SEPARATOR);
        stringRoom.append(room.getCurrency()).append(TextUtil.DB_FIELDS_SEPARATOR);
        try {
            stringRoom.append(room.getReservedForUser().getId()).append(TextUtil.DB_FIELDS_SEPARATOR);
        } catch (NullPointerException e) {
            stringRoom.append(TextUtil.DB_FIELDS_SEPARATOR);
        }

        return stringRoom.toString();
    }

    private Room lineToRoom(String line) {
        if (line.isEmpty()) {
            return null;
        }
        String[] fields = line.split(TextUtil.DB_FIELDS_SEPARATOR);
        long id = Long.parseLong(fields[0]);
        int roomNumber = Integer.parseInt(fields[1]);
        int persons = Integer.parseInt(fields[2]);
        RoomType roomType = RoomType.valueOf(fields[3]);
        int price = Integer.parseInt(fields[4]);
        Currency currency = Currency.valueOf(fields[5]);
        Dao<User> userDao = new UserDao();
        User user;
        try {
            user = userDao.findByID(Long.parseLong(fields[6]));
        } catch (ArrayIndexOutOfBoundsException e) {
            user = null;
        }

        Room room = new Room(id, roomNumber, price, currency, persons, roomType);
        room.setReservedForUser(user);
        return room;
    }
}
