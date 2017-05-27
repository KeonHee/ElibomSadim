package kr.co.midas.midasmobile.base.domain;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by user on 2017-05-28.
 */

public class Rank {

    @SerializedName("user")
    private List<User> users;
    @SerializedName("team")
    private List<Team> teams;
    @SerializedName("section")
    private List<Section> sections;
    @SerializedName("total")
    private long total;

    public Rank(){}

    public Rank(List<User> users, List<Team> teams, List<Section> sections, long total) {
        this.users = users;
        this.teams = teams;
        this.sections = sections;
        this.total = total;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}
