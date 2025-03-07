package com.ui.tests;
import com.ui.pages.HomePage;
import io.qameta.allure.Description;
import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends TestBase{

//Successful Login
    @Description("This test for validating successful Login")
    @Step("Login")
    @Owner("Hager")
    @Link(name = "Website",url ="https://opensource-demo.orangehrmlive.com/web/index.php/auth/login" )
    @Test
    public void validateUserLoginSuccessfully(){
        login();
        // Validate the URL after login
        String expectedUrl = config.getProperty("dashboard.url");
        String actualUrl = getDriver().getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl, "Login failed, the URL is not as expected.");
    }

    @Description("This test for validating successful Logout")
    @Step("Logout")
    @Owner("Hager")
    @Link(name = "Website",url ="https://opensource-demo.orangehrmlive.com/web/index.php/" )
    @Test
    public void validateUserLogoutTest(){
        HomePage homePage = new HomePage(getDriver());
                 homePage.openTheDropDownMenu()
                .clickOnLogout();
        String expectedUrl = config.getProperty("login.url");
        String actualUrl = getDriver().getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl, "Logout failed, the URL is not as expected.");

    }

}
