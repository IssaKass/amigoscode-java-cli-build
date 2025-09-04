package com.issakass.user;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Author: abdallah-issakass
 */
class UserArrayDataAccessServiceTest {

    private final UserArrayDataAccessService underTest = new UserArrayDataAccessService();

    @Test
    void canGetAllUsers() {
        // When
        var users = underTest.getUsers();

        // Then
        assertThat(users).isNotNull()
                .hasSize(2)
                .containsExactly(
                        new User(UUID.fromString("aa7b8bc7-1b1f-4e6e-9082-3144cc8e00d5"), "James"),
                        new User(UUID.fromString("6199a6a5-1efb-4ef2-972f-73f32c639ef5"), "Jamila")
                );
    }
}