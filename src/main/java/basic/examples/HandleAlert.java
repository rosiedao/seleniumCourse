package basic.examples;

import common.InitBrowser;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class HandleAlert extends InitBrowser {
    WebDriverWait explicitWait;
    @BeforeClass
    public void launchApplication(){
        startBrowser("chrome");
        explicitWait = new WebDriverWait(driver,Duration.ofSeconds(5));
    }
    @Test
    public void acceptAlert(){
        navigateURL("https://automationfc.github.io/basic-form/index.html");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,500)", "");

        driver.findElement(By.xpath("//button[contains(text(),'Click for JS Alert')]")).click();
        Alert alert = explicitWait.until(ExpectedConditions.alertIsPresent());
        String alertText = alert.getText();
        assertEquals("I am a JS Alert", alertText);
        alert.accept();

        driver.findElement(By.xpath("//button[contains(text(),'Click for JS Confirm')]")).click();
        Alert confirmAlert = explicitWait.until(ExpectedConditions.alertIsPresent());
        String alertConfirm = confirmAlert.getText();
        assertEquals("I am a JS Confirm", alertConfirm);
        confirmAlert.dismiss();
        assertEquals("You clicked: Cancel", driver.findElement(By.xpath("//p[@id='result']")).getText());

        driver.findElement(By.xpath("//button[contains(text(),'Click for JS Prompt')]")).click();
        Alert promptAlert = explicitWait.until(ExpectedConditions.alertIsPresent());
        String promptConfirm = promptAlert.getText();
        assertEquals("I am a JS prompt", promptConfirm);
        String input = "automationfc";
        promptAlert.sendKeys(input);
        promptAlert.accept();
        System.out.println(driver.findElement(By.xpath("//p[@id='result']")).getText());
        assertEquals("You entered: " + input, driver.findElement(By.xpath("//p[@id='result']")).getText());
    }
    @Test
    public void AuthenticationAlert(){
        //Option1: add username, password to the link
        navigateURL("https://admin:admin@the-internet.herokuapp.com/basic_auth");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(45));
        String title = driver.getTitle();
        System.out.println("The page title is : " + title);


        String text = driver.findElement(By.tagName("p")).getText();
        System.out.println("The text present in page is : " + text);
        assertTrue(driver.findElement(By.tagName("p")).isDisplayed());
        //Option 2: use AutoIT only for window
    }
    @Test
    public void TC09_AlertFrame(){
        navigateURL("https://netbanking.hdfcbank.com/netbanking/");
        driver.switchTo().frame("login_page");
        driver.findElement(By.cssSelector(".btn")).click();
        Alert warningAlert = explicitWait.until(ExpectedConditions.alertIsPresent());
        System.out.println(warningAlert.getText());
    }
}
