package com.messed.chatappatg.Model;

public class UserChatList {
    String username;
    String userkey;

    public UserChatList(String username, String userkey) {
        this.username = username;
        this.userkey = userkey;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserkey() {
        return userkey;
    }

    public void setUserkey(String userkey) {
        this.userkey = userkey;
    }
}
