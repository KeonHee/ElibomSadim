package kr.co.midas.midasmobile.base.domain;

import java.io.Serializable;
import java.util.Date;

public class Donation implements Serializable{

    private static final long serialVersionUID = 1L;

    private long id;
    private String senderType;
    private Date donate_at;
    private long point;
    private Team team;
    private User user;
    private String recipient;

    public Donation(){}

    public Donation(long id, String senderType, Date donate_at, long point, Team team, User user, String recipient) {
        this.id = id;
        this.senderType = senderType;
        this.donate_at = donate_at;
        this.point = point;
        this.team = team;
        this.user = user;
        this.recipient = recipient;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSenderType() {
        return senderType;
    }

    public void setSenderType(String senderType) {
        this.senderType = senderType;
    }

    public Date getDonate_at() {
        return donate_at;
    }

    public void setDonate_at(Date donate_at) {
        this.donate_at = donate_at;
    }

    public long getPoint() {
        return point;
    }

    public void setPoint(long point) {
        this.point = point;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }
}
