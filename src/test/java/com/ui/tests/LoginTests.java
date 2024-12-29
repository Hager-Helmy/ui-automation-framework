package com.ui.tests;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ui.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.Credentials;
import io.qameta.allure.*;

import java.io.File;
import java.io.IOException;

public class LoginTests extends TestBase{
private Credentials credentials;

public void getCredentials(){

    ObjectMapper mapper = new ObjectMapper();
    try {
        credentials = mapper.readValue(new File("src\\test\\java\\data\\credentials.json"), Credentials.class);
    } catch (IOException e) {
        e.printStackTrace();
        throw new RuntimeException("Failed to load credentials from JSON file.");
    }
}


//Successful Login
    @Description("Test the Login Functionality")
    @Test
    public void validateUserLoginSuccessfully(){
    getCredentials();
    String username = credentials.getUsername();
    String password = credentials.getPassword();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.enterUsername(username)
                .enterPassword(password)
                .clickOnLoginButton();

        // Validate the URL after login
        String expectedUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index";
        String actualUrl = getDriver().getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl, "Login failed, the URL is not as expected.");
    }

    @Description("Test the Logout Functionality")
    @Test
    public void logoutTest(){
        getCredentials();
        String username = credentials.getUsername();
        String password = credentials.getPassword();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.enterUsername(username)
                .enterPassword(password)
                .clickOnLoginButton()
                .openTheDropDownMenu()
                .clickOnLogout();
        String expectedUrl =  "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
        String actualUrl = getDriver().getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl, "Login failed, the URL is not as expected.");

    }

}
