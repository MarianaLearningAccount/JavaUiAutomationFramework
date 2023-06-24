package com.opencart.stepdefinitions;

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
        driver.get("https://andreisecuqa.host/");
        homePage = new HomePage(driver);
        registerPage = new RegisterPage(driver);
        counter++;
        System.out.println("The test number " + counter + " started! ");
    }

    @Test
    @DisplayName("The url contains success keyword after registration with valid data")
    public void registerFlowRedirectsTheUserToTheCorrectUrls() throws InterruptedException {

        homePage.navigateToRegisterPageFromHeaderMenu();

        String firstName = DataFakerManager.getRandomName();
        String lastName = DataFakerManager.getRandomName();
        String randomEmail = DataFakerManager.getRandomEmail();
        String password = DataFakerManager.getRandomPassword(4, 20);

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.fillInTheRegisterForm(firstName, lastName, randomEmail, password);
        registerPage.switchOnThePrivacyToggle(driver);
        registerPage.clickOnContinueButton();

        Thread.sleep(2000);
        System.out.println(driver.getCurrentUrl());

        boolean urlContainsTheCorrectKeyWords = driver.getCurrentUrl().contains("/index.php?route=account/success&language");
        String errorMessage = "The Url " + driver.getCurrentUrl() + "contains success keyword";
        Assertions.assertTrue(urlContainsTheCorrectKeyWords, errorMessage);

        Thread.sleep(1000);
        System.out.println(driver.getCurrentUrl());
    }

    @Test
    @DisplayName("The url contains register keyword when privacy policy is not accepted")
    public void registerFlowIsBlockedByPrivacyPolicyToggleThatIsNotAccepted() throws InterruptedException {

        homePage.navigateToRegisterPageFromHeaderMenu();

        String firstName = DataFakerManager.getRandomName();
        String lastName = DataFakerManager.getRandomName();
        String randomEmail = DataFakerManager.getRandomEmail();
        String password = DataFakerManager.getRandomPassword(4, 20);

        registerPage.fillInTheRegisterForm(firstName, lastName, randomEmail, password);
//        Do not enable the Privacy Toggle
//        registerPage.switchOnThePrivacyToggle(driver);
        registerPage.clickOnContinueButton();

        Thread.sleep(500);
        System.out.println(driver.getCurrentUrl());

        boolean urlContainsTheCorrectKeyWords = driver.getCurrentUrl().contains("/index.php?route=account/success&language");
        String errorMessage = "The Url " + driver.getCurrentUrl() + "does not contain success keyword";
        Assertions.assertFalse(urlContainsTheCorrectKeyWords, errorMessage);

        boolean urlContainsRegisterKeyword = driver.getCurrentUrl().contains("register");
        Assertions.assertTrue(urlContainsRegisterKeyword, "The Url belongs to register page");

        Thread.sleep(1000);
        System.out.println(driver.getCurrentUrl());
    }

    @AfterEach
    public void executeThisMethodAfterEachTestCase() {
        DriverManager.getInstance().quitTheDriver();
        System.out.println("The test number " + counter + " finished! ");
    }
}
