package com.issakass.user;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

/**
 * Author: abdallah-issakass
 */
@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserDAO userDAO;

    @InjectMocks
    private UserService underTest;

    @Test
    void getUsers_shouldReturnAllUsersFromDAO() {
        // Given
        var expected = List.of(
                new User(UUID.randomUUID(), "Alice"),
                new User(UUID.randomUUID(), "Bob")
        );

        when(userDAO.getUsers()).thenReturn(expected);

        // When
        var actual = underTest.getUsers();

        // Then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void getUserById_shouldReturnUser_whenIdExists() {
        // Given
        var userId = UUID.randomUUID();
        var expected = new User(userId, "Charlie");

        var users = List.of(
                new User(UUID.randomUUID(), "Alice"),
                expected,
                new User(UUID.randomUUID(), "Bob")
        );

        when(userDAO.getUsers()).thenReturn(users);

        // When
        var actual = underTest.getUserById(userId);

        // Then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void getUserById_shouldReturnNull_whenIdDoesNotExist() {
        // Given
        UUID nonExistentId = UUID.randomUUID();
        var users = List.of(
                new User(UUID.randomUUID(), "Alice"),
                new User(UUID.randomUUID(), "Bob")
        );

        when(userDAO.getUsers()).thenReturn(users);

        // When
        var actual = underTest.getUserById(nonExistentId);

        // Then
        assertThat(actual).isNull();
    }
}