package com.issakass.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * Author: abdallah-issakass
 */
public interface UserRepository extends JpaRepository<User, UUID> {
}
