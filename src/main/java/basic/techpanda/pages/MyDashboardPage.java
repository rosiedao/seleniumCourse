package basic.techpanda.pages;

import common.Commons;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.testng.AssertJUnit.assertEquals;

public class MyDashboardPage extends Commons {
    WebDriver driver;
    @FindBy(css = ".success-msg span")
    WebElement msgRegisterSuccess;
    @FindBy(css = "p.welcome-msg")
    WebElement msgWelcome;
    @FindBy(xpath = "//a[contains(text(),'Change Password')]//ancestor::p")
    WebElement emailBox;
    @FindBy(xpath = "//a[contains(text(),'Mobile')]")
    WebElement linkMobile;
    @FindBy(xpath = "//a//span[contains(text(),'Account')]")
    WebElement linkAccount;
    @FindBy(css = "#header-account a[title='Log Out']")
    WebElement linkLogOut;
    @FindBy(css = "#header-account a[title='My Account']")
    WebElement linkMyAccount;
    @FindBy(css = "#header-account a[title='My Wishlist']")
    WebElement linkMyWishlist;
    @FindBy(css = "#header-account a[title='My Cart']")
    WebElement linkMyCart;
    @FindBy(css = "#header-account a[title='Checkout']")
    WebElement linkCheckout;
    public MyDashboardPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void verifyMessageWelcome(String firstname, String middleName, String lastname){
        assertEquals(msgRegisterSuccess.getText(),"Thank you for registering with Main Website Store.");
        String[] words = msgWelcome.getText().split(" ");
        for(String w:words){
            System.out.println(w);
        }
        assertEquals(firstname.toUpperCase(), words[1]);
        assertEquals(middleName.toUpperCase(), words[2]);
        assertEquals(lastname.toUpperCase(), words[3].replace("!",""));
    }
    public void verifyInformation(String firstname, String middleName, String lastname, String email){
        String[] lines = emailBox.getText().split("\n");
        assertEquals(email, lines[1]);
        String[] words = lines[0].split(" ");
        assertEquals(firstname, words[0]);
        assertEquals(middleName, words[1]);
        assertEquals(lastname, words[2]);
    }
    public MobileListingPage gotoMobileListingPage(){
        linkMobile.click();
        return new MobileListingPage(driver);
    }
    public HomePage logout(){
        linkAccount.click();
        linkLogOut.click();
        return new HomePage(driver);
    }
}
