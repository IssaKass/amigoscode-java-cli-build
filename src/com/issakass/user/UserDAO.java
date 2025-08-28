package com.issakass.user;

import java.util.UUID;

/**
 * Author: abdallah-issakass
 */
public class UserDAO {

    private static final User[] USERS = {
            new User(UUID.fromString("aa7b8bc7-1b1f-4e6e-9082-3144cc8e00d5"), "James"),
            new User(UUID.fromString("6199a6a5-1efb-4ef2-972f-73f32c639ef5"), "Jamila")
    };

    public User[] getUsers() {
        return USERS;
    }
}
