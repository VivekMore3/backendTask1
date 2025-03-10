package com.main.springBatch.controllers;

import com.main.springBatch.entities.User;
import com.main.springBatch.repositories.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserController userController;

    private User user1, user2;

    @Before
    public void setup() {

        user1 = new User(1, "John", "Doe", "john.doe@example.com", 1234567890, "password123");
        user2 = new User(2, "Jane", "Doe", "jane.doe@example.com", 1827654321, "password123");
    }

    @Test
    public void testGetUsers() {
        when(userRepository.findAll()).thenReturn(Arrays.asList(user1, user2));

        ResponseEntity<List<User>> response = userController.getUsers();

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
    }

    @Test
    public void testAddUser() {
        when(userRepository.save(any(User.class))).thenReturn(user1);

        ResponseEntity<User> response = userController.addUser(user1);

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals("John", response.getBody().getFirstName());
    }

    @Test
    public void testUpdateUser() {
        when(userRepository.findById(1)).thenReturn(Optional.of(user1));
        when(userRepository.save(any(User.class))).thenReturn(user1);

        User updatedUser = new User(1, "John Updated", "Doe", "john.doe@example.com", 1234567890, "password123");

        ResponseEntity<User> response = userController.updateUser(1, updatedUser);

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals("John Updated", response.getBody().getFirstName());
    }

    @Test
    public void testUpdateUser_NotFound() {
        when(userRepository.findById(3)).thenReturn(Optional.empty());

        ResponseEntity<User> response = userController.updateUser(3, user1);

        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    public void testDeleteUser() {
        when(userRepository.existsById(1)).thenReturn(true);
        doNothing().when(userRepository).deleteById(1);

        ResponseEntity<String> response = userController.deleteUser(1);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("User deleted successfully", response.getBody());
    }

    @Test
    public void testDeleteUser_NotFound() {
        when(userRepository.existsById(3)).thenReturn(false);

        ResponseEntity<String> response = userController.deleteUser(3);

        assertEquals(404, response.getStatusCodeValue());
    }
}