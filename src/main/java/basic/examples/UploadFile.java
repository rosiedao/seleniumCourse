package basic.examples;

import common.InitBrowser;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.testng.AssertJUnit.assertTrue;

public class UploadFile extends InitBrowser {
    @BeforeClass
    public void launchApplication(){
        startBrowser("safari");
        navigateURL("https://fineuploader.com/demos.html");
    }
    @Test
    public void uploadImage(){
        //System.getProperty("user.dir") gets root folder (C:/, D:/..) to folder containing current source code
        String path = System.getProperty("user.dir") + "/src/images/sample.png";
        driver.findElement(By.cssSelector("#fine-uploader-gallery input[type='file']")).sendKeys(path);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        assertTrue(driver.findElement(By.cssSelector("img.qq-thumbnail-selector")).isDisplayed());
    }
}
