package com.example.echonosenserest.database;


import com.example.echonosenserest.DatabaseConnection;
import com.example.echonosenserest.models.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDBUtils {

    private static Connection connection;

    static {
        try {
            connection = DatabaseConnection.getInstance().getConnection();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Fetch all users
    public static List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM Users");
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            User user = new User(
                    resultSet.getInt("UserID"),
                    resultSet.getString("Email"),
                    resultSet.getString("Password"), // In a real-world scenario, avoid fetching the password.
                    resultSet.getString("Role")
            );
            users.add(user);
        }
        return users;
    }

    // Fetch user by ID
    public static User getUserById(int userId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM Users WHERE UserID = ?");
        statement.setInt(1, userId);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return new User(
                    resultSet.getInt("UserID"),
                    resultSet.getString("Email"),
                    resultSet.getString("Password"), // Avoid this in real scenarios.
                    resultSet.getString("Role")
            );
        }
        return null;
    }

    // Add user
    public static boolean addUser(User user) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO Users (Email, Password, Role) VALUES (?, ?, ?)");
        statement.setString(1, user.getEmail());
        statement.setString(2, user.getPassword());
        statement.setString(3, user.getRole());
        return statement.executeUpdate() > 0;
    }

    // Update user
    public static boolean updateUser(User user) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("UPDATE Users SET Email = ?, Password = ?, Role = ? WHERE UserID = ?");
        statement.setString(1, user.getEmail());
        statement.setString(2, user.getPassword());
        statement.setString(3, user.getRole());
        statement.setInt(4, user.getUserId());
        return statement.executeUpdate() > 0;
    }

    // Delete user
    public static boolean deleteUser(int userId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM Users WHERE UserID = ?");
        statement.setInt(1, userId);
        return statement.executeUpdate() > 0;
    }

    // Change user status (assuming there's a 'status' column in Users table)
    public static boolean changeUserStatus(int userId, String status) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("UPDATE Users SET Status = ? WHERE UserID = ?");
        statement.setString(1, status);
        statement.setInt(2, userId);
        return statement.executeUpdate() > 0;
    }
}
