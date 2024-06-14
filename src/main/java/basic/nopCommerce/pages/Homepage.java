package basic.nopCommerce.pages;

import common.Commons;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.testng.AssertJUnit.assertEquals;

public class Homepage extends Commons {
    WebDriver driver;
    @FindBy(css = ".result")
    WebElement textResult;
    @FindBy(linkText = "Register")
    WebElement linkRegister;
    @FindBy(linkText = "My account")
    WebElement linkMyAccount;
    public Homepage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    public RegisterPage goToRegister(){
        linkRegister.click();
        return new RegisterPage(driver);
    }
    public void verifyRegisterSuccess(){
        assertEquals(textResult.getText(),"Your registration completed");
    }
    public MyAccountPage goToMyAccount(){
        linkMyAccount.click();
        return new MyAccountPage(driver);
    }
}
