package models;

import java.util.List;

public class GameApp extends App {

    boolean isMultiplayer;



    public void setMultiplayer(boolean multiplayer){isMultiplayer = multiplayer;}

    @Override
    public String appSummary(){
        return "GameApp"+
                "isMultiplayer:" + isMultiplayer +
                "developer:" + developer +
                "appName:" + appName +
                "appVersion:" + appVersion +
                "appCost:" + appCost +
                "ratings:" + ratings ;
    }

    public GameApp(Developer developer, String appName, double appVersion , double appCost, double appSize, boolean isMultiplayer){
        this.isMultiplayer = isMultiplayer;
        this.appName = appName;
        this.developer = developer;
        this.appVersion = appVersion;
        this.appCost = appCost;
        this.appSize = appSize;
    }

    @Override
    public boolean isRecommendedApp(){
        if((this.isMultiplayer = true) && (this.calculateRating() >= 4.0)){return  true;}
        return false;
    }

    @Override
    public String toSring() {
        return null;
    }
}
