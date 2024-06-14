package basic.guru99bank.pages;

import common.Commons;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ManagerHomepage extends Commons {
    WebDriver driver;
    @FindBy(linkText = "New Customer")
    WebElement linkNewCustomer;
    @FindBy(linkText = "Edit Customer")
    WebElement linkEditCustomer;

    public ManagerHomepage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    public NewCustomerPage goToNewCustomer(){
        linkNewCustomer.click();
        return new NewCustomerPage(driver);
    }
    public SearchCustomerPage goToEditCustomer(){
        linkEditCustomer.click();
        return new SearchCustomerPage(driver);
    }
}
