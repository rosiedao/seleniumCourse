package basic.examples;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Date;

public class Mix_Wait {
    WebDriver driver;
    WebDriverWait explicitWait;
    @BeforeClass
    public void start(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions option = new ChromeOptions();
        option.addArguments("--disable-notifications");
        driver = new ChromeDriver(option);
        driver.manage().window().maximize();
    }
    @Test
    public void TC_01_Only_Implicit_Found(){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://www.facebook.com/");
        // Khi vao tim element thi no tim thay ngay va khong can cho het timeout
        driver.findElement(By.cssSelector("input#email"));
    }
    @Test
    public void TC_01_Only_Implicit_NotFound(){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://www.facebook.com/");
        // Khi vao tim ma khong thay element thi polling 0.5s tim lai mot lan
        // Khi het timeout se danh fail testcase va throw exception: NoSuchElementException
        driver.findElement(By.cssSelector("input#automation"));
    }
    @Test
    public void TC_01_Only_Explicit_Found(){
        explicitWait = new WebDriverWait(driver,Duration.ofSeconds(5));
        driver.get("https://www.facebook.com/");
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#email")));
    }
    @Test
    public void TC_01_Only_Explicit_NotFound(){
        explicitWait = new WebDriverWait(driver,Duration.ofSeconds(5));
        driver.get("https://www.facebook.com/");
        // Khi vao tim element ma khong thay
        // Polling 0.5s tim lai mot lan
        // Khi het timeout danh test case fail va throw exception TimoutException
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#email")));
        //nosuchementexception vi findElement bi anh huong boi implicitWait, ma neu khong set implicitWait thi mac dinh = 0
        explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("input#automation"))));
    }
    @Test
    public void TC_01_Mix_Explicit_Implicit(){
        //Total time = 6.671s
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        explicitWait = new WebDriverWait(driver,Duration.ofSeconds(5));
        driver.get("https://www.facebook.com/");

        System.out.println("Start time: " + getDateTimeNow());
        try {
            explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#automation")));
        } catch (Exception e) {
            System.out.println("End time: " + getDateTimeNow());
            throw new RuntimeException(e);
        }
    }
    public String getDateTimeNow(){
        Date date = new Date();
        return date.toString();
    }
    @AfterClass
    public void close(){
        driver.quit();
    }
}
