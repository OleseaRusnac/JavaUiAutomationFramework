package com.opencart;

import com.opencart.managers.DriverManager;
import org.openqa.selenium.*;


public class TestRunner {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = DriverManager.getInstance().getDriver();

        driver.get("https://demo.opencart.com/");

        String currentWindowName = driver.getWindowHandle();

        driver.switchTo().newWindow(WindowType.TAB);

        driver.get("https://demo.opencart.com/");

        WebElement accountIcon = driver.findElement(By.xpath("//i[@class='fas fa-user']"));
        accountIcon.click();

        WebElement registerBtn = driver.findElement(By.xpath("//a[@class='dropdown-item'][normalize-space()='Register']"));
        registerBtn.click();

        WebElement firstNameInput = driver.findElement(By.id("input-firstname"));
        firstNameInput.sendKeys("Olesea");

        WebElement lastNameInput = driver.findElement(By.id("input-lastname"));
        lastNameInput.sendKeys("Rusnac");

        WebElement emailInput = driver.findElement(By.id("input-email"));
        emailInput.sendKeys("olesea@gmail.com");

        WebElement passwordInput = driver.findElement(By.id("input-password"));
        passwordInput.sendKeys("12345qwert!");

        Thread.sleep(2000);

        WebElement privacyCheckbox = driver.findElement(By.cssSelector("input[value='1'][name='agree']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", privacyCheckbox);
        Thread.sleep(1000);

        privacyCheckbox.click();

        WebElement continueBtn = driver.findElement(By.xpath("//button[normalize-space()='Continue']"));
        continueBtn.click();

        Thread.sleep(5000);
        System.out.println(driver.getCurrentUrl());

        driver.close();

        driver.switchTo().window(currentWindowName);

        driver.get("https://demo.opencart.com/");

        driver.quit();

        System.out.println("The execution was finished!");
    }
}