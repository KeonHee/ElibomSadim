package kr.co.midas.midasmobile.base.domain;

import java.io.Serializable;
import java.util.Date;

public class Voluntary implements Serializable {

    private static final long serialVersionUID = 1L;

    private long id;
    private String location;
    private String title;
    private String contents;
    private String section;
    private Date voluntary_date;
    private int voluntary_time;
    private int maxParticipants;
    private int currentParticipants;
    private String imgPath;

    public Voluntary(){}

    public Voluntary(long id, String location, String title, String contents, String section, Date voluntary_date, int voluntary_time, int maxParticipants, int currentParticipants, String imgPath) {
        this.id = id;
        this.location = location;
        this.title = title;
        this.contents = contents;
        this.section = section;
        this.voluntary_date = voluntary_date;
        this.voluntary_time = voluntary_time;
        this.maxParticipants = maxParticipants;
        this.currentParticipants = currentParticipants;
        this.imgPath = imgPath;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public Date getVoluntary_date() {
        return voluntary_date;
    }

    public void setVoluntary_date(Date voluntary_date) {
        this.voluntary_date = voluntary_date;
    }

    public int getVoluntary_time() {
        return voluntary_time;
    }

    public void setVoluntary_time(int voluntary_time) {
        this.voluntary_time = voluntary_time;
    }

    public int getMaxParticipants() {
        return maxParticipants;
    }

    public void setMaxParticipants(int maxParticipants) {
        this.maxParticipants = maxParticipants;
    }

    public int getCurrentParticipants() {
        return currentParticipants;
    }

    public void setCurrentParticipants(int currentParticipants) {
        this.currentParticipants = currentParticipants;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }
}
