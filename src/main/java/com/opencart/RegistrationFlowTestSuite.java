package com.opencart;

import com.opencart.managers.DataFakerManager;
import com.opencart.managers.DriverManager;
import com.opencart.pageobjects.HomePage;
import com.opencart.pageobjects.RegisterPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;

public class RegistrationFlowTestSuite {

    WebDriver driver;
    HomePage homePage;
    RegisterPage registerPage;
    static int counter = 0;

    @BeforeEach
    public void executeTheCodeBeforeEachTestFromThisClass() {
        driver = DriverManager.getInstance().getDriver();
        driver.get("https://opencart.lab01.tech-yard.club/en-gb?route=common/home");
        homePage = new HomePage(driver);
        registerPage = new RegisterPage(driver);
        counter++;
        System.out.println("The test number " + counter + " started!");
    }

    @Test
    @DisplayName("The url contains success keyword after registration with valid data")
    public void registerFlowRedirectsTheUserToTheCorrectUrl() throws InterruptedException {
        homePage.navigateToRegisterPageFromHeaderMenu();

        String firstName = DataFakerManager.getRandomName();
        String lastName = DataFakerManager.getRandomName();
        String randomEmail = DataFakerManager.getRandomEmail();
        String password = DataFakerManager.getRandomPassword(5, 9);

        registerPage.fillInTheRegisterForm(firstName, lastName, randomEmail, password);
        registerPage.switchOnThePrivacyCheckbox(driver);
        registerPage.clickOnContinueButton();
        Thread.sleep(2000);
        System.out.println(driver.getCurrentUrl());

        boolean urlContainsTheCorrectKeywords = driver.getCurrentUrl().contains("club/en-gb?route=account/success&customer");
        Assertions.assertTrue(urlContainsTheCorrectKeywords, "The Url " + driver.getCurrentUrl() + " contains the correct keywords");
    }


    @Test
    @DisplayName("The url contains register keyword when privacy policy is not accepted")
    public void registerFlowIsBlockedByPrivacyPolicyCheckboxThatIsNotAccepted() throws InterruptedException {
        homePage.navigateToRegisterPageFromHeaderMenu();

        String firstName = DataFakerManager.getRandomName();
        String lastName = DataFakerManager.getRandomName();
        String randomEmail = DataFakerManager.getRandomEmail();
        String password = DataFakerManager.getRandomPassword(5, 9);

        registerPage.fillInTheRegisterForm(firstName, lastName, randomEmail, password);
//        Do not enable the Privacy Checkbox
//        registerPage.switchOnThePrivacyCheckbox(driver);
        registerPage.clickOnContinueButton();
        Thread.sleep(2000);
        System.out.println(driver.getCurrentUrl());

        boolean urlContainsTheCorrectKeywords = driver.getCurrentUrl().contains("club/en-gb?route=account/success&customer");
        Assertions.assertFalse(urlContainsTheCorrectKeywords, "The Url " + driver.getCurrentUrl() + " doesn't contain the correct keywords");

        boolean urlContainsRegisterKeyword = driver.getCurrentUrl().contains("route=account/register");
        Assertions.assertTrue(urlContainsRegisterKeyword, "The url belongs to register page");
    }

    @AfterEach
    public void executeThisMethodAfterEachTestCase() {
        DriverManager.getInstance().quitTheDriver();
        System.out.println("The test number " + counter + " finished!");
    }
}