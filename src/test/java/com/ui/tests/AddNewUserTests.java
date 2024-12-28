package com.ui.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ui.pages.AddNewUserPage;
import com.ui.pages.LoginPage;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.Credentials;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class AddNewUserTests extends TestBase {
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

    @BeforeMethod
    public void validateUserLoginSuccessfully(){
        getCredentials();
        String username = credentials.getUsername();
        String password = credentials.getPassword();
        LoginPage loginPage = new LoginPage(getDriver());

        loginPage.enterUsername(username)
                .enterPassword(password)
                .clickOnLoginButton1();


    }

    @Test
    public void addNewUserTest() {
        AddNewUserPage addNewUserPage = new AddNewUserPage(getDriver());
        addNewUserPage.clickOnAdminButton()
                      .clickOnAddButton()
                      .openUserRoleDropDown()
                      .selectAdminUserFromDropDown("ESS")
                      .enterEmployeeName("A")
                      .selectFromExistingEmployee(2)
                      .openStatusDropDownArrow()
                      .selectFromStatusDropDown("Enabled")
                      .enterUsername("TestUser78362")
                      .enterPassword("testtesttets4656")
                      .confirmPassword("testtesttets4656")
                      .clickOnSaveButton();
        //Assertions
        String expectedSuccessMessage = "Success";
        Assert.assertEquals(addNewUserPage.getSuccessMessage(), expectedSuccessMessage);

        String expectedUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/admin/viewSystemUsers";
        new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                .until(ExpectedConditions.urlToBe(expectedUrl));
        String actualUrl = getDriver().getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl);

    }
}
