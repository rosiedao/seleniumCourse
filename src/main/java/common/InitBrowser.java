package common;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.testng.annotations.AfterMethod;

import java.time.Duration;

public class InitBrowser {
        public String driverPath = "/Users/dungdao/Documents/Setup/chromedriver/chromedriver";
        public WebDriver driver;

    public void startBrowser(String browser) {
        if(browser.equals("safari")){
            WebDriverManager.safaridriver().setup();
            SafariOptions option = new SafariOptions();
            driver = new SafariDriver(option);
            driver.manage().window().maximize();
        }else {
            WebDriverManager.chromedriver().setup();
            ChromeOptions option = new ChromeOptions();
            option.addArguments("--disable-notifications");
            driver = new ChromeDriver(option);
            driver.manage().window().maximize();
        }
    }
    public void navigateURL(String url){
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));
    }
    @AfterMethod
    public void endTest() {
        driver.quit();
    }
}
