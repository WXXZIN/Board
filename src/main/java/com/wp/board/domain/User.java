package com.wp.board.domain;

public class User {

    private Long u_id;
    private String id;
    private String password;
    private String nickname;

    public User() {}

    public User(Long u_id, String id, String password, String nickname) {
        this.u_id = u_id;
        this.id = id;
        this.password = password;
        this.nickname = nickname;
    }

    public Long getU_id() {
        return u_id;
    }

    public void setU_id(Long u_id) {
        this.u_id = u_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
