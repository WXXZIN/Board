package com.wp.board.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.wp.board.dao.PostDAO;
import com.wp.board.domain.Post;

public class PostDAOImpl implements PostDAO {

    private final String WRITE_POST = "INSERT INTO POST (title, content, writer, boardType, regDate) VALUES (?, ?, ?, ?, ?)";
    private final String GET_TOTAL_COUNT = "SELECT COUNT(*) FROM POST WHERE boardType = ?";
    private final String GET_SEARCH_POST_COUNT =
    "SELECT COUNT(*) FROM POST " +
    "WHERE boardType = ? " +
    "AND ( " +
    "    CASE " +
    "        WHEN ? = 'title' THEN title LIKE ? " +
    "        WHEN ? = 'content' THEN content LIKE ? " +
    "        WHEN ? = 'writer' THEN writer = ? " +
    "        ELSE TRUE " +
    "    END " +
    ")";
    private final String GET_POST_LIST =
    "SELECT * FROM POST " +
    "WHERE boardType = ? " +
    "AND ( " +
    "    CASE " +
    "        WHEN ? = 'title' THEN title LIKE ? " +
    "        WHEN ? = 'content' THEN content LIKE ? " +
    "        WHEN ? = 'writer' THEN writer = ? " +
    "        ELSE TRUE " +
    "    END " +
    ") " +
    "ORDER BY p_id DESC LIMIT ?, ?";
    private final String GET_POST = "SELECT * FROM POST WHERE p_id = ?";
    private final String EDIT_POST = "UPDATE POST SET title = ?, content = ? WHERE p_id = ?";
    private final String DELETE_POST = "DELETE FROM POST WHERE p_id = ?";

    protected Connection connection = null;
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;

    public PostDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public int writePost(Post post) {
        int result = 0;

        try {
            pstmt = connection.prepareStatement(WRITE_POST);
            pstmt.setString(1, post.getTitle());
            pstmt.setString(2, post.getContent());
            pstmt.setString(3, post.getWriter());
            pstmt.setString(4, post.getBoardType());
            pstmt.setTimestamp(5, java.sql.Timestamp.valueOf(post.getRegDate()));

            result = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public int getTotalCount(String boardType) {
        int result = 0;

        try {
            pstmt = connection.prepareStatement(GET_TOTAL_COUNT);
            pstmt.setString(1, boardType);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                result = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public int getSearchPostCount(String boardType, String searchType, String searchKey) {
        int result = 0;

        try {
            pstmt = connection.prepareStatement(GET_SEARCH_POST_COUNT);
            pstmt.setString(1, boardType);
            pstmt.setString(2, searchType != null ? searchType : null);

            if (searchKey != null && searchType != null) {
                if (searchType.equals("title") || searchType.equals("content")) {
                    pstmt.setString(3, "%" + searchKey + "%");
                    pstmt.setString(4, searchType);
                    pstmt.setString(5, "%" + searchKey + "%");
                    pstmt.setString(6, searchType);
                    pstmt.setString(7, "%" + searchKey + "%");
                } else if (searchType.equals("writer")) {
                    pstmt.setString(3, searchKey);
                    pstmt.setString(4, searchType);
                    pstmt.setString(5, searchKey);
                    pstmt.setString(6, searchType);
                    pstmt.setString(7, searchKey);
                } else {
                    pstmt.setString(3, null);
                    pstmt.setString(4, null);
                    pstmt.setString(5, null);
                    pstmt.setString(6, null);
                    pstmt.setString(7, null);
                }
            }

            rs = pstmt.executeQuery();

            if (rs.next()) {
                result = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public List<Post> getPostList(int start, int end, String boardType, String searchType, String searchKeyword) {
        List<Post> postList = new ArrayList<>();

        try {
            pstmt = connection.prepareStatement(GET_POST_LIST);
            pstmt.setString(1, boardType);
            pstmt.setString(2, searchType != null ? searchType : null);

            if (searchKeyword != null && searchType != null) {
                if (searchType.equals("title") || searchType.equals("content")) {
                    pstmt.setString(3, "%" + searchKeyword + "%");
                    pstmt.setString(4, searchType);
                    pstmt.setString(5, "%" + searchKeyword + "%");
                    pstmt.setString(6, searchType);
                    pstmt.setString(7, "%" + searchKeyword + "%");
                } else if (searchType.equals("writer")) {
                    pstmt.setString(3, searchKeyword);
                    pstmt.setString(4, searchType);
                    pstmt.setString(5, searchKeyword);
                    pstmt.setString(6, searchType);
                    pstmt.setString(7, searchKeyword);
                } else {
                    pstmt.setString(3, null);
                    pstmt.setString(4, null);
                    pstmt.setString(5, null);
                    pstmt.setString(6, null);
                    pstmt.setString(7, null);
                }
            }

            pstmt.setInt(8, start);
            pstmt.setInt(9, end);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                Post post = new Post();
                post.setP_id(rs.getLong("p_id"));
                post.setTitle(rs.getString("title"));
                post.setContent(rs.getString("content"));
                post.setWriter(rs.getString("writer"));
                post.setRegDate(rs.getTimestamp("regDate").toLocalDateTime());
                post.setBoardType(rs.getString("boardType"));

                postList.add(post);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return postList;
    }

    @Override
    public Post getPost(Long p_id) {
        Post post = null;

        try {
            pstmt = connection.prepareStatement(GET_POST);
            pstmt.setLong(1, p_id);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                post = new Post();
                post.setP_id(rs.getLong("p_id"));
                post.setTitle(rs.getString("title"));
                post.setContent(rs.getString("content"));
                post.setWriter(rs.getString("writer"));
                post.setRegDate(rs.getTimestamp("regDate").toLocalDateTime());
                post.setBoardType(rs.getString("boardType"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return post;
    }

    @Override
    public int editPost(Post post) {
        int result = 0;

        try {
            pstmt = connection.prepareStatement(EDIT_POST);
            pstmt.setString(1, post.getTitle());
            pstmt.setString(2, post.getContent());
            pstmt.setLong(3, post.getP_id());

            result = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public int deletePost(Long p_id) {
        int result = 0;

        try {
            pstmt = connection.prepareStatement(DELETE_POST);
            pstmt.setLong(1, p_id);

            result = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
