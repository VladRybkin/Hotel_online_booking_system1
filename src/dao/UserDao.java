package dao;


import entities.User;
import util.TextUtil;

import java.util.ArrayList;
import java.util.List;

public class UserDao implements Dao<User> {

    @Override
    public void add(User user) {

        TextUtil.writeToFile(TextUtil.USER_FILE_NAME, userToLine(user));
    }

    @Override
    public void update(User user) {
        TextUtil.updateInFile(TextUtil.USER_FILE_NAME, userToLine(user));
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public User findByID(long id) {
        List<User> users = getAll();
        return users.stream().filter(user -> user.getId() == id).findFirst().orElse(null);
    }

    @Override
    public List<User> getAll() {
        List<String> lines = TextUtil.readFromFile(TextUtil.USER_FILE_NAME);
        List<User> users = new ArrayList<>();

        for (String line : lines) {
            User user = lineToUser(line);
            if (user == null) {
                continue;
            }
            users.add(user);
        }
        return users;
    }

    private String userToLine(User user) {
        StringBuffer stringUser = new StringBuffer();

        stringUser.append(user.getId()).append(TextUtil.DB_FIELDS_SEPARATOR);
        stringUser.append(user.getFullName()).append(TextUtil.DB_FIELDS_SEPARATOR);
        stringUser.append(user.getEmail()).append(TextUtil.DB_FIELDS_SEPARATOR);
        stringUser.append(user.getPhoneNumber());

        return stringUser.toString();
    }

    private User lineToUser(String line) {
        if (line.isEmpty()) {
            return null;
        }
        String[] fields = line.split(TextUtil.DB_FIELDS_SEPARATOR);
        long id = Long.parseLong(fields[0]);
        String fullName = fields[1];
        String email = fields[2];
        String phoneNumber = fields[3];
        User user = new User(id, fullName, email, phoneNumber);
        return user;
    }
}





