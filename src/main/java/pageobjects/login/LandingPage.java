package pageobjects.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageobjects.common.AbstractComponent;

import java.time.Duration;

public class LandingPage extends AbstractComponent {
    WebDriver driver;
    public LandingPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy (id = "userEmail")
    WebElement userEmail;
    @FindBy (id = "userPassword")
    WebElement userPassword;
    @FindBy (id = "login")
    WebElement submit;
    @FindBy(css = ".toast-message")
    WebElement errorMessage;
    By errorMessageBy = By.cssSelector(".toast-message");
    public ProductCatalogue loginAppication(String email, String password){
        userEmail.sendKeys(email);
        userPassword.sendKeys(password);
        submit.click();
        return new ProductCatalogue(driver);
    }
    public void navigateURL(String url){
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));
    }
    public String getErrorMessage(){
        waitForElementToAppear(errorMessageBy,1);
        return errorMessage.getText();
    }
}
