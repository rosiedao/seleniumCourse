package basic.examples;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

public class HandleMultipleWindows {
    public String driverPath = "/Users/dungdao/Documents/Setup/chromedriver/chromedriver";
    public WebDriver driver;
    @BeforeClass
    public void startBrowser() {
        WebDriverManager.safaridriver().setup();
        SafariOptions option = new SafariOptions();
        driver = new SafariDriver(option);
        driver.manage().window().maximize();
        driver.get("https://the-internet.herokuapp.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));
    }
    @Test
    public void runTestcase(){
        driver.findElement(By.linkText("Multiple Windows")).click();
        driver.findElement(By.linkText("Click Here")).click();
        //Using when click link and open the new tab
        Set<String> windows = driver.getWindowHandles();//parentId, childId, sub-childId
        Iterator<String> it = windows.iterator();
        String parentId = it.next();
        String childId = it.next();
        driver.switchTo().window(childId);
        System.out.println(driver.findElement(By.cssSelector(".example h3")).getText());
    }
    @AfterClass
    public void endTest() {
        driver.quit();
    }
}
