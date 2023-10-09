package com.example.echonosenserest.database;

import com.example.echonosenserest.DatabaseConnection;
import com.example.echonosenserest.models.AdminLog;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminLogDBUtils {
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

    public static List<AdminLog> getAllAdminLogs() throws SQLException {
        List<AdminLog> adminLogs = new ArrayList<>();

        PreparedStatement statement = connection.prepareStatement("SELECT * FROM AdminLogs");
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            int logID = resultSet.getInt("LogID");
            int adminUserID = resultSet.getInt("AdminUserID");
            String action = resultSet.getString("Action");
            String description = resultSet.getString("Description");
            Timestamp createdAt = resultSet.getTimestamp("created_at");

            AdminLog adminLog = new AdminLog(logID, adminUserID, action, description, createdAt);
            adminLogs.add(adminLog);
        }

        return adminLogs;
    }

    public static boolean addAdminLog(AdminLog adminLog) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO AdminLogs (AdminUserID, Action, Description, created_at) VALUES (?, ?, ?, ?)");
        statement.setInt(1, adminLog.getAdminUserID());
        statement.setString(2, adminLog.getAction());
        statement.setString(3, adminLog.getDescription());
        statement.setTimestamp(4, new Timestamp(adminLog.getCreatedAt().getTime()));

        int rowsInserted = statement.executeUpdate();
        return rowsInserted > 0;
    }

}
