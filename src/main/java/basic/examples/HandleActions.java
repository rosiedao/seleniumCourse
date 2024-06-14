package basic.examples;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class HandleActions {
    public String driverPath = "/Users/dungdao/Documents/Setup/chromedriver/chromedriver";
    public WebDriver driver;
    @BeforeClass
    public void startBrowser() {
        System.setProperty("webdriver.chrome.driver", driverPath);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.lifehack.org/");
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));
    }
    @Test
    public void takeScreenShot() throws IOException {
        File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(src,new File("output/handleaction.png"));
    }

    @Test
    public void mouseOverElement() {
        Actions a = new Actions(driver);
        //Move to a specific element
        a.moveToElement(driver.findElement(By.linkText("Start Here"))).build().perform();
        // How to perform right click
        a.moveToElement(driver.findElement(By.linkText("The Time Flow System"))).contextClick().build().perform();
        // How to enter text "Hello" in capital letters
        // a.moveToElement(driver.findElement(element)).keyDown(Keys.SHIFT).sendKeys("Hello").build().perform();
    }

    @Test
    public void litmitLinkNumber() {
        WebElement firstMenus = driver.findElement(By.xpath("//ul[@id='menu-main-nav-rebrand']//li[1]/ul[@class='sub-menu']"));
        List<WebElement> links = firstMenus.findElements(By.tagName("a"));
        int noOfLinkOnFirstMenu = links.size();
        System.out.println("No of links on first menu is " + noOfLinkOnFirstMenu);

        // Open child link on new tab
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.linkText("Start Here"))).build().perform();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));

        //String clickOnLinkTab = Keys.chord(Keys.CONTROL, Keys.ENTER);
        //links.forEach(l -> System.out.println(l.getText()));
        links.forEach(l -> actions.keyDown(Keys.COMMAND).click(l).keyUp(Keys.COMMAND).build().perform());
        /*
        for (WebElement link : links) {
            link.getText();
            actions.keyDown(Keys.COMMAND).click(link).keyUp(Keys.COMMAND).build().perform();
        }*/

        //Open all the tab
        Set<String> windows = driver.getWindowHandles();
        for(String w : windows){
            driver.switchTo().window(w);
            System.out.println(driver.getTitle());
        }
    }
    @Test
    public void openLinkInNewTab(){
        Actions actions = new Actions(driver);
        String clickOnLinkTab = Keys.chord(Keys.CONTROL, Keys.ENTER);
        WebElement link = driver.findElement(By.xpath("//strong[contains(text(),'Take the Fast Track')]"));
        //Using Keys.CONTROL for window and Keys.COMMAND for MAC
        actions.keyDown(Keys.COMMAND).click(link).keyUp(Keys.COMMAND).build().perform();
        //driver.findElement(By.xpath("//strong[contains(text(),'Take the Fast Track')]")).sendKeys(clickOnLinkTab);
        ArrayList<String> tab = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tab.get(1));
    }

    @Test
    public void dragAndDrop(){
        driver.get("https://jqueryui.com/droppable");
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));
        System.out.println(driver.findElements(By.tagName("iframe")).size());
        driver.switchTo().frame(0);
        //driver.switchTo().frame(driver.findElement(By.cssSelector("iframe[class='demo-frame']")));
        driver.findElement(By.id("draggable")).click();
        Actions a = new Actions(driver);
        WebElement source = driver.findElement(By.id("draggable"));
        WebElement target = driver.findElement(By.id("droppable"));
        a.dragAndDrop(source, target).build().perform();
        driver.switchTo().defaultContent();

    }

    @AfterClass
    public void endTest() {
        driver.quit();
    }
}
