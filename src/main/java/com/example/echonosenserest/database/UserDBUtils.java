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
                    resultSet.getString("firstName"),
                    resultSet.getString("lastName"),
                    resultSet.getString("Email"),
                    resultSet.getString("Password"), // In a real-world scenario, avoid fetching the password.
                    resultSet.getString("Role"),
                    resultSet.getString("status")
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
                    resultSet.getString("firstName"),
                    resultSet.getString("lastName"),
                    resultSet.getString("Email"),
                    resultSet.getString("Password"), // Avoid this in real scenarios.
                    resultSet.getString("Role"),
                    resultSet.getString("status")
            );
        }
        return null;
    }

    // Add user
    public static boolean addUser(User user) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO Users (firstName, lastName, Email, Password, Role, status) VALUES (?, ?, ?, ?, ?, ?)");
        statement.setString(1, user.getFname());
        statement.setString(2, user.getlName());
        statement.setString(3, user.getEmail());
        statement.setString(4, user.getPassword());
        statement.setString(5, user.getRole());
        statement.setString(6, "Active");
        return statement.executeUpdate() > 0;
    }

    // Update user
    public static boolean updateUser(User user) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("UPDATE Users SET firstName = ? , lastName = ?, Email = ?, Password = ?, Role = ? WHERE UserID = ?");
        statement.setString(1, user.getFname());
        statement.setString(2, user.getlName());
        statement.setString(3, user.getEmail());
        statement.setString(4, user.getPassword());
        statement.setString(5, user.getRole());
        statement.setInt(6, user.getUserId());
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


    public static String userLogin(String email, String password) throws SQLException {
        String query = "SELECT Role FROM Users WHERE Email = ? AND Password = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, email);
        statement.setString(2, password);

        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getString("Role");
        }
        return null;
    }

}
