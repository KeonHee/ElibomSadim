package kr.co.midas.midasmobile.tabbar.objects;

/**
 * Created by user on 2017-05-27.
 */

public class TeamObject {
    String logoUrl;
    String teamName;
    String descript;
    int teamPoint;


    public TeamObject(String logoUrl, String teamName, String descript, int teamPoint){
        this.logoUrl = logoUrl;
        this.teamName = teamName;
        this.descript = descript;
        this.teamPoint = teamPoint;

    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public String getTeamName() {
        return teamName;
    }

    public String getDescript() {
        return descript;
    }

    public int getTeamPoint() {
        return teamPoint;
    }


    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }

    public void setTeamPoint(int teamPoint) {
        this.teamPoint = teamPoint;
    }

}
