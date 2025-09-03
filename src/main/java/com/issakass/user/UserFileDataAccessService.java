package com.issakass.user;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

/**
 * Author: abdallah-issakass
 */
public class UserFileDataAccessService implements UserDAO {
    @Override
    public List<User> getUsers() {
        File file = new File(getClass().getClassLoader().getResource("users.csv").getPath());

        List<User> users = new ArrayList<>();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                String[] split = scanner.nextLine().split(", ");
                users.add(new User(UUID.fromString(split[0]), split[1]));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return users;
    }
}
