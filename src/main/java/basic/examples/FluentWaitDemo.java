package basic.examples;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.function.Function;

import static org.testng.AssertJUnit.assertEquals;

public class FluentWaitDemo {
    public String driverPath = "/Users/dungdao/Documents/Setup/chromedriver/chromedriver";
    public WebDriver driver;
    WebDriverWait explicitWait;
    //FluentWait can allow edit polling time
    FluentWait<WebDriver> fluentWait;
    FluentWait<WebElement> elementFluentWait; // Constructor when element is display
    FluentWait<String> stringFluentWait;
    @BeforeClass
    public void startBrowser() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions option = new ChromeOptions();
        option.addArguments("--disable-notifications");
        driver = new ChromeDriver(option);
        driver.manage().window().maximize();
        // Time - Default polling time = 0.5s
        //explicitWait = new WebDriverWait(driver,Duration.ofSeconds(10));
        //Time - Polling: 0.3s
        //explicitWait = new WebDriverWait(driver,Duration.ofSeconds(10),Duration.ofMillis(300));
        fluentWait = new FluentWait<>(driver);

    }
    @Test
    public void run(){
        driver.get("https://the-internet.herokuapp.com/dynamic_loading/1");
        driver.findElement(By.xpath("//button[contains(text(),'Start')]")).click();
        fluentWait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(100))
                .ignoring(NoSuchElementException.class);
        fluentWait.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver driver) {
                if(driver.findElement(By.cssSelector("[id='finish'] h4")).isDisplayed()){
                    return driver.findElement(By.cssSelector("[id='finish'] h4"));
                }else{
                    return null;
                }
            }
        });
        //System.out.println(foo.getText());
        fluentWait.until(new Function<WebDriver, Boolean>() {
            @Override
            public Boolean apply(WebDriver webDriver) {
                return webDriver.findElement(By.cssSelector("[id='finish'] h4")).isDisplayed();
            }
        });
        fluentWait.until(new Function<WebDriver, String>() {
            @Override
            public String apply(WebDriver webDriver) {
                return webDriver.findElement(By.cssSelector("[id='finish'] h4")).getText();
            }
        });
    }
    public void TC_01(){
        fluentWait = new FluentWait<WebDriver>(driver);
        WebElement element = driver.findElement(By.cssSelector(""));
        elementFluentWait = new FluentWait<>(element);
        stringFluentWait = new FluentWait<>("Hello Word!");

        fluentWait.withTimeout(Duration.ofSeconds(10)).pollingEvery(Duration.ofMillis(300)).ignoring(NoSuchElementException.class);
        fluentWait.withTimeout(Duration.ofSeconds(10)).pollingEvery(Duration.ofMillis(300)).ignoring(NoAlertPresentException.class);
        //Condition
        fluentWait.until(new Function<WebDriver, String>() {

            @Override
            public String apply(WebDriver webDriver) {
                //return a string
                return webDriver.getCurrentUrl();
            }
        });
        fluentWait.until(new Function<WebDriver, Alert>() {
            @Override
            public Alert apply(WebDriver webDriver) {
                return new WebDriverWait(driver,Duration.ofSeconds(15)).until(ExpectedConditions.alertIsPresent());
            }
        });
    }
    @Test
    public void TC_02(){
        driver.get("https://automationfc.github.io/dynamic-loading/");
        driver.findElement(By.xpath("//button[contains(text(),'Start')]")).click();
        fluentWait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(100))
                .ignoring(NoSuchElementException.class);
        fluentWait.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver driver) {
                if(driver.findElement(By.cssSelector("[id='finish'] h4")).isDisplayed()){
                    return driver.findElement(By.cssSelector("[id='finish'] h4"));
                }else{
                    return null;
                }
            }
        });
        //System.out.println(foo.getText());
        fluentWait.until(new Function<WebDriver, Boolean>() {
            @Override
            public Boolean apply(WebDriver webDriver) {
                return webDriver.findElement(By.cssSelector("[id='finish'] h4")).isDisplayed();
            }
        });
        String helloText = fluentWait.until(new Function<WebDriver, String>() {
            @Override
            public String apply(WebDriver webDriver) {
                return webDriver.findElement(By.cssSelector("[id='finish'] h4")).getText();
            }
        });
        assertEquals("Hello World!",helloText);
    }
    @Test
    public void TC03(){
        driver.get("https://automationfc.github.io/fluent-wait");
        WebElement countDownTime = driver.findElement(By.cssSelector("div#javascript_countdown_time"));
        elementFluentWait = new FluentWait<>(countDownTime);
        elementFluentWait.withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofMillis(100))
                .ignoring(java.util.NoSuchElementException.class);// Ko ignore - fail
        elementFluentWait.until(new Function<WebElement, Boolean>() {
            @Override
            public Boolean apply(WebElement element) {
                System.out.println(element.getText());
                return element.getText().endsWith("00");
            }
        });
    }

    @AfterClass
    public void endTest() {
        driver.quit();
    }
}
