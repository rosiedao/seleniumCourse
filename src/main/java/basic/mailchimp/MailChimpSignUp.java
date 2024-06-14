package basic.mailchimp;

import common.Commons;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class MailChimpSignUp extends Commons {
    WebDriver driver;
    @FindBy(id = "email")
    WebElement txtEmail;
    @FindBy(id = "new_username")
    WebElement txtNewUsername;
    @FindBy(id = "new_password")
    WebElement txtNewPassword;
    @FindBy(css = ".lowercase-char")
    WebElement lowercaseCheck;
    @FindBy(css = ".uppercase-char")
    WebElement uppercaseCheck;
    @FindBy(css = ".number-char")
    WebElement numberCheck;
    @FindBy(css = ".special-char")
    WebElement specialCheck;
    @FindBy(xpath = "//span[contains(text(),'8 characters minimum')]/ancestor::li")
    WebElement lengthCheck;
    @FindBy(css = ".username-check")
    WebElement usernameCheck;
    public MailChimpSignUp(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    public boolean checkElementInputSuccess(WebElement e){
        return e.getAttribute("class").contains("success-check");
    }
    public void validateEmailInput(String email){
        txtEmail.sendKeys(email);
        txtNewUsername.click();
        assertTrue(checkElementInputSuccess(txtEmail));
        //txtNewPassword.click();
        //assertTrue(checkElementInputSuccess(txtNewUsername));
    }
    public void validatePasswordHasLowerCase(String password){
        txtNewPassword.sendKeys(password);
        boolean check = lowercaseCheck.getAttribute("class").contains("completed");
        assertTrue(check);
        txtNewPassword.clear();
    }
    public void validatePasswordHasUpperCase(String password){
        txtNewPassword.sendKeys(password);
        boolean check = uppercaseCheck.getAttribute("class").contains("completed");
        assertTrue(check);
        txtNewPassword.clear();
    }
    public void validatePasswordHasNumberCase(String password){
        txtNewPassword.sendKeys(password);
        boolean check = numberCheck.getAttribute("class").contains("completed");
        assertTrue(check);
        txtNewPassword.clear();
    }
    public void validatePasswordHasSpecialCase(String password){
        txtNewPassword.sendKeys(password);
        boolean check = specialCheck.getAttribute("class").contains("completed");
        assertTrue(check);
        txtNewPassword.clear();
    }
    public void validatePasswordHasLengthCase(String password){
        txtNewPassword.sendKeys(password);
        boolean check = lengthCheck.getAttribute("class").contains("completed");
        assertTrue(check);
        txtNewPassword.clear();
    }
    public void validatePasswordHasUsernameCase(String password){
        txtNewPassword.sendKeys(password);
        boolean check = usernameCheck.getAttribute("class").contains("completed");
        assertTrue(check);
        txtNewPassword.clear();
    }
}
