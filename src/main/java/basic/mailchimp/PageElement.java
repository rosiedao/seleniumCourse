package basic.mailchimp;

import common.Commons;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.HashMap;

import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

public class PageElement extends Commons {
    WebDriver driver;
    @FindBy(id = "mail")
    WebElement txtEmail;
    @FindBy(id = "under_18")
    WebElement chbAge;
    @FindBy(id = "edu")
    WebElement txtEducation;
    @FindBy(xpath = "//h5[contains(text(),'Name: User5')]")
    WebElement lableUser5;
    @FindBy(css = "img[alt='User Avatar 05']")
    WebElement img05;
    @FindBy(id = "job1")
    WebElement drdlJobRole01;
    @FindBy(id = "development")
    WebElement chbDevelopment;
    @FindBy(id = "slider-1")
    WebElement slider1;
    @FindBy(id="disable_password")
    WebElement txtPassword;
    @FindBy(id = "java")
    WebElement chbLanguageJava;
    public PageElement(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    public void checkElementDisplayed(){
        scrollDown();
        assertTrue(txtEmail.isDisplayed());
        assertTrue(chbAge.isDisplayed());
        assertTrue(txtEducation.isDisplayed());
        assertTrue(img05.isDisplayed());
        assertFalse(lableUser5.isDisplayed());
    }
    public void checkUser5Displayed(){
        mouseHover(img05);
        if(lableUser5.isDisplayed()){
            txtEmail.sendKeys("Automation Testing");
            txtEducation.sendKeys("Automation Testing");
            chbAge.click();
            System.out.println("Element User5 is displayed");
        }else{
            System.out.println("Element User5 is not displayed");
        }
    }
    public void checkEnableElement(){
        scrollDown();
        HashMap<WebElement,String> listElements = new HashMap<>();
        listElements.put(txtEmail, "Email");
        listElements.put(txtEducation, "Education");
        listElements.put(chbAge, "Checkbox Age");
        listElements.put(drdlJobRole01, "Dropdown list Job Role 1");
        listElements.put(chbDevelopment, "Checkbox Development");
        listElements.put(slider1, "Slider 1");
        listElements.put(txtPassword, "Password");
        listElements.forEach((e,s) -> {
            if(e.isEnabled()){
                System.out.println("Element " + s + " is enable");
            }else{
                System.out.println("Element " + s + " is disable");
            }
        });
    }

    public void checkElementChecked(){
        chbAge.click();
        chbLanguageJava.click();
        if(chbAge.isSelected() && chbLanguageJava.isSelected()){
            System.out.println("Element is selected");
        }else{
            System.out.println("Element is de-selected");
        }
    }
}
