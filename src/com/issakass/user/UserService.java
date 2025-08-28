package com.issakass.user;

import java.util.UUID;

/**
 * Author: abdallah-issakass
 */
public class UserService {

    private final UserDAO userDao = new UserDAO();

    public User[] getUsers() {
        return userDao.getUsers();
    }

    public User getUserById(UUID id) {
        for (User user : getUsers()) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        return null;
    }
}
