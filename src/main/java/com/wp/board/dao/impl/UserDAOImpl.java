package com.wp.board.dao.impl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.wp.board.dao.UserDAO;
import com.wp.board.domain.User;

public class UserDAOImpl implements UserDAO {

    private final String REGISTER_USER = "INSERT INTO \"USER\" (id, password, nickname) VALUES (?, ?, ?)";
    private final String GET_TOTAL_COUNT = "SELECT COUNT(*) FROM \"USER\"";
    private final String GET_USER_LIST = "SELECT * FROM \"USER\" WHERE id NOT IN ('admin') ORDER BY id DESC LIMIT ?, ?";
    private final String GET_USER_BY_ID = "SELECT * FROM \"USER\" WHERE id = ?";
    private final String DELETE_USER = "DELETE FROM \"USER\" WHERE id = ?; DELETE FROM POST WHERE WRITER = ?";
    private final String IS_ID_EXIST = "SELECT COUNT(*) FROM \"USER\" WHERE id = ?";
    private final String IS_NICKNAME_EXIST = "SELECT COUNT(*) FROM \"USER\" WHERE nickname = ?";

    protected Connection connection = null;
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;


    public UserDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public int registerUser(User user) {
        int result = 0;

        try {
            pstmt = connection.prepareStatement(REGISTER_USER);
            pstmt.setString(1, user.getId());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getNickname());

            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public int getTotalCount() {
        int result = 0;

        try {
            pstmt = connection.prepareStatement(GET_TOTAL_COUNT);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                result = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public List<User> getUserList(int startIndex, int pageSize) {
        List<User> userList = new ArrayList<>();

        try {
            pstmt = connection.prepareStatement(GET_USER_LIST);
            pstmt.setInt(1, startIndex);
            pstmt.setInt(2, pageSize);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getString("id"));
                user.setPassword(rs.getString("password"));
                user.setNickname(rs.getString("nickname"));

                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userList;
    }

    @Override
    public User getUserById(String id) {
        User result = null;

        try {
            pstmt = connection.prepareStatement(GET_USER_BY_ID);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                result = new User();
                result.setId(rs.getString("id"));
                result.setPassword(rs.getString("password"));
                result.setNickname(rs.getString("nickname"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public int deleteUser(String id, String nickname) {
        int result = 0;

        try {
            pstmt = connection.prepareStatement(DELETE_USER);
            pstmt.setString(1, id);
            pstmt.setString(2, nickname);

            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public boolean isIdExist(String id) {
        boolean result = false;

        try {
            pstmt = connection.prepareStatement(IS_ID_EXIST);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                result = rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public boolean isNicknameExist(String nickname) {
        boolean result = false;

        try {
            pstmt = connection.prepareStatement(IS_NICKNAME_EXIST);
            pstmt.setString(1, nickname);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                result = rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
}
