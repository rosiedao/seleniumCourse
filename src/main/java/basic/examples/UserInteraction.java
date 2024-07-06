package basic.examples;

import common.InitBrowser;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

import static org.testng.AssertJUnit.*;

public class UserInteraction extends InitBrowser {
    WebDriverWait explicitWait;
    Actions actions;
    @BeforeClass
    public void launchApplication(){
        startBrowser("chrome");
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        actions = new Actions(driver);
    }
    @Test
    public void TC01_Hover(){
        navigateURL("https://www.myntra.com/");
        actions.moveToElement(driver.findElement(By.cssSelector("div.desktop-navLink a[data-group='kids']"))).build().perform();
        driver.findElement(By.linkText("Activity Toys")).click();
        System.out.println(driver.getTitle());
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        List<WebElement> categories = driver.findElements(By.cssSelector("li.common-customCheckbox"));
        System.out.println(categories.size());
        categories.forEach(WebElement::getText);
        //WebElement checkbox = driver.findElement(By.xpath("//ul[@class='categories-list']/li/label[text()='Action Figures and Toys']/input"));
        //System.out.println(checkbox.isDisplayed());
    }
    @Test
    public void TC02_Hover(){
        navigateURL("https://www.fahasa.com/");
        WebElement icon = driver.findElement(By.cssSelector("span.icon_menu"));
        actions.moveToElement(icon).build().perform();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        WebElement parentLink = driver.findElement(By.cssSelector(".parent a[title='FOREIGN BOOKS'] span"));
        actions.moveToElement(parentLink).build().perform();
        List<WebElement> categories = driver.findElements(By.cssSelector(".fhs_menu_content>.row h3"));
        categories.forEach(e -> System.out.println(e.getText()));
        //get link of first category
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        List<WebElement> blocks = driver.findElements(By.cssSelector(".fhs_menu_content>.row ul"));
        System.out.println(blocks.size());
        List<WebElement> linksOfFirstCategory = blocks.get(0).findElements(By.cssSelector("li>a"));
        System.out.println("-----------");
        linksOfFirstCategory.forEach(e -> System.out.println(e.getText()));
        List<WebElement> allLinks = driver.findElements(By.cssSelector(".fhs_menu_content>.row ul>li a"));
        System.out.println("-----------");
        allLinks.forEach(e -> System.out.println(e.getText()));
    }
    @Test
    public void TC03_ClickAndHold(){
        navigateURL("https://automationfc.github.io/jquery-selectable/");
        List<WebElement> selections = driver.findElements(By.cssSelector("#selectable li"));
        //release = free mouse
        actions.clickAndHold(selections.get(0)).moveToElement(selections.get(3)).release().perform();
        System.out.println(selections.get(0).getAttribute("class"));
        assertTrue(selections.get(0).getAttribute("class").contains("ui-selected"));

        //select specific number
        actions.keyDown(Keys.CONTROL).perform();
        selections.get(5).click();
        selections.get(6).click();
        actions.keyUp(Keys.CONTROL).perform();
        assertTrue(selections.get(5).getAttribute("class").contains("ui-selected"));
        assertTrue(selections.get(6).getAttribute("class").contains("ui-selected"));
    }
    @Test
    public void TC_04_DoubleClick(){
        navigateURL("https://automationfc.github.io/basic-form/index.html");
        WebElement btnDoubleClickMe = driver.findElement(By.xpath("//button[contains(text(),'Double click me')]"));
        actions.doubleClick(btnDoubleClickMe).perform();
        System.out.println(driver.findElement(By.id("demo")).getText());
    }
    @Test
    public void TC05_RightClick(){
        navigateURL("http://swisnl.github.io/jQuery-contextMenu/demo.html");
        WebElement btnRightClickMe = driver.findElement(By.cssSelector("span.context-menu-one"));
        // contextClick = right click
        actions.contextClick(btnRightClickMe).perform();
        WebElement quit = driver.findElement(By.cssSelector("li.context-menu-icon-quit"));
        assertTrue(quit.isDisplayed());
        //hover quit
        actions.moveToElement(quit).perform();
        assertTrue(driver.findElement(By.cssSelector("li.context-menu-icon-quit.context-menu-visible.context-menu-hover")).isDisplayed());
        //click quit
        //quit.click();
        actions.click(quit).perform();
        //Alert alert = explicitWait.until(ExpectedConditions.alertIsPresent());
        System.out.println(driver.switchTo().alert().getText());
        driver.switchTo().alert().accept();
        System.out.println(quit.isDisplayed());
    }
    @Test
    public void TC06_DragAndDrop(){
        navigateURL("https://automationfc.github.io/kendo-drag-drop/");
        WebElement source = driver.findElement(By.id("draggable"));
        WebElement target = driver.findElement(By.id("droptarget"));
        actions.dragAndDrop(source,target).perform();
        assertEquals("You did great!",target.getText());
        assertEquals("#03a9f4", Color.fromString(target.getCssValue("background-color")).asHex());
    }
}
