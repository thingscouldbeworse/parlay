package com.parley.parley;

import java.util.Date;

/**
 * Created by Tricia Sallee on 3/16/2017.
 */


public class ChatMessage {

    private String messText;
    private String messUser;
    private long messTime;

    public ChatMessage(String messText, String messUser) {
        this.messText = messText;
        this.messUser = messUser;

        // Initialize to current time
        messTime = new Date().getTime();
    }

    public ChatMessage(){

    }

    public String getMessText() {
        return messText;
    }

    public String getMessUser() {
        return messUser;
    }

    public long getMessTime() {
        return messTime;
    }

}