package com.issakass.user;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Author: abdallah-issakass
 */
public interface UserDAO {
    List<User> getUsers();

	Optional<User> getUserById(UUID id);
}
