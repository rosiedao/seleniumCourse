package basic.orangehrm.pages;

import common.Commons;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class ImmigrationListPage extends Commons {
    WebDriver driver;
    @FindBy(xpath = "(//*[@class='orangehrm-action-header'])[1]//button")
    WebElement btnAddImmigration;
    @FindBy(css = "input[type='radio'][value='1']~span")
    WebElement radioPassport;
    @FindBy(css = "input[type='radio'][value='2']~span")
    WebElement radioVisa;
    @FindBy(xpath = "(//label[contains(text(),'Number')]/following::div)[1]/input")
    WebElement txtNumber;
    @FindBy(tagName = "textarea")
    WebElement areaComments;
    @FindBy(css = "button[type='submit']")
    WebElement btnSave;
    @FindBy(css = ".bi-pencil-fill")
    WebElement iconEdit;
    public ImmigrationListPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    public void goToAddNewImmigration(){
        btnAddImmigration.click();
    }
    public void addNewImmigration(String document, String number, String comment){
        if(document.equalsIgnoreCase("visa")){
            radioVisa.click();
        }else if (document.equalsIgnoreCase("password")) {
            radioVisa.click();
            radioPassport.click();
        }
        txtNumber.sendKeys(number);
        areaComments.sendKeys(comment);
        btnSave.click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }
    public void goToImmigrationEdit(){
        iconEdit.click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }
    public void verifyImmigrationDetail(String document, String number,String comment){
        if(document.equalsIgnoreCase("visa")){
            assertTrue(radioVisa.isEnabled());
        }else if (document.equalsIgnoreCase("password")) {
            assertTrue(radioPassport.isEnabled());
        }
        assertEquals(txtNumber.getAttribute("value"), number);
        assertEquals(areaComments.getAttribute("value"), comment);
    }
}
