package com.wp.board.dao;

import java.util.List;

import com.wp.board.domain.Post;

public interface PostDAO {
    int writePost(Post post);
    int getTotalCount(String boardType);
    int getSearchPostCount(String boardType, String searchType, String searchKey);
    List<Post> getPostList(int start, int end, String boardType, String searchType, String searchKeyword);
    Post getPost(Long p_id);
    int editPost(Post post);
    int deletePost(Long p_id);
}
