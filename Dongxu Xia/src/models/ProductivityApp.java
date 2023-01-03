package models;

public class ProductivityApp extends App{
    @Override
    public String toSring(){
        return "ProductivityApp:"+
                "developer:"+ developer+
                "appName:"+ appName+
                "appVersion:"+appVersion+
                "appCost:"+appCost+
                "appSize:"+appSize+
                "rating:" + ratings;
    }

    public ProductivityApp(Developer developer, String appName, double appVersion , double appCost, double appSize, int i){

        this.appName = appName;
        this.developer = developer;
        this.appVersion = appVersion;
        this.appCost = appCost;
        this.appSize = appSize;
        this.ratings = ratings;
    }

    public boolean isRecommendedApp(){
        if((this.appCost >= 1.99) && (this.calculateRating() >= 3.0)){return  true;}
        return false;
    }
}
