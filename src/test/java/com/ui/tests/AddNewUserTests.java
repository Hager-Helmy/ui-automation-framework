package com.ui.tests;
import com.ui.pages.AddNewUserPage;
import io.qameta.allure.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.time.Duration;
import java.util.List;
import utils.JSONFileManager;


public class AddNewUserTests extends TestBase {

    JSONFileManager jsonFileManager = new JSONFileManager("data/testData.json");
    @BeforeMethod
    public void setup(){
        login();
    }

    @Feature("Main Feature- Add New User")
    @Story("Add New User")
    @Description("This test for validating user is added successfully")
    @Owner("Hager")
    @Link(name = "Website",url ="https://opensource-demo.orangehrmlive.com/web/index.php/auth/login" )
    @Test
    public void addNewUserTest() throws InterruptedException {
        List<String> userRoles = jsonFileManager.getDataAsList("addNewUser.userRole", String.class);
        List<String> status = jsonFileManager.getDataAsList("addNewUser.employeeStatus", String.class);
        List<String> employeeName = jsonFileManager.getDataAsList("addNewUser.employeeName", String.class);
        List<Integer> employeeIndex = jsonFileManager.getDataAsList("addNewUser.existingEmployee", Integer.class);
        AddNewUserPage addNewUserPage = new AddNewUserPage(getDriver());
        addNewUserPage.clickOnAdminButton()
                      .clickOnAddButton()
                      .openUserRoleDropDown()
                      .selectAdminUserFromDropDown(userRoles.get(1))
                      .enterEmployeeName(employeeName.get(0))
                     .selectFromExistingEmployee(employeeIndex.get(1))
                      .openStatusDropDownArrow()
                      .selectFromStatusDropDown(status.get(0))
                      .enterUsername(jsonFileManager.getDataAsString("addNewUser.userName"))
                      .enterPassword(jsonFileManager.getDataAsString("addNewUser.password"))
                      .confirmPassword(jsonFileManager.getDataAsString("addNewUser.password"))
                      .clickOnSaveButton();
        //Assertions
        String expectedSuccessMessage = "Success";
        Assert.assertEquals(addNewUserPage.getSuccessMessage(), expectedSuccessMessage);

        String expectedUrl = config.getProperty("system.users.url");
        new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                .until(ExpectedConditions.urlToBe(expectedUrl));
        String actualUrl = getDriver().getCurrentUrl();
                Assert.assertEquals(actualUrl, expectedUrl);

    }
}
