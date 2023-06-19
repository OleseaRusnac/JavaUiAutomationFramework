package com.opencart;

import com.opencart.managers.DriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;

public class TestRunner {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = DriverManager.getInstance().getDriver();
        driver.get("https://mvnrepository.com/");
        String currentWindowName = driver.getWindowHandle();
        driver.switchTo().newWindow(WindowType.TAB);
        driver.get("https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-api");
        Thread.sleep(2000);
        driver.close();
        driver.switchTo().window(currentWindowName);
        driver.get("https://iticket.md/login");
        Thread.sleep(2000);
        driver.quit();
        System.out.println("The execution was finished!");
    }
}