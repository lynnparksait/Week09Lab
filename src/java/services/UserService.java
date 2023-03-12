package services;

import java.util.List;

import dataaccess.UserDB;
import models.Role;
import models.User;

public class UserService {

    public List<User> getAll() throws Exception {
        UserDB userDB = new UserDB();
        List<User> user = userDB.getAll();
        return user;
    }

    public User getUser(String email) throws Exception {
        UserDB userDB = new UserDB();
        User user = userDB.getUser(email);
        return user;
    }

    public void addUser(String email, String firstName, String lastName, String password, Role role) throws Exception {
        User user = new User(email, firstName, lastName, password, role);
        UserDB userDB = new UserDB();
        userDB.addUser(user);
    }

    public void updateUser(String email, String firstName, String lastName, String password, Role role) throws Exception {
        User user = new User(email, firstName, lastName, password, role);
        UserDB userDB = new UserDB();
        userDB.updateUser(user);
    }

    public void deleteUser(String email) throws Exception {
        UserDB userDB = new UserDB();
        userDB.deleteUser(email);
    }

    public String userExist(String email) throws Exception {
        UserDB userDB = new UserDB();
        User user = userDB.getUser(email);

        if (user == null) {
            return null;
        } else {
            return user.getEmail();
        }
    }
}
