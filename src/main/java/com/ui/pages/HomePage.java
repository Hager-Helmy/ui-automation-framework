package com.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage  {

    WebDriver driver;

    public HomePage(WebDriver driver){
        this.driver = driver;
    }

    private By logOutButton = By.xpath("//a[@href='/web/index.php/auth/logout']");
    private By profileDropDownMenu = By.xpath("//i[contains(@class, 'bi-caret-down-fill')]");

    public HomePage openTheDropDownMenu(){
        driver.findElement(profileDropDownMenu).click();
        return this;
    }

    public LoginPage clickOnLogout(){
        driver.findElement(logOutButton).click();
        return new LoginPage(driver);

    }
}
