package com.ui.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TestBase {
protected static WebDriver driver;
protected static Properties config;

static {
    config = new Properties();
    try {
        FileInputStream fis = new FileInputStream("src/test/resources/config.properties");
        config.load(fis);
    } catch (IOException e) {
        e.printStackTrace();
    }
}


public void browserSetup(){
    WebDriverManager.chromedriver().setup();
    driver = new ChromeDriver();
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    driver.manage().window().maximize();
    //driver.get(config.getProperty("url"));
    driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
    //System.out.println("URL from config: " + config.getProperty("url"));
}
}
