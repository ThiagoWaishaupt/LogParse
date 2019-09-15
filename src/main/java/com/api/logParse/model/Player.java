package com.api.logParse.model;

public class Player {

    private String nick;
    private int kill;

    public Player() {
        kill = 0;
    }

    public void morreuProMundo() {
        if (kill > 0) {
            kill--;
        }
    }

    public void matou() {
        kill++;
    }

    //Getters and Setters

    public String getNick() {
        return nick;
    }

    public void setNick(final String nick) {
        this.nick = nick;
    }

    public int getKill() {
        return kill;
    }

    public void setKill(final int kill) {
        this.kill = kill;
    }

}
