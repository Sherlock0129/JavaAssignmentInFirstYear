package models;

import utils.Utilities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public abstract class App {

    public Developer developer;
    public String appName = "No app name";
    public double appSize = 0;
    public double appVersion = 1.0;
    public double appCost = 0;
    public static List<Rating> ratings = new ArrayList<>();

    public App(Developer developer, String appName, double appSize, double appVersion, double appCost) {
        this.developer = developer;
        this.appName = appName;
        if (Utilities.validRange(appSize,1,1000))
            this.appSize = appSize;
        if (appVersion >= 1.0)
            this.appVersion = appVersion;
        if (appCost >= 0)
            this.appCost = appCost;
    }


    public App(String appName, double appVersion, double appCost, double appSize) {
        this.appName = appName;
        this.appVersion = appVersion;
        this.appCost = appCost;
        this.appSize = appSize;
    }

    public App() {
    }
    public Developer getDeveloper() {
        return developer;
    }
    public Developer setDeveloper(Developer developerSphero){return developer;}

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public double getAppVersion() {
        return appVersion;
    }
    public void setAppVersion(double appVersion) {
        this.appVersion = appVersion;
    }

    public double getAppCost() {return appCost;}

    public void setAppCost(double appCost) {
        this.appCost = appCost;
    }

    public  double getAppSize(){return appSize;}

    public  void setAppSize(double appSize){this.appSize = appSize;}

    public double calculateRating() {
        double sum = 0;
        if (ratings.size() > 0) {
            for (int i = 0; i < ratings.size(); i++) {
                sum += ratings.get(i).getNumberOfStars();
            }
            return sum / ratings.size();

        } else
            return 0;
    }

    public static boolean addRating(Rating rating) {
        return ratings.add(rating);
    }

    public String listRatings() {

        if (ratings.isEmpty())
            return "No ratings added yet";
        else {
            String listOfRatings = "";
            for (int i = 0; i < ratings.size(); i++) {
                listOfRatings += i + ": " + ratings.get(i).toString() + "\n";
            }
            return listOfRatings;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        App app = (App) o;
        return Objects.equals(appName, app.appName) && Objects.equals(appVersion, app.appVersion);
    }

    public String appSummary() {
        return appName + "(V" + appVersion + ") by " + developer + ", €" + appCost + ". Rating: " + calculateRating();
    }

    public abstract String toSring();

    public abstract boolean isRecommendedApp();

    public String toString() {
        String temp = "";
        for (int i = 0;i < ratings.size();i++)
            temp += ratings.get(i).toString() + "\n";
        return appName + "(Version " + appVersion + ")" + "\n"
                + "Developer: " + developer+ "\n"
                + "AppSize: " + appSize + "MB" + "\n"
                + "Cost: "  + "€" + appCost + "\n"
                + "Ratings: " + calculateRating() + "\n" + temp;
    }



    public List<Rating> getRatings() {
        return ratings;
    }
}

