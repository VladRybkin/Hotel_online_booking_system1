package dao;

import entities.Room;
import util.TextUtil;

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
        return null;
    }

    private String roomToLine(Room room) {
        StringBuffer stringRoom = new StringBuffer();

        stringRoom.append(room.getId()).append(TextUtil.getSeparator());
        stringRoom.append(room.getRoomNumber()).append(TextUtil.getSeparator());
        stringRoom.append(room.getPersons()).append(TextUtil.getSeparator());
        stringRoom.append(room.getRoomType().name()).append(TextUtil.getSeparator());
        stringRoom.append(room.getPrice()).append(TextUtil.getSeparator());
        stringRoom.append(room.getReservedForUser().getId());

        return stringRoom.toString();
    }
}
