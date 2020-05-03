package com.messed.chatappatg.Model;

public class ChatInside {
    String type;
    String info;
    String time;

    public ChatInside(String type, String info, String time) {
        this.type = type;
        this.info = info;
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
