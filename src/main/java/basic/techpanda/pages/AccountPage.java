package basic.techpanda.pages;

import common.Commons;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.testng.AssertJUnit.assertTrue;

public class AccountPage extends Commons {
    WebDriver driver;
    @FindBy(id = "email")
    WebElement txtEmail;
    @FindBy(id = "pass")
    WebElement txtPassword;
    @FindBy(css = ".buttons-set>a[title='Create an Account']")
    WebElement btnCreateAnAccount;
    @FindBy(id = "advice-required-entry-email")
    WebElement msgEmail;
    @FindBy(id = "advice-required-entry-pass")
    WebElement msgPassword;
    @FindBy(id = "send2")
    WebElement btnLogin;
    public AccountPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    public MyDashboardPage login(String email, String password){
        txtEmail.sendKeys(email);
        txtPassword.sendKeys(password);
        btnLogin.click();
        return new MyDashboardPage(driver);
    }
    public CreateAccountPage goToCreateAccountPage(){
        btnCreateAnAccount.click();
        return new CreateAccountPage(driver);
    }
    public void verifyMessageDisplay(){
        btnLogin.click();
        assertTrue(msgEmail.isDisplayed() && msgPassword.isDisplayed());
    }
}
