package basic.orangehrm.pages;

import common.Commons;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class LoginPage extends Commons {
    WebDriver driver;
    @FindBy(css = "input[name='username']")
    WebElement txtUsername;
    @FindBy(css = "input[name='password']")
    WebElement txtPassword;
    @FindBy(tagName = "button")
    WebElement btnLogin;
    public LoginPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    public DashboardPage login(String username, String password){
        txtUsername.sendKeys(username);
        txtPassword.sendKeys(password);
        btnLogin.click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        return new DashboardPage(driver);
    }
}
