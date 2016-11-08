package dao;


import entities.User;
import util.TextUtil;

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

        return null;
    }

    @Override
    public List<User> getAll() {

        return null;
    }

    private String userToLine(User user) {
        StringBuffer stringUser = new StringBuffer();

        stringUser.append(user.getId()).append(TextUtil.getSeparator());
        stringUser.append(user.getFullName()).append(TextUtil.getSeparator());
        stringUser.append(user.getEmail()).append(TextUtil.getSeparator());
        stringUser.append(user.getPhoneNumber());

        return stringUser.toString();
    }
}





