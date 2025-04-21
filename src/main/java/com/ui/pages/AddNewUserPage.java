package com.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AddNewUserPage {
    private WebDriver driver;
    private WebDriverWait wait;
    public AddNewUserPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }
//Locators
    private By adminButton = By.xpath("//span[text()='Admin']");
    private By addButton = By.xpath("//div[@class=\"orangehrm-header-container\"]//button");
    private By userRoleDropDownArrow = By.xpath("(//div[contains(@class,'oxd-select-text--after')])[1]");
    private By employeeName = By.xpath("(//div[contains(@class, 'oxd-input-group')]//input)[1]");
    private By statusDropDownArrow = By.xpath("(//div[contains(@class,'oxd-select-text--after')])[2]");
    private By userName = By.xpath("(//div[contains(@class, 'oxd-input-group')]//input)[2]");
    private By password = By.xpath("(//div[contains(@class, 'oxd-input-group')]//input)[3]");
    private By confirmPassword = By.xpath("(//div[contains(@class, 'oxd-input-group')]//input)[4]");
    private By saveButton = By.xpath("//div[contains(@class, 'oxd-form-actions')]/button[@type = 'submit']");
    private By successNotification = By.xpath("//div/p[text()='Success']");


//Actions
public AddNewUserPage clickOnAdminButton(){
    driver.findElement(adminButton).click();
    return this;
}
public AddNewUserPage clickOnAddButton()  {
    wait.until(ExpectedConditions.visibilityOfElementLocated(addButton)).click();
    return this;
}

public AddNewUserPage openUserRoleDropDown(){
    driver.findElement(userRoleDropDownArrow).click();
    return this;
}
public AddNewUserPage selectAdminUserFromDropDown(String text){
    By selectUserRoleOption = By.xpath("//div[contains(@class, 'oxd-select-option')]//span[text()='"+ text +"']");
    wait.until(ExpectedConditions.visibilityOfElementLocated(selectUserRoleOption));
    driver.findElement(selectUserRoleOption).click();
    return this;
}

public AddNewUserPage enterEmployeeName(String name){
    driver.findElement(employeeName).sendKeys(name);
    return this;
}
public AddNewUserPage selectFromExistingEmployee(int index) {
    By existingEmployeeOption = By.xpath("//div[@role='option'][" + index +"]");
    wait.until(ExpectedConditions.presenceOfElementLocated(existingEmployeeOption)).click();
    return this;
}

    public AddNewUserPage openStatusDropDownArrow(){
    driver.findElement(statusDropDownArrow).click();
    return this;
    }
    public AddNewUserPage selectFromStatusDropDown(String text){
    By status = By.xpath("//div[contains(@class, 'oxd-select-dropdown')]//span[text()='"+ text +"']");
    driver.findElement(status).click();
    return this;
}
public AddNewUserPage enterUsername(String username){
    driver.findElement(userName).sendKeys(username);
    return this;
}
public AddNewUserPage enterPassword(String passwordText){
    driver.findElement(password).sendKeys(passwordText);
    return this;
}
public AddNewUserPage confirmPassword(String confirmPasswordText){
    driver.findElement(confirmPassword).sendKeys(confirmPasswordText);
    return this;
}

public SystemUsersPage clickOnSaveButton(){
    driver.findElement(saveButton).click();
    return new SystemUsersPage(driver);
}
public String getSuccessMessage(){
    return wait.until(ExpectedConditions.visibilityOfElementLocated(successNotification)).getText();

}
}
