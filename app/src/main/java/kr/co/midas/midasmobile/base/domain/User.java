package kr.co.midas.midasmobile.base.domain;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @SerializedName("user_id") private long id;
    @SerializedName("name") private String userName;
    @SerializedName("email") private String email;
    @SerializedName("pw") private String password;
    @SerializedName("point") private long point;
    @SerializedName("introduction") private String introduction;
    @SerializedName("contribute") private int contribute_rate;
    @SerializedName("age") private int age;
    @SerializedName("phonenumber") private String phone;
    @SerializedName("position") private String position;
    @SerializedName("teams") private List<Team> teams;

    private List<Record> records;

    public User(){}

    public User(long id, String userName, String email, String password, long point,
                String introduction, int contribute_rate, int age, String phone, String position,
                List<Team> teams, List<Record> records) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.point = point;
        this.introduction = introduction;
        this.contribute_rate = contribute_rate;
        this.age = age;
        this.phone = phone;
        this.position = position;
        this.teams = teams;
        this.records = records;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getPoint() {
        return point;
    }

    public void setPoint(long point) {
        this.point = point;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public int getContribute_rate() {
        return contribute_rate;
    }

    public void setContribute_rate(int contribute_rate) {
        this.contribute_rate = contribute_rate;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public List<Record> getRecords() {
        return records;
    }

    public void setRecords(List<Record> records) {
        this.records = records;
    }
}
