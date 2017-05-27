package kr.co.midas.midasmobile.base.domain;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Team {

    private static final long serialVersionUID = 1L;

    @SerializedName("team_id") private long id;
    @SerializedName("team_name") private String teamName;
    @SerializedName("description") private String description;
    @SerializedName("team_point") private long point;
    @SerializedName("create_at") private String create_at;
    @SerializedName("users") private List<User> users;
    @SerializedName("avartar_url") private String avatarUrl;

    private List<Voluntary> voluntaries;

    public Team(){}


    public Team(long id, String teamName, String description, long point, String create_at, List<User> users, String avatarUrl, List<Voluntary> voluntaries) {
        this.id = id;
        this.teamName = teamName;
        this.description = description;
        this.point = point;
        this.create_at = create_at;
        this.users = users;
        this.avatarUrl = avatarUrl;
        this.voluntaries = voluntaries;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getPoint() {
        return point;
    }

    public void setPoint(long point) {
        this.point = point;
    }

    public String getCreate_at() {
        return create_at;
    }

    public void setCreate_at(String create_at) {
        this.create_at = create_at;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public List<Voluntary> getVoluntaries() {
        return voluntaries;
    }

    public void setVoluntaries(List<Voluntary> voluntaries) {
        this.voluntaries = voluntaries;
    }
}
