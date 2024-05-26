package basic;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import static org.testng.AssertJUnit.assertEquals;

public class CaptureScreenshot {
    public String driverPath = "/Users/dungdao/Documents/Setup/chromedriver/chromedriver";
    public WebDriver driver;
    private By name = By.xpath("//div[@class = 'form-group']/input[@name='name']");
    private By email = By.xpath("//div[@class = 'form-group']/input[@name='email']");
    private By password = By.id("exampleInputPassword1");
    private By checkMeOut = By.id("exampleInputPassword1");
    private By genderSelect = By.id("exampleFormControlSelect1");
    private By employerStatusStudent = By.id("inlineRadio1");
    private By dateOfBirth = By.xpath("//div[@class = 'form-group']/input[@name='bday']");
    private By btnSubmit = By.cssSelector(".btn-success");
    private By bindingName = By.xpath("//h4/input[@name='name']");
    private By alertSuccess = By.xpath("//div[@class='alert alert-success alert-dismissible']/strong");
    @BeforeClass
    public void startBrowser() {
        System.setProperty("webdriver.chrome.driver", driverPath);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://rahulshettyacademy.com/angularpractice/");
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));
    }
    @Test
    public void fillData() {
        driver.findElement(name).sendKeys("Rosie");
        driver.findElement(email).sendKeys("user@test.com");
        driver.findElement(password).sendKeys("123456");
        driver.findElement(password).click();
        Select dropdown = new Select(driver.findElement(genderSelect));
        dropdown.selectByIndex(1);
        driver.findElement(checkMeOut).click();
        driver.findElement(employerStatusStudent).click();
        driver.findElement(dateOfBirth).sendKeys("11/15/1992");
        driver.findElement(btnSubmit).click();
        assertEquals (driver.findElement(name).getText(), driver.findElement(bindingName).getText());
        assertEquals("Success!", driver.findElement(alertSuccess).getText());
    }
    @Test
    public void invokingMultipleWindow() throws IOException {
        //Open new tab
        driver.switchTo().newWindow(WindowType.TAB);
        Set<String> windows = driver.getWindowHandles();
        Iterator<String> it = windows.iterator();
        String parentWindowId = it.next();
        String childWindowId = it.next();
        // Go to new tab
        driver.switchTo().window(childWindowId);
        driver.get("https://rahulshettyacademy.com");
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(2000));
        String text = driver.findElement(By.xpath("//a[contains(text(),'All-Access Membership-Complete Access to 25+ Cours')]")).getText();
        // Back to parent tab
        driver.switchTo().window(parentWindowId);
        driver.findElement(name).sendKeys(text);
        //capture screenshot
        File file = driver.findElement(name).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file, new File("output/nameinput.png"));

        // Get height & width
        System.out.println(driver.findElement(name).getRect().getDimension().getHeight());
        System.out.println(driver.findElement(name).getRect().getDimension().getWidth());
    }
    @AfterClass
    public void endTest() {
        driver.quit();
    }
}
