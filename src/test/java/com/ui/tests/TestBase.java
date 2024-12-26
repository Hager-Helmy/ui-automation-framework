package com.ui.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TestBase {
    protected static Properties config;

    private static ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<>();

    static {
        config = new Properties();
        try {
            FileInputStream fis = new FileInputStream("src/test/resources/config.properties");
            config.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @BeforeMethod
    @Parameters("browser")
    public void browserSetup(String browser) {

        WebDriver driver;
        switch (browser.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            default:
                throw new IllegalArgumentException("unsupported browser" + browser);


        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        //driver.get(config.getProperty("url"));
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        //System.out.println("URL from config: " + config.getProperty("url"));
        threadLocalDriver.set(driver);
    }

    public WebDriver getDriver() {
        if (threadLocalDriver.get() != null) {
            return threadLocalDriver.get();
        }
        throw new IllegalStateException("Driver is not initialized");
    }

    @AfterMethod
    public void tearDown(){
        if(getDriver() != null){
            getDriver().quit();
            threadLocalDriver.remove();
        }
    }
}