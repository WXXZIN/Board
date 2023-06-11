package com.wp.board.dao;

import java.util.List;

import com.wp.board.domain.User;

public interface UserDAO {
    int registerUser(User user);
    int getTotalCount();
    List<User> getUserList(int startIndex, int pageSize);
    User getUserById(String id);
    int deleteUser(String id, String nickname);
    boolean isIdExist(String id);
    boolean isNicknameExist(String nickname);
}
