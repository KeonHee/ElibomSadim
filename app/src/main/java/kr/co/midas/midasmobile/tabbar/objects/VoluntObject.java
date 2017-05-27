package kr.co.midas.midasmobile.tabbar.objects;

/**
 * Created by user on 2017-05-27.
 */

public class VoluntObject {
    private String imgUrl;
    private String imgTitle;
    private String participants;

    public VoluntObject(String imgUrl, String imgTitle, String participants){
        this.imgUrl = imgUrl;
        this.imgTitle = imgTitle;
        this.participants = participants;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public String getImgTitle() {
        return imgTitle;
    }

    public String getParticipants() {
        return participants;
    }

    public void setImgTitle(String imgTitle) {
        this.imgTitle = imgTitle;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public void setParticipants(String participants) {
        this.participants = participants;
    }
}
