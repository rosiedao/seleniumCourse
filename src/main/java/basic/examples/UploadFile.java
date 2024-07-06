package basic.examples;

import common.InitBrowser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;
import java.util.List;

import static org.testng.AssertJUnit.assertTrue;

public class UploadFile extends InitBrowser {
    String image1 = "sample.png";
    String image2 = "image22.png";
    String image3 = "image3.png";
    //System.getProperty("user.dir") gets root folder (C:/, D:/..) to folder containing current source code
    // All images should be contained in a folder of current project
    String pathImage1 = System.getProperty("user.dir") + "/src/images" + File.separator + image1;
    String pathImage2 = System.getProperty("user.dir") + "/src/images" + File.separator + image2;
    String pathImage3 = System.getProperty("user.dir") + "/src/images" + File.separator + image3;
    @BeforeClass
    public void launchApplication(){
        startBrowser("chrome");
    }
    @Test
    public void uploadImage(){
        navigateURL("https://fineuploader.com/demos.html");
        WebElement uploadFile = driver.findElement(By.cssSelector("#fine-uploader-gallery input[type='file']"));
        uploadFile.sendKeys(pathImage1);
        sleep(2);
        uploadFile.sendKeys(pathImage2);
        sleep(2);
        uploadFile.sendKeys(pathImage3);
        sleep(2);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        assertTrue(driver.findElement(By.cssSelector("img.qq-thumbnail-selector")).isDisplayed());
    }
    @Test
    public void TC01_SingleFile(){
        navigateURL("https://blueimp.github.io/jQuery-File-Upload/");
        WebElement uploadFile = driver.findElement(By.cssSelector("input[name='files[]']"));
        uploadFile.sendKeys(pathImage1);
        sleep(5);
        uploadFile.sendKeys(pathImage2);
        sleep(5);
        uploadFile.sendKeys(pathImage3);
        sleep(5);
        List<WebElement> startButtons = driver.findElements(By.cssSelector("td>button.start"));
        startButtons.forEach(WebElement::click);
        assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + image1 + "']")).isDisplayed());
        assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + image2 + "']")).isDisplayed());
        assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + image3 + "']")).isDisplayed());
    }
    @Test
    public void TC02_MultipleFiles(){
        navigateURL("https://blueimp.github.io/jQuery-File-Upload/");
        WebElement uploadFile = driver.findElement(By.cssSelector("input[name='files[]']"));
        uploadFile.sendKeys(pathImage1 + "\n" + pathImage2 + "\n" + pathImage3);
        sleep(10);
        List<WebElement> startButtons = driver.findElements(By.cssSelector("td>button.start"));
        startButtons.forEach(WebElement::click);
        assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + image1 + "']")).isDisplayed());
        assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + image2 + "']")).isDisplayed());
        assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + image3 + "']")).isDisplayed());
    }
}
