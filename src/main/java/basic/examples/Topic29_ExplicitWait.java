package basic.examples;

import common.InitBrowser;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;
import java.util.*;

import static org.testng.AssertJUnit.assertEquals;

public class Topic29_ExplicitWait {
    WebDriver driver;
    WebDriverWait explicitWait;
    Actions actions;
    String image1 = "sample.png";
    String image2 = "image22.png";
    String image3 = "image3.png";
    //System.getProperty("user.dir") gets root folder (C:/, D:/..) to folder containing current source code
    // All images should be contained in a folder of current project
    String pathImage1 = System.getProperty("user.dir") + "/src/images" + File.separator + image1;
    String pathImage2 = System.getProperty("user.dir") + "/src/images" + File.separator + image2;
    String pathImage3 = System.getProperty("user.dir") + "/src/images" + File.separator + image3;
    @BeforeClass
    public void start(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions option = new ChromeOptions();
        option.addArguments("--disable-notifications");
        driver = new ChromeDriver(option);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        actions = new Actions(driver);
    }
    @Test
    public void TC_01_Equals_5_Second(){
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.get("https://automationfc.github.io/dynamic-loading/");
        driver.findElement(By.xpath("//button[contains(text(),'Start')]")).click();
        // Wait element invisible
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading")));
        assertEquals("Hello World!",driver.findElement(By.cssSelector("[id='finish'] h4")).getText());
    }
    @Test
    public void TC_01_LessThan_5_Second(){
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(3));
        driver.get("https://automationfc.github.io/dynamic-loading/");
        driver.findElement(By.xpath("//button[contains(text(),'Start')]")).click();
        // Wait element invisible
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading")));
        assertEquals("Hello World!",driver.findElement(By.cssSelector("[id='finish'] h4")).getText());
    }
    @Test
    public void TC_01_MoreThan_5_Second(){
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://automationfc.github.io/dynamic-loading/");
        driver.findElement(By.xpath("//button[contains(text(),'Start')]")).click();
        // Wait element invisible
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading")));
        assertEquals("Hello World!",driver.findElement(By.cssSelector("[id='finish'] h4")).getText());
    }
    @Test
    public void TC02_AjaxLoading(){// Click date and wait loading invisible then get text
        //When element has different state in time, we will not define element = driver.findElement, we use By elementBy
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10),Duration.ofMillis(100));
        driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("#rcMain")));
        By text = By.cssSelector("#ctl00_ContentPlaceholder1_Label1");
        System.out.println(driver.findElement(text).getText());

        driver.findElement(By.xpath("//td/a[text()='16']")).click();
        //Wait Ajax Loading invisibility
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.raDiv")));
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@class,'rcSelected')]//a[text()='16']")));
        assertEquals("Tuesday, July 16, 2024", driver.findElement(text).getText());
    }
    @Test
    public void TC_07_UploadFile_SingleFile(){
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(15),Duration.ofMillis(100));
        driver.get("https://gofile.io/welcome");
        //Waiting loading disappear
        explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div.spinner-border"))));
        //Waiting btn display and then click
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".btn"))).click();
        //Waiting loading disappear
        explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div.spinner-border"))));

        WebElement uploadFile = driver.findElement(By.id("filesUploadInput"));
        uploadFile.sendKeys(pathImage1);
        explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div[role='progressbar']>span"))));
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.mainUploadSuccess div.alert")));
        WebElement link = driver.findElement(By.cssSelector("div.mainUploadSuccessLink a.ajaxLink"));
        //Open link in new tab
        //String openLinkInNewTab = Keys.chord(Keys.COMMAND, Keys.RETURN);
        //driver.findElement(By.cssSelector("div.mainUploadSuccessLink a.ajaxLink")).sendKeys(openLinkInNewTab);
        //actions.keyDown(Keys.COMMAND).click(link).keyUp(Keys.COMMAND).build().perform();
        //ArrayList<String> tab = new ArrayList<>(driver.getWindowHandles());
        //driver.switchTo().window(tab.get(1));
        explicitWait.until(ExpectedConditions.elementToBeClickable(link)).click();
        explicitWait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//span[text()='"+image1+"']/parent::a/parent::div//following-sibling::div//button/span[text()='Download']")));
        explicitWait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//span[text()='"+image1+"']/parent::a/parent::div//following-sibling::div//button/span[text()='Play']")));
    }
    @Test
    public void TC_07_UploadFile_MultipleFiles(){
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(15),Duration.ofMillis(100));
        driver.get("https://gofile.io/welcome");
        //Waiting loading disappear
        explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div.spinner-border"))));
        //Waiting btn display and then click
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".btn"))).click();
        //Waiting loading disappear
        explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div.spinner-border"))));

        WebElement uploadFile = driver.findElement(By.id("filesUploadInput"));
        uploadFile.sendKeys(pathImage1 + "\n" + pathImage2 + "\n" + pathImage3);
        explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div[role='progressbar']>span"))));
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.mainUploadSuccess div.alert")));
        WebElement link = driver.findElement(By.cssSelector("div.mainUploadSuccessLink a.ajaxLink"));
        explicitWait.until(ExpectedConditions.elementToBeClickable(link)).click();
        List<String> files = Arrays.asList(image1,image2,image3);
        for(String f : files) {
            explicitWait.until(ExpectedConditions
                    .visibilityOfElementLocated(By.xpath("//span[text()='" + f + "']/parent::a/parent::div//following-sibling::div//button/span[text()='Download']")));
            explicitWait.until(ExpectedConditions
                    .visibilityOfElementLocated(By.xpath("//span[text()='" + f + "']/parent::a/parent::div//following-sibling::div//button/span[text()='Play']")));
        }
    }
    @AfterMethod
    public void endTest() {
        driver.quit();
    }
}
