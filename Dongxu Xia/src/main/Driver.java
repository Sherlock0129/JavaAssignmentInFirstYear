package main;

import controllers.AppStoreAPI;
import controllers.DeveloperAPI;
import models.*;
import utils.ScannerInput;

public class Driver {

    //TODO Some skeleton code has been given to you.
    //     Familiarise yourself with the skeleton code...run the menu and then review the skeleton code.
    //     Then start working through the spec.

    private DeveloperAPI developerAPI = new DeveloperAPI();
    private AppStoreAPI appStoreAPI = new AppStoreAPI();

    public static void main(String[] args) {
        new Driver().start();
    }

    public void start() {
        loadAllData();
        runMainMenu();
    }

    private int mainMenu() {
        System.out.println("""
                 -------------App Store------------
                |  1) Developer - Management MENU  |
                |  2) App - Management MENU        |
                |  3) Reports MENU                 |
                |----------------------------------|
                |  4) Search                       |
                |  5) Sort                         |
                |----------------------------------|
                |  6) Recommended Apps             |
                |  7) Random App of the Day        |
                |  8) Simulate ratings             |
                |----------------------------------|
                |  20) Save all                    |
                |  21) Load all                    |
                |----------------------------------|
                |  0) Exit                         |
                 ----------------------------------""");
        return ScannerInput.validNextInt("==>> ");
    }

    private void runMainMenu() {
        int option = mainMenu();
        while (option != 0) {
            switch (option) {
                case 1 -> runDeveloperMenu();
                case 2 -> runAppMenu();
                case 3 -> runReportsMenu();
                case 4 -> searchAppsBySpecificCriteria();
                case 5 -> sortAppsByNameAscending();
                case 6 -> System.out.println(appStoreAPI.listAllRecommendedApps());
                case 7 -> System.out.println(appStoreAPI.randomApp().appSummary());
                case 8 -> simulateRatings();
                case 20 -> saveAllData();
                case 21 -> loadAllData();
                default -> System.out.println("Invalid option entered: " + option);
            }
            ScannerInput.validNextLine("\n Press the enter key to continue");
            option = mainMenu();
        }
        exitApp();
    }

    private void exitApp() {
        saveAllData();
        System.out.println("Exiting....");
        System.exit(0);
    }

    //--------------------------------------------------
    //  Developer Management - Menu Items
    //--------------------------------------------------
    private int developerMenu() {
        System.out.println("""
                 -------Developer Menu-------
                |   1) Add a developer       |
                |   2) List developer        |
                |   3) Update developer      |
                |   4) Delete developer      |
                |   0) RETURN to main menu   |
                 ----------------------------""");
        return ScannerInput.validNextInt("==>> ");
    }

    private void runDeveloperMenu() {
        int option = developerMenu();
        while (option != 0) {
            switch (option) {
                case 1 -> addDeveloper();
                case 2 -> System.out.println(developerAPI.listDevelopers());
                case 3 -> updateDeveloper();
                case 4 -> deleteDeveloper();
                default -> System.out.println("Invalid option entered" + option);
            }
            ScannerInput.validNextLine("\n Press the enter key to continue");
            option = developerMenu();
        }
    }

    private void addDeveloper() {
        String developerName = ScannerInput.validNextLine("Please enter the developer name: ");
        String developerWebsite = ScannerInput.validNextLine("Please enter the developer website: ");

        if (developerAPI.addDeveloper(new Developer(developerName, developerWebsite))) {
            System.out.println("Add successful");
        } else {
            System.out.println("Add not successful");
        }
    }

    private void updateDeveloper() {
        System.out.println(developerAPI.listDevelopers());
        Developer developer = readValidDeveloperByName();
        if (developer != null) {
            String developerWebsite = ScannerInput.validNextLine("Please enter new website: ");
            if (developerAPI.updateDeveloperWebsite(developer.getDeveloperName(), developerWebsite))
                System.out.println("Developer Website Updated");
            else
                System.out.println("Developer Website NOT Updated");
        } else
            System.out.println("Developer name is NOT valid");
    }

    private void deleteDeveloper() {
        String developerName = ScannerInput.validNextLine("Please enter the developer name: ");
        if (developerAPI.removeDeveloper(developerName) != null) {
            System.out.println("Delete successful");
        } else {
            System.out.println("Delete not successful");
        }
    }



    private int appMenu() {
        System.out.println("""
                 -------App Menu-------------
                |   1) Add a GameApp         |
                |   2) Add an EducationApp   |
                |   3) Add a ProductivityApp |
                |   4) Update app            |
                |   5) Delete app            |
                |   0) RETURN to main menu   |
                 ----------------------------""");
        return ScannerInput.validNextInt("==>> ");
    }


    private void runAppMenu() {
        int option = appMenu();
        while (option != 0) {
            switch (option) {
                case 1 -> addGameApp();
                case 2 -> addEducationApp();
                case 3 -> addProductivityApp();
                case 4 -> updateApp();
                case 5 -> deleteApp();
                default -> System.out.println("Invalid option entered" + option);
            }
            ScannerInput.validNextLine("\n Press the enter key to continue");
            option = appMenu();
        }
    }

    private void addGameApp(){
        System.out.println(developerAPI.listDevelopers());
        Developer developer = readValidDeveloperByName();


        String appName = ScannerInput.validNextLine("Please enter the App name: ");
        double appVersion = Double.parseDouble(ScannerInput.validNextLine("Please enter the App version: "));
        double appCost = Double.parseDouble(ScannerInput.validNextLine("Please enter the App cost: "));
        double appSize = Double.parseDouble(ScannerInput.validNextLine("Please enter the app size: "));
        boolean isMultiplayer = Boolean.parseBoolean(ScannerInput.validNextLine("Is it a multiplayer game? "));

        if (developer != null){
            if (appStoreAPI.addApp(new GameApp(developer, appName, appVersion ,appCost ,appSize,isMultiplayer))){
                System.out.println("Add successfully");
            }else {
                System.out.println("Add not successfully");
            }

        }else{System.out.println("The developer does not exist");}

        if (appStoreAPI.addApp(new GameApp(developer,appName, appVersion, appCost , appSize , isMultiplayer))) {
            System.out.println("Add successful");
        } else {
            System.out.println("Add not successful");
        }
    }

    private void addEducationApp(){
        System.out.println(developerAPI.listDevelopers());
        Developer developer = readValidDeveloperByName();

        String appName = ScannerInput.validNextLine("Please enter the App name: ");
        double appVersion = Double.parseDouble(ScannerInput.validNextLine("Please enter the App version: "));
        double appCost = Double.parseDouble(ScannerInput.validNextLine("Please enter the App cost: "));
        double appSize = Double.parseDouble(ScannerInput.validNextLine("Please enter the app size: "));
        int level = Integer.parseInt(ScannerInput.validNextLine("Please enter the app level: "));

        if (developer != null){
            if (appStoreAPI.addApp(new EducationApp(developer, appName, appVersion ,appCost ,appSize, level))){
                System.out.println("Add successfully");
            }else {
                System.out.println("Add not successfully");
            }

        }else{System.out.println("The developer does not exist");}

        if (appStoreAPI.addApp(new EducationApp(developer,appName, appVersion, appCost , appSize , level))) {
            System.out.println("Add successful");
        } else {
            System.out.println("Add not successful");
        }
    }

    private void addProductivityApp(){
        System.out.println(developerAPI.listDevelopers());
        Developer developer = readValidDeveloperByName();

        String appName = ScannerInput.validNextLine("Please enter the App name: ");
        double appVersion = Double.parseDouble(ScannerInput.validNextLine("Please enter the App version: "));
        double appCost = Double.parseDouble(ScannerInput.validNextLine("Please enter the App cost: "));
        double appSize = Double.parseDouble(ScannerInput.validNextLine("Please enter the app size: "));

        if (developer != null){
            if (appStoreAPI.addApp(new ProductivityApp(developer, appName, appVersion ,appCost ,appSize, 10))){
                System.out.println("Add successfully");
            }else {
                System.out.println("Add not successfully");
            }

        }else{System.out.println("The developer does not exist");}

        if (appStoreAPI.addApp(new ProductivityApp(developer,appName, appVersion, appCost , appSize, 10))) {
            System.out.println("Add successful");
        } else {
            System.out.println("Add not successful");
        }
    }

    private Developer readValidDeveloperByName() {
        String developerName = ScannerInput.validNextLine("Please enter the developer`s name: ");
        if (developerAPI.isValidDeveloper(developerName)) {
            return developerAPI.getDeveloperByName(developerName);
        } else {
            return null;
        }
    }
    private void updateApp(){
        System.out.println(appStoreAPI.listAllApps());
        App app = readValidAppByName();
        if (app != null) {
            double appVersion = Double.parseDouble(ScannerInput.validNextLine("Please enter new version: "));
            double appCost = Double.parseDouble(ScannerInput.validNextLine("Please enter new cost:"));
            String appName = String.valueOf(Double.parseDouble(ScannerInput.validNextLine("Please enter new name:")));
            if (appStoreAPI.updateAppVersion(app.getAppName(), appVersion,appName,appCost))
                System.out.println("App version Updated");
            else
                System.out.println("App version NOT Updated");
        } else
            System.out.println("App name is NOT valid");
    }

    private void deleteApp(){
        String appName = ScannerInput.validNextLine("Please enter the app name: ");
        if (appStoreAPI.deleteAppByIndex(appName) != null) {
            System.out.println("Delete successful");
        } else {
            System.out.println("Delete not successful");
        }
    }

    private App readValidAppByName() {
        String appName = ScannerInput.validNextLine("Please enter the app`s name: ");
        if (appStoreAPI.isValidIndex(appName)) {
            return appStoreAPI.getAppByName(appName);
        } else {
            return null;
        }
    }

    private int reportsMenu() {
        System.out.println("""
                 -------Reports Menu-------------
                |   1) Apps Overview            |
                |   2) Developers Overview      |
                |   0) RETURN to main menu      |
                 ----------------------------""");
        return ScannerInput.validNextInt("==>> ");
    }
    private void runReportsMenu(){
        int option = reportsMenu();
        while (option != 0) {
            switch (option) {
                case 1 -> System.out.println(appStoreAPI.listAllApps());
                case 2 -> System.out.println(developerAPI.listDevelopers());
                default -> System.out.println("Invalid option entered" + option);
            }
            ScannerInput.validNextLine("\n Press the enter key to continue");
            option = reportsMenu();
        }
    }



    //--------------------------------------------------
    // TODO UNCOMMENT THIS CODE as you start working through this class
    //--------------------------------------------------
    private void searchAppsBySpecificCriteria() {
        System.out.println("""
                What criteria would you like to search apps by:
                  1) App Name
                  2) Developer Name
                  3) Rating (all apps of that rating or above)""");
        int option = ScannerInput.validNextInt("==>> ");
        switch (option) {
            // TODO Search methods below
             case 1 -> searchAppsByName();
             case 2 -> searchAppsByDeveloper(readValidDeveloperByName());
            // case 3 -> searchAppsEqualOrAboveAStarRating();
             default -> System.out.println("Invalid option");
        }
    }

    private void searchAppsByName() {
        String appName = ScannerInput.validNextLine("Please enter a app name to search by:");
        System.out.println(appStoreAPI.searchByAppName(appName));
    }

    private void searchAppsByDeveloper(Developer developer) {
        String developerName = ScannerInput.validNextLine("Please enter a developer name to search by:");
        System.out.println(developerAPI.searchByDeveloperName(developerName));
    }

    private void sortAppsByNameAscending() {
        appStoreAPI.sortAppsByNameAscending();
        System.out.println(appStoreAPI.listAllApps());
    }
    //--------------------------------------------------
    // TODO UNCOMMENT THIS COMPLETED CODE as you start working through this class
    //--------------------------------------------------
    private void simulateRatings() {
        // simulate random ratings for all apps (to give data for recommended apps and reports etc).
        if (appStoreAPI.numberOfApps() > 0) {
            System.out.println("Simulating ratings...");
            appStoreAPI.simulateRatings();
            System.out.println(appStoreAPI.listSummaryOfAllApps());
        } else {
            System.out.println("No apps");
        }
    }

    //--------------------------------------------------
    //  Persistence Menu Items
    //--------------------------------------------------

    private void saveAllData() {
        try {
            DeveloperAPI.save();
            AppStoreAPI.save();
        } catch (Exception e) {
            System.err.println("Error writing to file: " + e);
        }
    }

    private void loadAllData() {
        try {
            DeveloperAPI.load();
            AppStoreAPI.load();
        } catch (Exception e) {
            System.err.println("Error reading from file: " + e);
        }
    }


}
