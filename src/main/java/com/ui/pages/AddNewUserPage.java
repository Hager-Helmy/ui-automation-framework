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
    private By addButton = By.xpath("//div[@class=\"orangehrm-header-container\"]//button[normalize-space()='Add']");
    private By userRoleDropDownArrow = By.xpath("//label[normalize-space()='User Role']/ancestor::div[contains(@class, 'oxd-input-group')]//div[contains(@class, 'oxd-select-text--after')]");
    private By employeeName = By.xpath("//label[normalize-space()='Employee Name']/ancestor::div[contains(@class, 'oxd-input-group')]//input");
    private By statusDropDownArrow = By.xpath("//label[text()='Status']/ancestor::div[contains(@class, 'oxd-input')]//div[contains(@class, 'oxd-select-text--after')]");
    private By userName = By.xpath("//label[text()='Username']/ancestor::div[contains(@class, 'oxd-input-group')]//input");
    private By password = By.xpath("//label[text()='Password']/ancestor::div[contains(@class, 'oxd-input-group')]//input");
    private By confirmPassword = By.xpath("//label[text()='Confirm Password']/ancestor::div[contains(@class, 'oxd-input-group')]//input");
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
