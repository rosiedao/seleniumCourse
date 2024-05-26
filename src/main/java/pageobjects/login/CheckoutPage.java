package pageobjects.login;

import common.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage extends AbstractComponent {
    WebDriver driver;
    @FindBy(css = "[placeholder='Select Country']")
    WebElement country;
    @FindBy(css=".action__submit")
    WebElement submit;
    @FindBy(xpath = "(//button[contains(@class,'ta-item')])[2]")
    WebElement selectCountry;
    By result = By.cssSelector(".ta-results");
    public CheckoutPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    public void selectCountry(String countryName){
        Actions a = new Actions(driver);
        a.sendKeys(country,countryName).build().perform();
        waitForElementToAppear(result,5);
        selectCountry.click();
    }
    public ComfirmationPage submitOrder(){
        submit.click();
        return new ComfirmationPage(driver);
    }
}
