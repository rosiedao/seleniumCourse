package basic.examples;

import common.InitBrowser;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class Topic43_StaticWait_Dead_Hard extends InitBrowser {
    WebDriverWait explicitWait;
    @BeforeClass
    public void start(){
        startBrowser("chrome");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        // total timeout = 10s and polling is 0.5s default
        // explicitWait = new WebDriverWait(driver,Duration.ofSeconds(10));
        // total timeout = 10s and polling is 0.3s
        //explicitWait = new WebDriverWait(driver,Duration.ofSeconds(10),Duration.ofMillis(300));
    }
    @Test
    public void TC03_StaticWait(){
        driver.get("https://automationfc.github.io/dynamic-loading/");
        driver.findElement(By.xpath("//button[contains(text(),'Start')]")).click();
        sleep(5);
        assertTrue(driver.findElement(By.cssSelector("#finish h4")).isDisplayed());
        assertEquals("Hello World!",driver.findElement(By.cssSelector("#finish h4")).getText());
    }
    @Test
    public void TC04_OnlyExplicitWait_3s(){
        explicitWait = new WebDriverWait(driver,Duration.ofSeconds(3),Duration.ofMillis(300));
        driver.get("https://automationfc.github.io/dynamic-loading/");
        driver.findElement(By.xpath("//button[contains(text(),'Start')]")).click();
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading")));
        // Fail vi khong set enough time
        assertEquals("Hello World!",driver.findElement(By.cssSelector("#finish h4")).getText());
    }
    @Test
    public void TC04_OnlyExplicitWait_5s(){ // 6.514s
        explicitWait = new WebDriverWait(driver,Duration.ofSeconds(5),Duration.ofMillis(300));
        driver.get("https://automationfc.github.io/dynamic-loading/");
        driver.findElement(By.xpath("//button[contains(text(),'Start')]")).click();
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading")));
        assertEquals("Hello World!",driver.findElement(By.cssSelector("#finish h4")).getText());
    }
    @Test
    public void TC04_OnlyExplicitWait_15s(){// 5.819s
        explicitWait = new WebDriverWait(driver,Duration.ofSeconds(15),Duration.ofMillis(300));
        driver.get("https://automationfc.github.io/dynamic-loading/");
        driver.findElement(By.xpath("//button[contains(text(),'Start')]")).click();
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading")));
        assertEquals("Hello World!",driver.findElement(By.cssSelector("#finish h4")).getText());
    }
    // Chi su dung Explicit Wait (Dung Visible)
    @Test
    public void TC05_OnlyExplicitWait_3s(){
        explicitWait = new WebDriverWait(driver,Duration.ofSeconds(3),Duration.ofMillis(300));
        driver.get("https://automationfc.github.io/dynamic-loading/");
        driver.findElement(By.xpath("//button[contains(text(),'Start')]")).click();
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#finish h4")));
        assertEquals("Hello World!",driver.findElement(By.cssSelector("#finish h4")).getText());
    }
    @Test
    public void TC05_OnlyExplicitWait_5s(){ // 6.514s
        explicitWait = new WebDriverWait(driver,Duration.ofSeconds(5),Duration.ofMillis(300));
        driver.get("https://automationfc.github.io/dynamic-loading/");
        driver.findElement(By.xpath("//button[contains(text(),'Start')]")).click();
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#finish h4")));
        assertEquals("Hello World!",driver.findElement(By.cssSelector("#finish h4")).getText());
    }
    @Test
    public void TC05_OnlyExplicitWait_15s(){// 5.819s
        explicitWait = new WebDriverWait(driver,Duration.ofSeconds(15),Duration.ofMillis(300));
        driver.get("https://automationfc.github.io/dynamic-loading/");
        driver.findElement(By.xpath("//button[contains(text(),'Start')]")).click();
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#finish h4")));
        assertEquals("Hello World!",driver.findElement(By.cssSelector("#finish h4")).getText());
    }
    public void sleep(int second) {
        try {
            Thread.sleep(second * 1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
