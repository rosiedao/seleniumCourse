package common;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import javax.xml.stream.events.Characters;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Set;
import java.util.function.Function;

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
    public void fluentWaitElementFound(By locator){
        FluentWait<WebDriver> fluentWait = new FluentWait<>(driver);
        fluentWait.withTimeout(Duration.ofSeconds(10)).pollingEvery(Duration.ofMillis(300)).ignoring(NoSuchElementException.class);
        fluentWait.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver webDriver) {
                return webDriver.findElement(locator);
            }
        });
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
    public void verifyInputtedValue(WebElement e, String value){
        assertEquals(e.getAttribute("value"),value);
    }
    public void verifySelectedValue(WebElement e, String value){
        Select select = new Select(e);
        assertEquals(select.getFirstSelectedOption().getText(),value);
    }
    public void openLinkInNewTab(WebElement link){
        String selectLinkOpenInNewTab = Keys.chord(Keys.COMMAND,Keys.RETURN);
        link.sendKeys(selectLinkOpenInNewTab);
    }
    public void openNewTab(){
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("window.open()");
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
    }
    public void switchToWindowByTitle(String expectedTitle){
        Set<String> windows = driver.getWindowHandles();
        for (String window : windows) {
            driver.switchTo().window(window);
            if (driver.getTitle().equals(expectedTitle)) {
                break;
            }
        }
    }
    public void sleep(long second){
        try {
            Thread.sleep(second * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isPageLoadedSuccess() {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return (Boolean) jsExecutor.executeScript("return (window.jQuery != null) && (jQuery.active === 0);");
            }
        };

        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
            }
        };
        return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
    }

}
