package basic.techpanda.pages;

import common.Commons;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateAccountPage extends Commons {
    WebDriver driver;
    @FindBy(id = "firstname")
    WebElement txtFirstName;
    @FindBy(id = "middlename")
    WebElement txtMiddleName;
    @FindBy(id = "lastname")
    WebElement txtLastName;
    @FindBy(id = "email_address")
    WebElement txtEmail;
    @FindBy(id = "password")
    WebElement txtPassword;
    @FindBy(id = "confirmation")
    WebElement txtConfirmation;
    @FindBy(id = "is_subscribed")
    WebElement chkIsSubcribed;
    @FindBy(css = "button[title='Register']")
    WebElement btnRegister;
    public CreateAccountPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    public MyDashboardPage fillAccountInformation(String firstName, String middleName, String lastName, String email,
                                                  String password, String confirmPassword, boolean isSubcribed){
        txtFirstName.sendKeys(firstName);
        txtMiddleName.sendKeys(middleName);
        txtLastName.sendKeys(lastName);
        txtEmail.sendKeys(email);
        txtPassword.sendKeys(password);
        txtConfirmation.sendKeys(confirmPassword);
        if(isSubcribed) chkIsSubcribed.click();
        btnRegister.click();
        return new MyDashboardPage(driver);
    }
    public String generateRandomEmail(){
        return generateRandomString(10)+"@test.com";
    }
}
