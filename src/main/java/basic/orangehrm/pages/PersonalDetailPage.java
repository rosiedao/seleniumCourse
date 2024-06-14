package basic.orangehrm.pages;

import common.Commons;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.testng.AssertJUnit.assertEquals;

public class PersonalDetailPage extends Commons {
    WebDriver driver;
    @FindBy(css = "input[name='firstName']")
    WebElement txtFirstname;
    @FindBy (css = "input[name='middleName']")
    WebElement txtMiddleName;
    @FindBy (css = "input[name='lastName']")
    WebElement txtLastname;
    @FindBy (xpath = "//label[contains(text(),'Employee Id')]/following::div/input")
    WebElement txtEmployeeId;
    @FindBy (xpath = "//div[@role='tablist']//a[contains(text(),'Immigration')]")
    WebElement linkImmigration;
    public PersonalDetailPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    public void verifyInputtedInformation(String firstname, String middleName, String lastname){
        System.out.println("Field Firstname is displayed: " + txtFirstname.isDisplayed() + " Value is " + txtFirstname.getAttribute("value"));
        System.out.println("Field Middle name is displayed: " + txtMiddleName.isDisplayed() + " Value is " + txtMiddleName.getAttribute("value"));
        System.out.println("Field Lastname is displayed: " + txtLastname.isDisplayed() + " Value is " + txtLastname.getAttribute("value"));
        System.out.println("Field Employee Id is displayed: " + txtEmployeeId.isDisplayed() + " Value is " + txtEmployeeId.getAttribute("value"));
        //assertEquals(firstname,txtFirstname.getAttribute("value"));
        //assertEquals(middleName,txtMiddleName.getAttribute("value"));
        //assertEquals(lastname,txtLastname.getAttribute("value"));
    }
    public String getEmployeeId(){
        System.out.println("Employee ID of detail screen is: " + txtEmployeeId.getAttribute("value"));
        return txtEmployeeId.getAttribute("value");
    }
    public ImmigrationListPage goToImmigrationPage(){
        System.out.println(linkImmigration.isDisplayed());
        linkImmigration.click();
        return new ImmigrationListPage(driver);
    }
}
