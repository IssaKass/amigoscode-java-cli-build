package com.issakass.user;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Author: abdallah-issakass
 */
@Service("userArrayDAO")
public class UserArrayDataAccessService implements UserDAO {

	private static final User[] USERS;

	static {
		USERS = new User[]{
				new User(UUID.fromString("aa7b8bc7-1b1f-4e6e-9082-3144cc8e00d5"), "James"),
				new User(UUID.fromString("6199a6a5-1efb-4ef2-972f-73f32c639ef5"), "Jamila")
		};
	}


	@Override
	public List<User> getUsers() {
		return Arrays.asList(USERS);
	}

	@Override
	public Optional<User> getUserById(UUID id) {
		return getUsers().stream()
				.filter(user -> user.getId().equals(id))
				.findFirst();
	}

}
