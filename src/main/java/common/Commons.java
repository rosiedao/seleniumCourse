package common;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.xml.stream.events.Characters;
import java.time.Duration;
import java.util.List;
import java.util.Random;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class Commons {
    WebDriver driver;
    public Commons(WebDriver driver){
        this.driver = driver;
    }
    public void waitForElementToAppear(By findBy, int duration){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(duration));
        wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
    }
    public void waitForElementToDisappear(By findBy, int duration){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(duration));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(findBy));
    }
    public void verifyCurrentURLOfPage(String expectedURL){
        assertEquals(driver.getCurrentUrl(),expectedURL);
    }
    public void verifyCurrentTitleOfPage(String expectedURL){
        assertEquals(driver.getTitle(),expectedURL);
    }
    public void back(){
        driver.navigate().back();
    }
    public void forward(){
        driver.navigate().forward();
    }
    public void verifyContainTextInSourceCode(String text){
        assertTrue(driver.getPageSource().contains(text));
    }
    public void scrollDown(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(500,document.body.scrollHeight)");
    }
    public void mouseHover(WebElement element){
        Actions actions = new Actions(driver);
        actions.moveToElement(element).build().perform();
    }
    public String generateRandomString(int length){
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder(length);
        for(int i= 0; i< length; i++){
            int index = random.nextInt(chars.length());
            stringBuilder.append(chars.charAt(index));
        }
        return stringBuilder.toString();
    }
    public String generateRandomNumber(int length){
        String chars = "0123456789";
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder(length);
        for(int i= 0; i< length; i++){
            int index = random.nextInt(chars.length());
            stringBuilder.append(chars.charAt(index));
        }
        return stringBuilder.toString();
    }
    public void inputDateTime(WebElement input, String date){
        String[] words = date.split("-");
        for(String w : words){
            input.sendKeys(w);
        }
    }
    public static  String convertDateFormat(String date){
        String[] words = date.split("-");
        System.out.println(words.length);
        return words[2] + "-" + words[1] + "-" + words[0];
    }
    public int getOptions(WebElement select){
        Select objSelect = new Select(select);
        List<WebElement> options = objSelect.getOptions();
        return options.size();
    }
}
