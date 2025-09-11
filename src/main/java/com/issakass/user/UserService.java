package com.issakass.user;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Author: abdallah-issakass
 */
@Service
public class UserService {

	private final UserDAO userDAO;

	public UserService(@Qualifier("userJpaDAO") UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public List<User> getUsers() {
		return userDAO.getUsers();
	}

	public User getUserById(UUID id) {
		return userDAO.getUserById(id)
				.orElse(null);
	}
}
