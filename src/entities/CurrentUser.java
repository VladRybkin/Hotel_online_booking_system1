package entities;

public class CurrentUser {

    private static User currentUser;

    public static void setCurrentUser(User user) {
        if (user == null) {
            throw new NullPointerException();
        }
        currentUser = user;
}

    public static User getCurrentUser() {
        return currentUser;
    }
}
