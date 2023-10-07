package com.example.echonosenserest.database;

import com.example.echonosenserest.DatabaseConnection;
import com.example.echonosenserest.models.AdminLog;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminLogDBUtils {
    private static Connection connection;
    // ... connection setup ...

    public static List<AdminLog> getAllAdminLogs() throws SQLException {
        List<AdminLog> adminLogs = new ArrayList<>();

        PreparedStatement statement = connection.prepareStatement("SELECT * FROM AdminLogs");
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            int logID = resultSet.getInt("LogID");
            int adminUserID = resultSet.getInt("AdminUserID");
            String action = resultSet.getString("Action");
            Date date = resultSet.getDate("Date");

            AdminLog adminLog = new AdminLog(logID, adminUserID, action, date);
            adminLogs.add(adminLog);
        }

        return adminLogs;
    }

    public static boolean addAdminLog(AdminLog adminLog) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO AdminLogs (AdminUserID, Action, Date) VALUES (?, ?, ?)");
        statement.setInt(1, adminLog.getAdminUserID());
        statement.setString(2, adminLog.getAction());
        statement.setDate(3, new java.sql.Date(adminLog.getDate().getTime()));

        int rowsInserted = statement.executeUpdate();
        return rowsInserted > 0;
    }
}
