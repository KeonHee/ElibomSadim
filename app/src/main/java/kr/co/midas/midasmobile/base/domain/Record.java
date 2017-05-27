package kr.co.midas.midasmobile.base.domain;

import java.io.Serializable;

public class Record implements Serializable{

    private static final long serialVersionUID = 1L;

    private long id;
    private int playTime;
    private Voluntary voluntary;
    private User user;
    private Team team;

    public Record(){}

    public Record(long id, int playTime, Voluntary voluntary, User user, Team team) {
        this.id = id;
        this.playTime = playTime;
        this.voluntary = voluntary;
        this.user = user;
        this.team = team;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getPlayTime() {
        return playTime;
    }

    public void setPlayTime(int playTime) {
        this.playTime = playTime;
    }

    public Voluntary getVoluntary() {
        return voluntary;
    }

    public void setVoluntary(Voluntary voluntary) {
        this.voluntary = voluntary;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
