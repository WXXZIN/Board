package com.wp.board.domain;

import java.time.LocalDateTime;

public class Post {

    private Long p_id;
    private String title;
    private String content;
    private String writer;
    private String boardType;
    private LocalDateTime regDate;

    public Post() {}

    public Post(Long p_id, String title, String content, String writer, String boardType, LocalDateTime regDate) {
        this.p_id = p_id;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.boardType = boardType;
        this.regDate = regDate;
    }

    public Long getP_id() {
        return p_id;
    }

    public void setP_id(Long p_id) {
        this.p_id = p_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getBoardType() {
        return boardType;
    }

    public void setBoardType(String boardType) {
        this.boardType = boardType;
    }

    public LocalDateTime getRegDate() {
        return regDate;
    }

    public void setRegDate(LocalDateTime regDate) {
        this.regDate = regDate;
    }
}
