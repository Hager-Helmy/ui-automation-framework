package com.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class LoginPage {

    WebDriver driver;

    public LoginPage(WebDriver driver){
        this.driver = driver;
    }

    //Identify Locators
    private By userName = By.xpath("//input[@name='username']");
    private By userPassword = By.xpath("//input[@name='password']");
    private By loginButton = By.xpath("//button[@type='submit']");


    //Fill input data
    public LoginPage enterUsername(String username) {
        driver.findElement(userName).sendKeys(username);
        return this;
    }

    public LoginPage enterPassword(String password) {
        driver.findElement(userPassword).sendKeys(password);
        return this;
    }

    //Clicks
    public AddNewUserPage clickOnLoginButton1(){
        driver.findElement(loginButton).click();
        return new AddNewUserPage(driver);
    }

    public HomePage clickOnLoginButton(){
        driver.findElement(loginButton).click();
        return new HomePage(driver);
    }



}
