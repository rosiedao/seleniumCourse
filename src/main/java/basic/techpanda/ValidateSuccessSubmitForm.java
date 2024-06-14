package basic.techpanda;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.HashMap;
import java.util.Random;

import static org.testng.AssertJUnit.assertEquals;

public class ValidateSuccessSubmitForm{
    public String driverPath = "/Users/dungdao/Documents/Setup/chromedriver/chromedriver";
    public WebDriver driver;

    public void startBrowser() {
        System.setProperty("webdriver.chrome.driver", driverPath);
        ChromeOptions option = new ChromeOptions();
        option.setBrowserVersion("124");
        option.addArguments("--disable-notifications");
        driver = new ChromeDriver(option);
        driver.manage().window().maximize();
    }
    public void navigateURL(String url){
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));
    }
    @AfterMethod
    public void endTest() {
        driver.quit();
    }
    @Test
    public void testSubmitFormSuccess(){
        startBrowser();
        navigateURL("http://live.techpanda.org/");
        driver.findElement(By.xpath("//a[@title='Site Map']//following::a[@title='My Account']")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        // Fill form
        HashMap<String, String> data = new HashMap<>();
        data.put("firstname","Anh");
        data.put("middlename","Lan");
        data.put("lastname","Tran");
        data.put("email_address",generateRandomEmail(10));
        data.put("password","Abc@123");
        data.put("confirmation","Abc@123");
        System.out.println(generateRandomEmail(10));

        driver.findElement(By.cssSelector(".buttons-set>a[title='Create an Account']")).click();
        driver.findElement(By.id("firstname")).sendKeys(data.get("firstname"));
        driver.findElement(By.id("middlename")).sendKeys(data.get("middlename"));
        driver.findElement(By.id("lastname")).sendKeys(data.get("lastname"));
        driver.findElement(By.id("email_address")).sendKeys(data.get("email_address"));
        driver.findElement(By.id("password")).sendKeys(data.get("password"));
        driver.findElement(By.id("confirmation")).sendKeys(data.get("confirmation"));
        driver.findElement(By.cssSelector("button[title='Register']")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        String[] words = driver.findElement(By.cssSelector("p.welcome-msg")).getText().split(" ");
        assertEquals(data.get("firstname").toUpperCase(), words[1]);
        assertEquals(data.get("middlename").toUpperCase(), words[2]);
        assertEquals(data.get("lastname").toUpperCase(), words[3].replace("!",""));

        String[] mailbox = driver.findElement(By.xpath("//a[contains(text(),'Change Password')]//ancestor::p")).getText().split("\n");
        System.out.println(mailbox[0] + "," + mailbox[2]);
    }
    public String generateRandomEmail(int length){
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder(length);
        for(int i= 0; i< length; i++){
            int index = random.nextInt(chars.length());
            stringBuilder.append(chars.charAt(index));
        }
        return stringBuilder.toString() + "@gmail.com";
    }

}
