package kr.co.midas.midasmobile.base.domain;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by user on 2017-05-28.
 */

public class Report implements Serializable {
    private static final long serialVersionUID = 1L;


    @SerializedName("rep_id") private long id;
    @SerializedName("state") private String state;
    @SerializedName("rep_title") private String title;
    @SerializedName("rep_date") private String date;
    @SerializedName("rep_location") private String location;
    @SerializedName("rep_point") private String point;
    @SerializedName("rep_contents") private String contents;
    @SerializedName("rep_image") private String imgUrl;


    public Report(){}

    public Report(long id, String state, String title, String date, String location, String point, String contents, String imgUrl) {
        this.id = id;
        this.state = state;
        this.title = title;
        this.date = date;
        this.location = location;
        this.point = point;
        this.contents = contents;
        this.imgUrl = imgUrl;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
