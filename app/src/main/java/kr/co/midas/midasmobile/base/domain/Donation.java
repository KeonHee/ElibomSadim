package kr.co.midas.midasmobile.base.domain;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Donation implements Serializable{

    private static final long serialVersionUID = 1L;

    @SerializedName("donation_id")
    private long id;
    @SerializedName("sender_type")
    private String senderType;
    @SerializedName("donate_date")
    private String donate_at;
    @SerializedName("donate_point")
    private long point;

    private Team team;

    private User user;
    @SerializedName("rep_title")
    private String recipient;

    public Donation(){}

    public Donation(long id, String senderType, String donate_at, long point, Team team, User user, String recipient) {
        this.id = id;
        this.senderType = senderType;
        this.donate_at = donate_at;
        this.point = point;
        this.team = team;
        this.user = user;
        this.recipient = recipient;
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

    public String getDonate_at() {
        return donate_at;
    }

    public void setDonate_at(String donate_at) {
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
