package basic.examples;

import common.InitBrowser;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class Topic31_Wait_PageReady {
    WebDriver driver;
    WebDriverWait explicitWait;
    @BeforeClass
    public void start(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions option = new ChromeOptions();
        option.addArguments("--disable-notifications");
        driver = new ChromeDriver(option);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        explicitWait = new WebDriverWait(driver,Duration.ofSeconds(30),Duration.ofMillis(100));
    }

    public boolean isPageLoadedSuccess() {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        //Condition 1:
        /*
        return explicitWait.until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return (Boolean) ((JavascriptExecutor) driver).executeScript("return (window.jQuery != null) && (jQuery.active === 0);");
            }
        });*/
        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return (Boolean) jsExecutor.executeScript("return (window.jQuery != null) && (jQuery.active === 0);");
            }
        };

        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
            }
        };
        return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
    }
    @Test
    public void TC01_NopCommerce_PageLoading(){
        driver.get("https://admin-demo.nopcommerce.com/");
        driver.findElement(By.id("Email")).clear();
        driver.findElement(By.id("Email")).sendKeys("admin@yourstore.com");
        driver.findElement(By.id("Password")).clear();
        driver.findElement(By.id("Password")).sendKeys("admin");
        driver.findElement(By.cssSelector(".login-button")).click();
        assertTrue(isPageLoadedSuccess());

        driver.findElement(By.cssSelector("a>i.fa-user~p")).click();
        driver.findElement(By.xpath("//ul[@style='display: block;']//i[contains(@class,'fa-dot-circle')]/following-sibling::p[contains(string(),'Customers')]")).click();
        //If we don't wait, next element is not found
        assertTrue(isPageLoadedSuccess());

        driver.findElement(By.cssSelector("a>i.fa-book~p")).click();
        //driver.findElement(By.xpath("//i[contains(@class,'fa-book')]/following-sibling::p")).click();
        driver.findElement(By.xpath("//ul[@style='display: block;']//i[contains(@class,'fa-dot-circle')]/following-sibling::p[contains(string(),'Products')]")).click();
        assertTrue(isPageLoadedSuccess());

        driver.findElement(By.cssSelector("a>i.fa-shopping-cart~p")).click();
        //driver.findElement(By.xpath("//i[contains(@class,'fa-book')]/following-sibling::p")).click();
        driver.findElement(By.xpath("//ul[@style='display: block;']//i[contains(@class,'fa-dot-circle')]/following-sibling::p[contains(string(),'Orders')]")).click();
        assertTrue(isPageLoadedSuccess());

        //Logout
        driver.findElement(By.xpath("//a[text()='Logout']")).click();
        assertTrue(driver.findElement(By.id("Email")).isDisplayed());
    }

    @Test
    public void TC01_NopCommerce_InvisibleLoadingIcon(){ // Khong on dinh, Khong phu hop neu dung check invisible
        driver.get("https://admin-demo.nopcommerce.com/");
        driver.findElement(By.id("Email")).clear();
        driver.findElement(By.id("Email")).sendKeys("admin@yourstore.com");
        driver.findElement(By.id("Password")).clear();
        driver.findElement(By.id("Password")).sendKeys("admin");
        driver.findElement(By.cssSelector(".login-button")).click();
        explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.id("ajaxBusy"))));

        driver.findElement(By.cssSelector("a>i.fa-user~p")).click();
        driver.findElement(By.xpath("//ul[@style='display: block;']//i[contains(@class,'fa-dot-circle')]/following-sibling::p[contains(string(),'Customers')]")).click();
        //If we don't wait, next element is not found
        explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.id("ajaxBusy"))));
        explicitWait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector("a>i.fa-book~p")))).click();
        explicitWait.until(ExpectedConditions
                .elementToBeClickable(driver.findElement(By.xpath("//ul[@style='display: block;']//i[contains(@class,'fa-dot-circle')]/following-sibling::p[contains(string(),'Products')]"))))
                .click();
        /*
        driver.findElement(By.cssSelector("a>i.fa-book~p")).click();
        //driver.findElement(By.xpath("//i[contains(@class,'fa-book')]/following-sibling::p")).click();
        driver.findElement(By.xpath("//ul[@style='display: block;']//i[contains(@class,'fa-dot-circle')]/following-sibling::p[contains(string(),'Products')]")).click();
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("ajaxBusy")));

        driver.findElement(By.cssSelector("a>i.fa-shopping-cart~p")).click();
        //driver.findElement(By.xpath("//i[contains(@class,'fa-book')]/following-sibling::p")).click();
        driver.findElement(By.xpath("//ul[@style='display: block;']//i[contains(@class,'fa-dot-circle')]/following-sibling::p[contains(string(),'Orders')]")).click();
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("ajaxBusy")));

        //Logout
        driver.findElement(By.xpath("//a[text()='Logout']")).click();
        assertTrue(driver.findElement(By.id("Email")).isDisplayed());
        */
    }
    @AfterClass
    public void endTest() {
        driver.quit();
    }
}
