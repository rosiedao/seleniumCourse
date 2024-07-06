package basic.examples;

import common.InitBrowser;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.testng.AssertJUnit.*;
import static org.testng.AssertJUnit.assertEquals;

public class HandleButtonRadioCheckboxAlert extends InitBrowser {
    @BeforeClass
    public void launchApplication(){
        startBrowser("chrome");
    }
    @Test
    public void testcase1(){
        navigateURL("https://www.fahasa.com/customer/account/create");
        //Navigate to tab Login
        driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        //Verify button login is disable
        WebElement loginButton = driver.findElement(By.cssSelector("button.fhs-btn-login"));
        assertFalse(loginButton.isEnabled());

        // Get background color
        String buttonBackgroundRGB = loginButton.getCssValue("background-color");
        System.out.println("Background color RGB: " + buttonBackgroundRGB);

        //Convert to Hexa
        Color buttonBackgroundColor = Color.fromString(buttonBackgroundRGB);
        String buttonBackgroundHexa = buttonBackgroundColor.asHex();
        System.out.println("Background color Hexa: " + buttonBackgroundHexa);
        assertEquals(buttonBackgroundHexa,"#000000");

        // Input email/ password
        driver.findElement(By.id("login_username")).sendKeys("test@test.com");
        driver.findElement(By.id("login_password")).sendKeys("123456");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        //Verify button is enabled
        assertTrue(loginButton.isEnabled());
        System.out.println("Background color RGB: " + loginButton.getCssValue("background-color"));
        assertEquals(Color.fromString(loginButton.getCssValue("background-color")).asHex(),"#c92127");
    }
    @Test
    public void testcase2(){
        navigateURL("https://demos.telerik.com/kendo-ui/checkbox/index");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        WebElement checkbox = driver.findElement(By.xpath("//label[text()='Luggage compartment cover']/preceding-sibling::span/input"));
        checkbox.click();
        assertTrue(checkbox.isSelected());
        checkbox.click();
        assertFalse(checkbox.isSelected());

        // Open new window using javascriptExecutor
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("window.open()");
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        navigateURL("https://demos.telerik.com/kendo-ui/radiobutton/index");
        WebElement selectedRadio = driver.findElement(By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::span/input"));
        checkToElement(selectedRadio);
        System.out.println(selectedRadio.isSelected());
    }
    @Test
    public void testcase3(){
        navigateURL("https://material.angular.io/components/radio/examples");
        WebElement chkSummer = driver.findElement(By.cssSelector("input[value='Summer']"));
        checkToElement(chkSummer);

        // Open new tab
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("window.open()");
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        navigateURL("https://material.angular.io/components/checkbox/examples");
        WebElement chkChecked = driver.findElement(By.xpath("//label[text()='Checked']/preceding-sibling::div/input"));
        WebElement chkIndeterminate = driver.findElement(By.xpath("//label[text()='Indeterminate']/preceding-sibling::div/input"));
        checkToElement(chkChecked);
        checkToElement(chkIndeterminate);
        assertTrue(chkChecked.isSelected());
        assertTrue(chkIndeterminate.isSelected());
        uncheckToElement(chkChecked);
        uncheckToElement(chkIndeterminate);
        assertFalse(chkChecked.isSelected());
        assertFalse(chkIndeterminate.isSelected());
    }
    @Test
    public void testcase4(){
        navigateURL("https://automationfc.github.io/multiple-fields/");
        List<WebElement> checkboxItems = driver.findElements(By.cssSelector(".form-checkbox-item input"));
        List<WebElement> checkboxLabels = driver.findElements(By.cssSelector(".form-checkbox-item label"));
        checkboxItems.forEach(this::checkToElement);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        checkboxItems.forEach(e -> assertTrue(e.isSelected()));
        checkboxItems.forEach(this::uncheckToElement);
        checkboxItems.forEach(e -> assertFalse(e.isSelected()));

        WebElement selectedCheckbox = checkboxItems.stream()
                .filter(element -> element.getAttribute("value").equals("Gout")).findFirst().orElse(null);
        assert selectedCheckbox != null;
        checkToElement(selectedCheckbox);
        assertTrue(selectedCheckbox.isSelected());

    }
    @Test
    public void testcase5(){
        navigateURL("https://login.ubuntu.com/");
        System.out.println(driver.findElement(By.cssSelector(".new-user>span")).isDisplayed());
        driver.findElement(By.cssSelector(".new-user>span")).click();
        System.out.println(driver.findElement(By.xpath("//label[contains(text(),'I have read and accept')]")).isDisplayed());
        driver.findElement(By.xpath("//label[contains(text(),'I have read and accept')]")).click();
    }
    public void checkToElement(WebElement element){
        if(!element.isSelected()){
            element.click();
        }
    }
    public void uncheckToElement(WebElement element){
        if(element.isSelected()){
            element.click();
        }
    }
}
