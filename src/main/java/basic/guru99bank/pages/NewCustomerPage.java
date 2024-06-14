package basic.guru99bank.pages;

import common.Commons;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class NewCustomerPage extends Commons {
    WebDriver driver;
    @FindBy(css = "input[name='name']")
    WebElement txtName;
    @FindBy(css = "input[name='rad1'][value='m']")
    WebElement chkMale;
    @FindBy(css = "input[name='rad1'][value='f']")
    WebElement chkFemale;
    @FindBy(id = "dob")
    WebElement dateDOB;
    @FindBy(css = "textarea[name='addr']")
    WebElement areaAddress;
    @FindBy(css = "input[name='city']")
    WebElement txtCity;
    @FindBy(css = "input[name='state']")
    WebElement txtState;
    @FindBy(css = "input[name='pinno']")
    WebElement txtPIN;
    @FindBy(css = "input[name='telephoneno']")
    WebElement txtMobile;
    @FindBy(css = "input[name='emailid']")
    WebElement txtEmail;
    @FindBy(css = "input[name='password']")
    WebElement txtPassword;
    @FindBy(css = "input[name='sub']")
    WebElement btnSubmit;
    public NewCustomerPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    public CustomerDetailPage addNewCustomer(String name, String gender, String dob, String address, String city, String state,
                                             String pin, String mobile, String email, String password){
        txtName.sendKeys(name);
        if (gender.equals("male")) {
            chkMale.click();
        } else {
            chkFemale.click();
        }
        // Input date of birth
        inputDateTime(dateDOB,dob);
        areaAddress.sendKeys(address);
        txtCity.sendKeys(city);
        txtState.sendKeys(state);
        txtPIN.sendKeys(pin);
        txtMobile.sendKeys(mobile);
        txtEmail.sendKeys(email);
        txtPassword.sendKeys(password);
        System.out.println(dateDOB.getAttribute("value"));
        btnSubmit.click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        return new CustomerDetailPage(driver);
    }
}
