package com.opencart.managers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class DriverManager {
    private static String webDriverType = "Chrome";
    private static DriverManager instance;
    private WebDriver driver;

    private DriverManager(){
        switch (webDriverType.toUpperCase()){
            case "CHROME":
                driver = new ChromeDriver();
                System.out.println("Crome Driver Initiated !");
                break;

            case "FIREFOX":
                driver = new FirefoxDriver();
                System.out.println("Firefox Driver Initiated !");
                break;

            case "EDGE":
                driver = new EdgeDriver();
                System.out.println("Edge Driver Initiated !");
                break;

            case "SAFARI":
                driver = new SafariDriver();
                System.out.println("Safari Driver Initiated !");
                break;
            default:
                System.out.println("There is not defined driver" + webDriverType);

        }
    }
    public static DriverManager getInstance(){
        if (instance == null){
            instance = new DriverManager();
        }
        return instance;

    }
    public WebDriver getDriver() {
        if (driver == null) {
            getInstance();
        }
        return driver;
    }

    public void quitTheDriver(){
        driver.quit();
        driver = null;
        instance = null;
        System.out.println("The driver is quit and the instance is reset");
    }
}

