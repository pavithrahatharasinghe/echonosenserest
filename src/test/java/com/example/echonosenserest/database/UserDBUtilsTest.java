package com.example.echonosenserest.database;
import com.example.echonosenserest.database.UserDBUtils;


import com.example.echonosenserest.models.User;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class UserDBUtilsTest {

    @Test
    void testGetAllUsers() {
        try {
            // Test fetching all users
            User user = (User) UserDBUtils.getAllUsers();
            assertNotNull(user);
        } catch (SQLException e) {
            fail("Exception thrown while fetching all users: " + e.getMessage());
        }
    }

    @Test
    void testGetUserById() {
        try {
            // Test fetching a user by ID (replace with a valid user ID)
            int userId = 15;
            User user = UserDBUtils.getUserById(userId);
            assertNotNull(user);
        } catch (SQLException e) {
            fail("Exception thrown while fetching user by ID: " + e.getMessage());
        }
    }

    @Test
    void testAddUser() {
        try {
            // Test adding a new user
            User user = new User(0, "John", "Doe", "johndoe4@example.com", "password", "user", "active");
            boolean isAdded = UserDBUtils.addUser(user);
            assertTrue(isAdded);
        } catch (SQLException e) {
            fail("Exception thrown while adding a user: " + e.getMessage());
        }
    }

    @Test
    void testUpdateUser() {
        try {
            // Test updating user details
            int userId = 15; // Replace with a valid user ID
            User user = new User(userId, "UpdatedFirstName", "UpdatedLastName", "updatedemail@example.com", "updatedpassword", "user", "active");
            boolean isUpdated = UserDBUtils.updateUser(user);
            assertTrue(isUpdated);
        } catch (SQLException e) {
            fail("Exception thrown while updating a user: " + e.getMessage());
        }
    }

    @Test
    void testDeleteUser() {
        try {
            // Test deleting a user by ID (replace with a valid user ID)
            int userId = 1;
            boolean isDeleted = UserDBUtils.deleteUser(userId);
            assertTrue(isDeleted);
        } catch (SQLException e) {
            fail("Exception thrown while deleting a user: " + e.getMessage());
        }
    }

    @Test
    void testChangeUserStatus() {
        try {
            // Test changing user status (replace with a valid user ID and status)
            int userId = 15;
            String status = "inactive";
            boolean isStatusChanged = UserDBUtils.changeUserStatus(userId, status);
            assertTrue(isStatusChanged);
        } catch (SQLException e) {
            fail("Exception thrown while changing user status: " + e.getMessage());
        }
    }

    @Test
    void testUserLogin() {
        try {
            // Test user login (replace with valid email and password)
            String email = "johndoe4@example.com";
            String password = "password";
            String role = UserDBUtils.userLogin(email, password);
            assertNotNull(role);
        } catch (SQLException e) {
            fail("Exception thrown during user login: " + e.getMessage());
        }
    }

    @Test
    void testGetUserPasswordById() {
        try {
            // Test getting a user's password by ID (replace with a valid user ID)
            int userId = 15;
            String password = UserDBUtils.getUserPasswordById(userId);
            assertNotNull(password);
        } catch (SQLException e) {
            fail("Exception thrown while getting user's password by ID: " + e.getMessage());
        }
    }

    @Test
    void testUpdateUserPassword() {
        try {
            // Test updating a user's password (replace with a valid user ID and new password)
            int userId = 15;
            String newPassword = "newpassword";
            boolean isUpdated = UserDBUtils.updateUserPassword(userId, newPassword);
            assertTrue(isUpdated);
        } catch (SQLException e) {
            fail("Exception thrown while updating user password: " + e.getMessage());
        }
    }

    @Test
    void testGetUserNameById() {
        try {
            // Test getting a user's name by ID (replace with a valid user ID)
            int userId = 15;
            String[] userName = UserDBUtils.getUserNameById(userId);
            assertNotNull(userName);
            assertEquals(2, userName.length);
        } catch (SQLException e) {
            fail("Exception thrown while getting user's name by ID: " + e.getMessage());
        }
    }

    @Test
    void testUpdateUserName() {
        try {
            // Test updating a user's first name and last name (replace with a valid user ID and names)
            int userId = 15;
            String newFirstName = "John";
            String newLastName = "Doe";
            boolean isUpdated = UserDBUtils.updateUserName(userId, newFirstName, newLastName);
            assertTrue(isUpdated);
        } catch (SQLException e) {
            fail("Exception thrown while updating user name: " + e.getMessage());
        }
    }

    @Test
    void testGetUserIdByEmail() {
        try {
            // Test getting a user's ID by email (replace with a valid email)
            String email = "johndoe4@example.com";
            int userId = UserDBUtils.getUserIdByEmail(email);
            assertTrue(userId > 0);
        } catch (SQLException e) {
            fail("Exception thrown while getting user ID by email: " + e.getMessage());
        }
    }

    @Test
    void testGetUserByEmailAndPassword() {
        try {
            // Test getting a user by email and password (replace with valid email and password)
            String email = "johndoe4@example.com";
            String password = "password";
            User user = UserDBUtils.getUserByEmailAndPassword(email, password);
            assertNotNull(user);
        } catch (SQLException e) {
            fail("Exception thrown while getting user by email and password: " + e.getMessage());
        }
    }

    @Test
    void testUpdateUserDetails() {
        try {
            // Test updating user details (replace with a valid user ID and new details)
            int userId = 15;
            String newFirstName = "John";
            String newLastName = "Doe";
            String newEmail = "johndoe@example.com";
            boolean isUpdated = UserDBUtils.updateUserDetails(userId, newFirstName, newLastName, newEmail);
            assertTrue(isUpdated);
        } catch (SQLException e) {
            fail("Exception thrown while updating user details: " + e.getMessage());
        }
    }
}
