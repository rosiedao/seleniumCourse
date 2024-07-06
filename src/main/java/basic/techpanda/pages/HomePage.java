package basic.techpanda.pages;

import common.Commons;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends Commons {
    WebDriver driver;
    @FindBy(xpath = "//a[@title='Site Map']//following::a[@title='My Account']")
    WebElement linkMyAccount;
    @FindBy(xpath = "/a[text()='Mobile']")
    WebElement linkMobile;
    public HomePage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    public AccountPage goToMyAccountPage(){
        linkMyAccount.click();
        return new AccountPage(driver);
    }
    public MobileListingPage goToMobilePage(){
        linkMobile.click();
        return new MobileListingPage(driver);
    }
}
