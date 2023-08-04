package com.cq.edu.pojo;

public class Match {
    private String id;
    private String player_a;
    private String a_song;
    private String player_b;
    private String b_song;
    private String state;
    private String player_a_id;
    private String player_b_id;

    public String getPlayer_a_id() {
        return player_a_id;
    }

    public void setPlayer_a_id(String player_a_id) {
        this.player_a_id = player_a_id;
    }

    public String getPlayer_b_id() {
        return player_b_id;
    }

    public void setPlayer_b_id(String player_b_id) {
        this.player_b_id = player_b_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlayer_a() {
        return player_a;
    }

    public void setPlayer_a(String player_a) {
        this.player_a = player_a;
    }

    public String getA_song() {
        return a_song;
    }

    public void setA_song(String a_song) {
        this.a_song = a_song;
    }

    public String getPlayer_b() {
        return player_b;
    }

    public void setPlayer_b(String player_b) {
        this.player_b = player_b;
    }

    public String getB_song() {
        return b_song;
    }

    public void setB_song(String b_song) {
        this.b_song = b_song;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
