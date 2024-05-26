package basic;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class HandleTableGrid {
    public String driverPath = "/Users/dungdao/Documents/Setup/chromedriver/chromedriver";
    public WebDriver driver;
    @BeforeClass
    public void startBrowser() {
        System.setProperty("webdriver.chrome.driver", driverPath);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://rahulshettyacademy.com/AutomationPractice");
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));
    }

    @Test
    public void getDataTable(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        //scroll bar down
        js.executeScript("window.scrollBy(0,500)");
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));
        js.executeScript("document.querySelector('.tableFixHead').scrollTop = 5000");
        List<WebElement> values = driver.findElements(By.cssSelector(".tableFixHead td:nth-child(4)"));
        int expectedSum = 0;
        for(WebElement v : values){
            System.out.println(Integer.parseInt(v.getText()));
            expectedSum += Integer.parseInt(v.getText());
        }
        int actualSum = Integer.parseInt(driver.findElement(By.cssSelector(".totalAmount")).getText().split(":")[1].trim());
        System.out.println(actualSum);
        assertEquals(expectedSum,actualSum);
    }

    @Test
    public void brokenLink() throws IOException {
        /**
         * Get all urls tied up to the links using selenium.
         * Java method will call URL's and gets you the status code
         * If the status > 400 => not working
         */
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,500)");
        String url = driver.findElement(By.cssSelector("a[href*='brokenlink']")).getAttribute("href");
        System.out.println(url);
        //URLConnection conn = new URL(url).openConnection();
        HttpURLConnection conn = (HttpURLConnection)new URL(url).openConnection();
        conn.setRequestMethod("HEAD");
        conn.connect();
        int responseCode = conn.getResponseCode();
        System.out.println(responseCode);
    }
    @AfterClass
    public void endTest() {
        driver.quit();
    }
}
