package com.issakass.user;

import com.github.javafaker.Faker;

import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

/**
 * Author: abdallah-issakass
 */
public class UserFakerDataAccessService implements UserDAO {

    private static final Faker faker = new Faker();
    private static final List<User> USERS;

    static {
        USERS = IntStream.range(0, 20)
                .mapToObj(i -> new User(UUID.randomUUID(), faker.name().name()))
                .toList();
    }

    @Override
    public List<User> getUsers() {
        return USERS;
    }
}
