package basic;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.testng.AssertJUnit.assertEquals;

public class HandleAlertPopup {
    public String driverPath = "/Users/dungdao/Documents/Setup/chromedriver/chromedriver";
    public WebDriver driver;
    public By ckbOption1 = By.id("checkBoxOption1");
    public By label = By.xpath("//label[@for='bmw']");
    public By dropdown = By.id("dropdown-class-example");
    public By textbox = By.id("name");
    public By btnAlert = By.id("alertbtn");
    @BeforeClass
    public void startBrowser() {
        System.setProperty("webdriver.chrome.driver", driverPath);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));
    }
    @Test
    public void fillValueFromOtherField(){
        String input = "";
        driver.findElement(ckbOption1).click();
        // Get corresponding label of checkbox
        input = driver.findElement(label).getText();
        System.out.println(input);

        //Select value from checkbox to dropdown list
        Select select = new Select(driver.findElement(dropdown));
        select.selectByVisibleText(input);

        driver.findElement(textbox).sendKeys(input);
        driver.findElement(btnAlert).click();
        String actualMsg = driver.switchTo().alert().getText();
        String expectedMsg = "Hello Option1, share this practice page and share your knowledge";
        assertEquals(expectedMsg,actualMsg);
    }

    @AfterClass
    public void endTest() {
        driver.quit();
    }
}
