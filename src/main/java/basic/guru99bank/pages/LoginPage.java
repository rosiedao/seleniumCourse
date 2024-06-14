package basic.guru99bank.pages;

import common.Commons;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends Commons {
    WebDriver driver;
    @FindBy(css = "input[name='uid']")
    WebElement txtUserId;
    @FindBy(css = "input[name='password']")
    WebElement txtPassword;
    @FindBy(css = "input[name='btnLogin']")
    WebElement btnLogin;
    public LoginPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    public ManagerHomepage login(String userId, String password){
        txtUserId.sendKeys(userId);
        txtPassword.sendKeys(password);
        btnLogin.click();
        return new ManagerHomepage(driver);
    }
}
