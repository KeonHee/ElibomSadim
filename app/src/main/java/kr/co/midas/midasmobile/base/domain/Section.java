package kr.co.midas.midasmobile.base.domain;

import com.google.gson.annotations.SerializedName;

/**
 * Created by user on 2017-05-28.
 */

public class Section {
    @SerializedName("section_name")
    private String name;
    @SerializedName("section_point")
    private int point;

    public Section(){}

    public Section(String name, int point) {
        this.name = name;
        this.point = point;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }
}
