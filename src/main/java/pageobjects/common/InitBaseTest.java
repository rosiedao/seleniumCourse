package pageobjects.common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import pageobjects.login.LandingPage;

public class InitBaseTest {
    public String driverPath = "/Users/dungdao/Documents/Setup/chromedriver/chromedriver";
    public WebDriver driver;
    public LandingPage landingPage;

    public void startBrowser() {
        System.setProperty("webdriver.chrome.driver", driverPath);
        ChromeOptions option = new ChromeOptions();
        option.setBrowserVersion("113");
        option.addArguments("--disable-notifications");
        driver = new ChromeDriver(option);
        driver.manage().window().maximize();
    }

    @BeforeClass
    public LandingPage launchApplication(){
        startBrowser();
        landingPage = new LandingPage(driver);
        landingPage.navigateURL("https://rahulshettyacademy.com/client");
        return landingPage;
    }
    @AfterClass
    public void endTest() {
        driver.quit();
    }
}
