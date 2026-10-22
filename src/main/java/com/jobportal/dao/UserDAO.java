package com.jobportal.dao;

import com.jobportal.model.User;
import com.jobportal.util.DBConnection;
import com.jobportal.util.PasswordUtil;
import java.sql.*;

public class UserDAO {
    public boolean register(String name, String email, String password) throws SQLException {
        String sql = "INSERT INTO users(full_name,email,password_hash,role) VALUES(?,?,?,'SEEKER')";
        try (Connection c = DBConnection.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, name); ps.setString(2, email); ps.setString(3, PasswordUtil.hash(password));
            return ps.executeUpdate() == 1;
        }
    }

    public User login(String email, String password) throws SQLException {
        String sql = "SELECT id,full_name,email,password_hash,role FROM users WHERE email=?";
        try (Connection c = DBConnection.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next() && PasswordUtil.verify(password, rs.getString("password_hash"))) {
                    return new User(rs.getInt("id"), rs.getString("full_name"), rs.getString("email"), rs.getString("role"));
                }
            }
        }
        return null;
    }
}
