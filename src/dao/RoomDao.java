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
        return null;
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

        stringRoom.append(room.getId()).append(TextUtil.getSeparator());
        stringRoom.append(room.getRoomNumber()).append(TextUtil.getSeparator());
        stringRoom.append(room.getPersons()).append(TextUtil.getSeparator());
        stringRoom.append(room.getRoomType().name()).append(TextUtil.getSeparator());
        stringRoom.append(room.getPrice()).append(TextUtil.getSeparator());
        stringRoom.append(room.getCurrency()).append(TextUtil.getSeparator());
        stringRoom.append(room.getReservedForUser().getId());

        return stringRoom.toString();
    }

    private Room lineToRoom(String line) {
        if (line.isEmpty()) {
            return null;
        }
        String[] fields = line.split(TextUtil.getSeparator());
        long id = Long.parseLong(fields[0]);
        int roomNumber = Integer.parseInt(fields[1]);
        int persons = Integer.parseInt(fields[2]);
        RoomType roomType = RoomType.valueOf(fields[3]);
        int price = Integer.parseInt(fields[4]);
        Currency currency = Currency.valueOf(fields[5]);
        Dao<User> userDao = new UserDao();
        User user = userDao.findByID(Long.parseLong(fields[6]));
        Room room = new Room(id, roomNumber, price, currency, persons, roomType);
        room.setReservedForUser(user);
        return room;
    }
}
