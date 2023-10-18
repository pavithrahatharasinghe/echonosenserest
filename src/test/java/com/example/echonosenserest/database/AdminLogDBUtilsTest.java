package com.example.echonosenserest.database;

import com.example.echonosenserest.models.AdminLog;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AdminLogDBUtilsTest {

    @Test
    void getAllAdminLogs() {
        try {
            List<AdminLog> adminLogs = AdminLogDBUtils.getAllAdminLogs();
            assertNotNull(adminLogs); // Ensure that the list is not null
            // Add more specific assertions as needed
        } catch (SQLException e) {
            fail("Exception thrown while retrieving admin logs: " + e.getMessage());
        }
    }

    @Test
    void addAdminLog() {
        try {
            // Create a sample AdminLog object
            AdminLog adminLog = new AdminLog(0, 1, "TestAction", "TestDescription", new java.util.Date());

            boolean isAdded = AdminLogDBUtils.addAdminLog(adminLog);
            assertTrue(isAdded); // Ensure that the entry was added successfully
            // Add more specific assertions as needed
        } catch (SQLException e) {
            fail("Exception thrown while adding admin log: " + e.getMessage());
        }
    }
}
