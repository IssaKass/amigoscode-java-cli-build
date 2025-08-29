package com.issakass.user;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.UUID;

/**
 * Author: abdallah-issakass
 */
public class UserFileDataAccessService implements UserDAO {
    @Override
    public User[] getUsers() {
        User[] users = new User[4];

        File file = new File("src/com/issakass/users.csv");
        try (Scanner scanner = new Scanner(file)) {
            int index = 0;

            while (scanner.hasNext()) {
                String[] split = scanner.nextLine().split(", ");
                users[index++] = new User(UUID.fromString(split[0]), split[1]);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return users;
    }
}
