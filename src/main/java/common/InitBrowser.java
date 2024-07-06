package common;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.annotations.AfterMethod;

import java.time.Duration;
import java.util.NoSuchElementException;

public class InitBrowser {
    public WebDriver driver;
    public JavascriptExecutor js;
    public FluentWait<WebDriver> fluentWait;

    public void startBrowser(String browser) {
        if(browser.equals("safari")){
            WebDriverManager.safaridriver().setup();
            SafariOptions option = new SafariOptions();
            driver = new SafariDriver(option);
            driver.manage().window().maximize();
        } else if (browser.equals("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions option = new FirefoxOptions();
            driver = new FirefoxDriver(option);
            driver.manage().window().maximize();
        } else {
            WebDriverManager.chromedriver().setup();
            ChromeOptions option = new ChromeOptions();
            option.addArguments("--disable-notifications");
            driver = new ChromeDriver(option);
            driver.manage().window().maximize();
        }
        js = (JavascriptExecutor) driver;
        fluentWait = new FluentWait<>(driver);
        //poll = find element again
        fluentWait.withTimeout(Duration.ofSeconds(10)).pollingEvery(Duration.ofSeconds(1));
    }
    public void navigateURL(String url){
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
    public void sleep(long second){
        try {
            Thread.sleep(second * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    @AfterMethod
    public void endTest() {
        driver.quit();
    }
}
