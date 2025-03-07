package com.ui.tests;
import com.ui.pages.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.*;
import utils.JSONFileManager;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;


public class TestBase {
    JSONFileManager jsonFileManager = new JSONFileManager("data/credentials.json");

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

    @BeforeClass
    @Parameters("browser")
    public void browserSetup(@Optional("chrome")String browser) {

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
        driver.get(config.getProperty("login.url"));
        threadLocalDriver.set(driver);
    }

    public WebDriver getDriver() {
        if (threadLocalDriver.get() != null) {
            return threadLocalDriver.get();
        }
        throw new IllegalStateException("Driver is not initialized");
    }
    public void login(){
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.enterUsername((jsonFileManager.getDataAsString("credentials.username")))
                .enterPassword((jsonFileManager.getDataAsString("credentials.password")))
                .clickOnLoginButton();
    }
    @AfterMethod
    public void refresh(){
        if(getDriver() != null) {
            getDriver().navigate().refresh();
            //threadLocalDriver.remove();
        }
    }
    @AfterClass
    public void tearDown(){
        if(getDriver() != null){
            getDriver().quit();
        }
    }
}