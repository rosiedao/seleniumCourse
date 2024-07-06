package basic.examples;

import common.InitBrowser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

public class Topic42_Wait extends InitBrowser {
    WebDriverWait explicitWait;
    By reconfirmEmailTextbox = By.cssSelector("input[name='reg_email_confirmation__']");
    @BeforeClass
    public void start(){
        startBrowser("chrome");
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        navigateURL("https://www.facebook.com/?locale=en_EN");
    }
    @Test
    public void TC01_Visible(){
        driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
        sleep(2);
        driver.findElement(By.cssSelector("input[name='reg_email__']")).sendKeys("dung@gmail.com");
        sleep(2);
        //At this moment/ step, Confirm email textbox is displaying in UI
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(reconfirmEmailTextbox));
        //Check element display
        assertTrue(driver.findElement(reconfirmEmailTextbox).isDisplayed());
    }
    @Test
    public void TC02_InvisibleInDom(){
        driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
        sleep(2);
        //Condition 2: Element is not displayed in UI but existed in DOM
        driver.findElement(By.cssSelector("input[name='reg_email__']")).clear();
        sleep(2);
        //At this moment/ step, Confirm email textbox is displaying in UI but existed in DOM
        //An expectation for checking that an element is either invisible or not present on the dom
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(reconfirmEmailTextbox));
        //Check element is not displayed - visible
        // Run so fast and result is pass
        assertFalse(driver.findElement(reconfirmEmailTextbox).isDisplayed());
    }
    @Test
    public void TC03_Presence(){
        driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
        sleep(2);
        driver.findElement(By.cssSelector("input[name='reg_email__']")).clear();
        sleep(2);
        //Condition 3: Just care element in DOM
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(reconfirmEmailTextbox));

    }
    @Test
    public void TC03_Staleness(){
        driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
        sleep(2);
        driver.findElement(By.cssSelector("input[name='reg_email__']")).sendKeys("dung@gmail.com");
        sleep(2);
        WebElement reconfirmEmail =driver.findElement(reconfirmEmailTextbox);
        //Close popup
        driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/preceding-sibling::img")).click();
        sleep(2);
        //Condition 3: Just care element in DOM
        explicitWait.until(ExpectedConditions.stalenessOf(reconfirmEmail));
    }
    @Test
    public void TC_01_FindElement(){
        //Case 1 - Element found is only one, don't need to wait full time
        //Get element and next step
        driver.findElement(By.cssSelector("input[name='reg_email__']")).sendKeys("dung@gmail.com");
        sleep(2);

        //Case 2 - Element found is more than 1
        //Get first element no matter how many nodes found and next step
        driver.findElement(By.cssSelector("input")).sendKeys("dung@gmail.com");
        sleep(2);

        //Case 3: Element not found - wait full timeout
        //Wait 10s and every 1s will find again
        //If element found then next step
        // If element not found throw exception: NoSuchElementException and TC failed, next step is not run

    }
}
