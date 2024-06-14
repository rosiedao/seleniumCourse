package basic.guru99bank.pages;

import common.Commons;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchCustomerPage extends Commons {
    WebDriver driver;
    @FindBy(css = "input[name='cusid']")
    WebElement txtCustomerId;
    @FindBy(css = "input[name='AccSubmit']")
    WebElement btnSubmit;
    public SearchCustomerPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    public EditCustomerPage searchCustomerId(String customerID){
        txtCustomerId.sendKeys(customerID);
        btnSubmit.click();
        return new EditCustomerPage(driver);
    }
}
