package pageobjects.login;

import pageobjects.common.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ComfirmationPage extends AbstractComponent {
    WebDriver driver;
    @FindBy (css = ".hero-primary")
    WebElement confirmationMessage;

    public ComfirmationPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public String getConfirmationMessage(){
        return confirmationMessage.getText();
    }
}
