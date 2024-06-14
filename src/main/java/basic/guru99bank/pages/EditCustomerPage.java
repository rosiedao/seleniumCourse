package basic.guru99bank.pages;

import common.Commons;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

import static org.testng.AssertJUnit.assertEquals;

public class EditCustomerPage extends Commons {
    WebDriver driver;
    @FindBy(css = "input[name='name']")
    WebElement txtName;
    @FindBy(css = "input[name='gender']")
    WebElement txtGender;
    @FindBy(css = "input[name='dob']")
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
    @FindBy(css = "input[name='sub']")
    WebElement btnSubmit;
    public EditCustomerPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    public void verifyCustomerDetailInfo(String name, String gender, String dob, String address, String city, String state,
                                         String pin, String mobile, String email){
        /*System.out.println(txtName.getAttribute("value")+ ", " +txtGender.getAttribute("value"));
        System.out.println(dateDOB.getAttribute("value"));
        System.out.println(areaAddress.getAttribute("value"));
        System.out.println(txtCity.getAttribute("value"));
        System.out.println(txtState.getAttribute("value"));
        System.out.println(txtMobile.getAttribute("value"));
        System.out.println(txtEmail.getAttribute("value"));*/

        assertEquals(txtName.getAttribute("value"),name);
        assertEquals(txtGender.getAttribute("value"),gender);
        assertEquals(dateDOB.getAttribute("value"),convertDateFormat(dob));
        assertEquals(areaAddress.getAttribute("value"),address);
        assertEquals(txtCity.getAttribute("value"),city);
        assertEquals(txtState.getAttribute("value"),state);
        assertEquals(txtPIN.getAttribute("value"),pin);
        assertEquals(txtMobile.getAttribute("value"),mobile);
        assertEquals(txtEmail.getAttribute("value"),email);
    }
    public CustomerDetailPage editCustomer(String address, String city, String state, String pin, String mobile, String email){
        areaAddress.clear();
        areaAddress.sendKeys(address);

        txtCity.clear();
        txtCity.sendKeys(city);

        txtState.clear();
        txtState.sendKeys(state);

        txtPIN.clear();
        txtPIN.sendKeys(pin);

        txtMobile.clear();
        txtMobile.sendKeys(mobile);

        txtEmail.clear();
        txtEmail.sendKeys(email);

        btnSubmit.click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        return new CustomerDetailPage(driver);
    }
}
