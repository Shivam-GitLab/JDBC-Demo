package com.sm.jdbcDemo;

import java.sql.*;

public class JDBCDemo {
    private static final String URL = "jdbc:mysql://localhost:3306/COLLEGE_DB";
    private static final String USER = "root";
    private static final String PASSWORD = "ShivamJha@2909";

    public static void main(String[] args) {
     // Without try with resources
 /*       Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connected to the Database!");

        } catch (SQLException e) {
            System.err.println("❌ Database operation failed");
            System.err.println("Error Message: " + e.getMessage());
        } finally {
            try {
                conn.close();
                System.out.println("Connection closed!");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }*/
        // try with resources
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            System.out.println("Connected to the Database!");
            insertStudent(conn, "Alice", "alice@gmail.com");
            updateStudent(conn, 2, "Bob", "alice@gmail.com");
            selectStudents(conn);
            deleteStudent(conn,1);
        } catch (SQLException e) {
            System.err.println("❌ Database operation failed");
            System.err.println("Error Message: " + e.getMessage());
        }
    }

    // CRUD Operations - Create, Read, Update, Delete
    // Create - INSERT operation
    private static void insertStudent(Connection conn, String name, String email) {
        String sql = "INSERT INTO STUDENT (name, email) VALUES ('" + name + "','" + email + "')";
        try (Statement stmt = conn.createStatement()) {
            int rows = stmt.executeUpdate(sql);
            System.out.println("INSERTED: " + rows);
        } catch (SQLException e) {
            System.err.println("❌ Database operation failed");
            System.err.println("Error Message: " + e.getMessage());
        }

    }

    // Read - SELECT operation
    private static void selectStudents(Connection conn) {
        String sql = "SELECT * FROM STUDENT";
        try (Statement stmt = conn.createStatement()) {
            ResultSet resultSet = stmt.executeQuery(sql);
            System.out.println("Student List: ");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                System.out.println(id + " : " + name + " : " + email);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    // Update - UPDATE operation
    private static void updateStudent(Connection conn, int id, String name, String email) {
//        String sql = "UPDATE student SET name = '" + name + "', email = '" + email + "' WHERE id=" + id;
        String sql = "UPDATE STUDENT SET name = ?, email = ? WHERE id = ?";
//       UPDATE student SET name = 'Alice', email = 'email@gmail.com'
//       WHERE id = 10;
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.setInt(3, id);
            int rows = pstmt.executeUpdate();
            System.out.println("UPDATED: " + rows);
        } catch (SQLException e) {
            System.err.println("❌ Database operation failed");
            System.err.println("Error Message: " + e.getMessage());
        }

    }

    // Delete - DELETE operation
    private static void deleteStudent(Connection conn, int id) {
        String sql = "DELETE FROM STUDENT WHERE id = " + id;
        try (Statement stmt = conn.createStatement()) {
            int rows = stmt.executeUpdate(sql);
            System.out.println("DELETED: " + rows);
        } catch (SQLException e) {
            System.err.println("❌ Database operation failed");
            System.err.println("Error Message: " + e.getMessage());
        }
    }
}






