package basic.orangehrm.pages;

import common.Commons;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

import static org.testng.AssertJUnit.assertFalse;

public class AddEmployeePage extends Commons {
    WebDriver driver;
    @FindBy (css = "input[name='firstName']")
    WebElement txtFirstname;
    @FindBy (css = "input[name='middleName']")
    WebElement txtMiddleName;
    @FindBy (css = "input[name='lastName']")
    WebElement txtLastname;
    @FindBy (xpath = "//label[contains(text(),'Employee Id')]/following::div/input")
    WebElement txtEmployeeId;
    @FindBy (css = ".oxd-switch-input")
    WebElement switchLoginDetail;
    @FindBy(xpath = "(//label[contains(text(),'Username')]/following::div)[1]/input")
    WebElement txtUsername;
    @FindBy(xpath = "(//label[contains(text(),'Password')]/following::div)[1]/input")
    WebElement txtPassword;
    @FindBy(xpath = "(//label[contains(text(),'Confirm Password')]/following::div)[1]/input")
    WebElement txtConfirmPassword;
    @FindBy(xpath = "//input[@type='radio' and @value='1']")
    WebElement radioEnabledStatus;
    @FindBy(xpath = "//input[@type='radio' and @value='2']")
    WebElement radioDisabledStatus;
    @FindBy(css = "input[type='file']")
    WebElement file;
    @FindBy(css = "button[type='submit']")
    WebElement btnSave;
    @FindBy(css = ".employee-image")
    WebElement imgEmployeeProfile;
    public AddEmployeePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    public PersonalDetailPage addEmployee(String firstname, String middleName, String lastname, String username,
                                          String isEnable, String password, String confirmPassword,String filePath){
        txtFirstname.sendKeys(firstname);
        txtMiddleName.sendKeys(middleName);
        txtLastname.sendKeys(lastname);
        txtEmployeeId.clear();
        //txtEmployeeId.sendKeys("0" + generateRandomNumber(3));
        switchLoginDetail.click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        txtUsername.sendKeys(username);
        if(isEnable.equals("No")){
            radioDisabledStatus.click();
        }
        txtPassword.sendKeys(password);
        txtConfirmPassword.sendKeys(confirmPassword);
        file.sendKeys(filePath);
        //verifyUploadImageSuccess();
        btnSave.click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        return new PersonalDetailPage(driver);
    }
    public String getEmployeeId(){
        System.out.println("Employee ID of add new screen is: " + txtEmployeeId.getAttribute("value"));
        return txtEmployeeId.getAttribute("value");
    }
    public void verifyUploadImageSuccess(){
        System.out.println(imgEmployeeProfile.getAttribute("value"));
        //assertFalse(imgEmployeeProfile.getAttribute("scr").equals("/web/images/default-photo.png"));
    }
}
