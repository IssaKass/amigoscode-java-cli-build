package com.issakass.user;

import com.github.javafaker.Faker;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.IntStream;

/**
 * Author: abdallah-issakass
 */
@Service("userFakerDAO")
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

	@Override
	public Optional<User> getUserById(UUID id) {
		return USERS.stream()
				.filter(user -> user.getId().equals(id))
				.findFirst();
	}
}
