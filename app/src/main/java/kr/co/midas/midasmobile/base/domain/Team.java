package kr.co.midas.midasmobile.base.domain;


import java.util.Date;
import java.util.List;

public class Team {

    private static final long serialVersionUID = 1L;

    private long id;
    private String teamName;
    private String description;
    private long point;
    private Date create_at;
    private List<User> users;
    private List<Voluntary> voluntaries;

    public Team(){}

    public Team(long id, String teamName, String description, long point, Date create_at, List<User> users, List<Voluntary> voluntaries) {
        this.id = id;
        this.teamName = teamName;
        this.description = description;
        this.point = point;
        this.create_at = create_at;
        this.users = users;
        this.voluntaries = voluntaries;
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

    public Date getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Date create_at) {
        this.create_at = create_at;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Voluntary> getVoluntaries() {
        return voluntaries;
    }

    public void setVoluntaries(List<Voluntary> voluntaries) {
        this.voluntaries = voluntaries;
    }
}
