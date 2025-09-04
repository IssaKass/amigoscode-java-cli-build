package com.issakass.user;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Author: abdallah-issakass
 */
class UserFakerDataAccessServiceTest {

    private final UserFakerDataAccessService underTest = new UserFakerDataAccessService();

    @Test
    void getUsers_shouldReturnAListOfTwentyUsers() {
        // When
        var users = underTest.getUsers();

        // Then
        assertThat(users).isNotNull()
                .hasSize(20);

        users.forEach(user -> {
            assertThat(user.id()).isNotNull();
            assertThat(user.name()).isNotBlank();
        });
    }

    @Test
    void getUsers_shouldReturnTheSameInstanceEveryTime() {
        // When
        var users1 = underTest.getUsers();
        var users2 = underTest.getUsers();

        // Then
        assertThat(users1).isEqualTo(users2);
    }

}