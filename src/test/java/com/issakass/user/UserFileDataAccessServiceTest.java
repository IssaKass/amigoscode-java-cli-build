package com.issakass.user;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Author: abdallah-issakass
 */
class UserFileDataAccessServiceTest {

    private final UserFileDataAccessService underTest = new UserFileDataAccessService();

    @Test
    void getUsers_shouldLoadUsersFromFile_whenFileExists() {
        // When
        var users = underTest.getUsers();

        // Then
        assertThat(users)
                .hasSize(2)
                .containsExactly(
                        new User(UUID.fromString("1a2b3c4d-5e6f-7a8b-9c0d-1e2f3a4b5c6d"), "Alice"),
                        new User(UUID.fromString("2a3b4c5d-6e7f-8a9b-0c1d-2e3f4a5b6c7d"), "Bob")
                );
    }
}