package models;

public class EducationApp extends App {
    @Override
    public boolean isRecommendedApp() {
        if (this.appCost >= 0.99 && calculateRating() >= 3.5 && this.level >= 3){
            return true;
        }
        return false;
    }

    @Override
    public String toSring(){
        return "EducationApp:"+
                "level:" + level+
                "developer:"+ developer+
                "appName:"+ appName+
                "appVersion:"+appVersion+
                "appCost:"+appCost+
                "appSize:"+appSize+
                "rating:" + ratings;
    }

    public EducationApp(Developer developer, String appName, double appVersion , double appCost, double appSize, int level){
        this.level = level;
        this.appName = appName;
        this.developer = developer;
        this.appVersion = appVersion;
        this.appCost = appCost;
        this.appSize = appSize;
        this.ratings = ratings;
    }

    int level = 0;
    public int getLevel(){
        if(level >=0&&level <=10){
            this.level = level;
        }
        return 0;
    }

    public void  setLevel(int level) {
        if (level >= 0 && level <= 10) {
            this.level = level;
        }

    }
}
