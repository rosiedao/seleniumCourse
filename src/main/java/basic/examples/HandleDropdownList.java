package basic.examples;

import common.InitBrowser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertFalse;

public class HandleDropdownList extends InitBrowser {
    WebDriverWait explicitWait;
    @BeforeClass
    public void launchApplication(){
        startBrowser("chrome");
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    @Test
    public void test(){
        navigateURL("https://rode.com/en/support/where-to-buy");
        Select select = new Select(driver.findElement(By.id("country")));
        //Verify dropdown list not support multiple select
        assertFalse(select.isMultiple());
        select.selectByVisibleText("Vietnam");
        driver.findElement(By.id("map_search_query")).sendKeys("Ho Chi Minh");
        driver.findElement(By.xpath("//button[contains(text(),'Search')]")).click();
        List<WebElement> dealerList = driver.findElements(By.cssSelector("div.dealer_branch h4"));
        System.out.println("Number of dealer: " + dealerList.size());
        dealerList.forEach(e -> System.out.println(e.getText()));
    }
    @Test
    public void verifySelectedValue(){
        navigateURL("https://jqueryui.com/resources/demos/selectmenu/default.html");
        // 1 - Click on tag to load all item in dropdown list
        // Select number
        WebElement numberSelect = driver.findElement(By.cssSelector("span#number-button"));
        selectValueFromSelect(numberSelect,By.cssSelector("ul#number-menu div"),"15");
        // select a speed
        WebElement speedSelect = driver.findElement(By.cssSelector("span#speed-button"));
        selectValueFromSelect(speedSelect,By.cssSelector("ul#speed-menu div"),"Slow");
        // select file
        WebElement fileSelect = driver.findElement(By.cssSelector("span#files-button"));
        selectValueFromSelect(fileSelect,By.cssSelector("ul#files-menu div"),"ui.jQuery.js");
        // select a title
        WebElement titleSelect = driver.findElement(By.cssSelector("span#salutation-button"));
        selectValueFromSelect(titleSelect,By.cssSelector("ul#salutation-menu div"),"Dr.");
        //Verify selected value
        assertEquals(driver.findElement(By.cssSelector("span#speed-button>span.ui-selectmenu-text")).getText(),"Slow");
    }
    public void selectValueFromSelect(WebElement e, By by, String value){
        e.click();
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
        List<WebElement> allItems = driver.findElements(by);
        WebElement item = allItems.stream()
                .filter(element -> element.getText().equals(value)).findFirst().orElse(null);
        assert item != null;
        item.click();
    }
    public void selectValueFromEditableSelect(WebElement el, By by, String value){
        el.clear();
        el.sendKeys(value);
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
        List<WebElement> allItems = driver.findElements(by);
        WebElement item = allItems.stream()
                .filter(element -> element.getText().equals(value)).findFirst().orElse(null);
        assert item != null;
        item.click();
    }
    @Test
    public void selectDropdownListReact(){
        navigateURL("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
        WebElement select = driver.findElement(By.cssSelector("div[role='listbox'] i"));
        selectValueFromSelect(select,By.cssSelector(".item span"),"Matt");
        assertEquals(driver.findElement(By.cssSelector(".divider.text")).getText(),"Matt");
    }
}
