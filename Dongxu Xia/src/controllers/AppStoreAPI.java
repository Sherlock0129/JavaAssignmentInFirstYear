package controllers;

import models.*;

import java.util.ArrayList;
import java.util.List;


import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import utils.ISerializer;
import utils.Utilities;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Random;


import static utils.RatingUtility.generateRandomRating;


public class AppStoreAPI implements ISerializer {


    private static List<App> apps = new ArrayList<>();

    public boolean addApp(App app) {
        if (isValidIndex(app.getAppName())){
            return false;
        }
        return apps.add(app);
    }


    public App getAppByIndex(int index){
        if (Utilities.isValidIndex(apps, index)){
            return apps.get(index);
        }
        else{
            return null;
        }
    }

    public App getAppByName(String appName){
        int index = retrieveAppIndex(appName);
        if (index != -1){
            return apps.get(index);
        }
        return null;
    }


    public String listAllApps(){
        String listApp = "";
        for (App developer : apps){
            listApp += apps.indexOf(developer) + ": " + developer + "\n";
        }
        if (listApp.equals("")){
            return "No app";
        }
        else {
            return listApp;
        }
    }

    public String listSummaryOfAllApps() {
        String temp = "";
        for (int i = 0; i < apps.size(); i++) {
            temp += i + ": " + apps.get(i).appSummary() + "\n";
        }
        if (temp == "")
            return "No apps";
        else
            return temp;
    }

    //---------------------
    // Update methods
    //---------------------
    public boolean updateAppVersion(String appName, double appVersion, String name, double appCost){
        if (isValidIndex(appName)){
            App appToUpdate = getAppByName(appName);
            appToUpdate.setAppVersion(Double.parseDouble(String.valueOf(appVersion)));
            return true;
        }
        return false;
    }

    //---------------------
    // Delete methods
    //---------------------
    public App deleteAppByIndex(String appName){
        int index = retrieveAppIndex(appName);
        if (index != -1) {
            return apps.remove(index);
        }
        return null;
    }

    //---------------------
    // Validation Methods
    //---------------------
    public boolean isValidIndex(String appName){
        for (App app : apps){
            if (app.getAppName().equalsIgnoreCase(appName)){
                return true;
            }
        }
        return false;
    }

    public int retrieveAppIndex(String developerName){
        for (App app : apps){
            if (app.getAppName().equalsIgnoreCase(developerName)){
                return apps.indexOf(app);
            }
        }
        return -1;
    }

    public String listAllAppsAboveOrEqualAGivenStarRating(int rating){
        String flag = "";
        for (int i=0;i<apps.size();i++) {
            if (apps.get(i).calculateRating() >= rating)
                flag += i+": "+apps.get(i).toString() + "\n";
        }
        if (flag == "")
            return "No apps have a rating of " + rating +" or above";
        else
            return flag;

    }

    public App randomApp(){
        Random random = new Random();
        return apps.get(random.nextInt(apps.size()));
    }

    public String searchByAppName(String appName) {
        String matchingApp = "";
        for(App app : apps) {
            if (app.getAppName().toUpperCase().contains(appName.toUpperCase())) {
                matchingApp += apps.indexOf(app) + ": " + app + "\n";
            }
        }

        if (matchingApp.equals("")){
            return "No products match your search";
        }
        else{
            return matchingApp;
        }
    }

    public void sortAppsByNameAscending()
    {
        for (int i = apps.size() -1; i >= 0; i--)
        {
            int highestIndex = 0;
            for (int j = 0; j <= i; j++)
            {
                if (apps.get(j).getAppName().compareTo(apps.get(highestIndex).getAppName()) > 0) {
                    highestIndex = j;
                }
            }
            swapApp((ArrayList<App>) apps, i, highestIndex);
        }
    }

    private void swapApp(ArrayList<App> employees, int i, int j) {
        App smaller = apps.get(i);
        App bigger = apps.get(j);

        apps.set(i,bigger);
        apps.set(j,smaller);
    }

    public String listAllRecommendedApps() {
        String temp = "";
        for (int i = 0; i < apps.size(); i++) {
            if (apps.get(i).isRecommendedApp())
                temp += i + ": " + apps.get(i).toString() + "\n";
        }
        if (temp == "")
            return "No recommended apps";
        else
            return temp;

    }

    public String listAllEducationApps(){
        String temp = "";
        for (int i = 0;i<apps.size();i++){
            if (apps.get(i) instanceof  EducationApp)
                temp += i+": "+apps.get(i).toString()+"\n";
        }
        if (temp == "")
            return "No Game apps";
        else
            return temp;
    }

    public String listAllProductivityApps(){
        String temp = "";
        for (int i = 0;i<apps.size();i++){
            if (apps.get(i) instanceof ProductivityApp)
                temp += i+": "+apps.get(i).toString()+"\n";
        }
        if (temp == "")
            return "No Game apps";
        else
            return temp;
    }

    //---------------------
    // Getters/Setters
    //---------------------
    public List<App> getApps() {
        return apps;
    }

    //---------------------
    // Persistence Methods
    //---------------------
    /**
     * The load method uses the XStream component to read all the objects from the xml
     * file stored on the hard disk.  The read objects are loaded into the associated ArrayList
     *
     * @throws Exception An exception is thrown if an error occurred during the load e.g. a missing file.
     */

    public static void load() throws Exception {
        //list of classes that you wish to include in the serialisation, separated by a comma
        Class<?>[] classes = new Class[]{App.class, EducationApp.class, GameApp.class, ProductivityApp.class, Rating.class};

        //setting up the xstream object with default security and the above classes
        XStream xstream = new XStream(new DomDriver());
        XStream.setupDefaultSecurity(xstream);
        xstream.allowTypes(classes);

        //doing the actual serialisation to an XML file
        ObjectInputStream in = xstream.createObjectInputStream(new FileReader(fileName()));
        apps = (List<App>) in.readObject();
        in.close();
    }

    public static void save() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter(fileName()));
        out.writeObject(apps);
        out.close();
    }

    public static String fileName(){
        return "apps.xml";
    }


    public void simulateRatings(){
        for (App app :apps) {
            App.addRating(generateRandomRating());
        }
    }

    public int numberOfApps() {
        return apps.size();
    }


    public boolean isValidIndex(int index) {
        return (index >= 0) && (index < apps.size());
    }



}
