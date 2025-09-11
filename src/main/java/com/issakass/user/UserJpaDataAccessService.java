package com.issakass.user;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Author: abdallah-issakass
 */
@Service("userJpaDAO")
public class UserJpaDataAccessService implements UserDAO {

	private final UserRepository userRepository;

	public UserJpaDataAccessService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public List<User> getUsers() {
		return userRepository.findAll();
	}

	@Override
	public Optional<User> getUserById(UUID id) {
		return userRepository.findById(id);
	}
}
