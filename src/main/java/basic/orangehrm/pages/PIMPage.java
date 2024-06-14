package basic.orangehrm.pages;

import common.Commons;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PIMPage extends Commons {
    WebDriver driver;
    @FindBy(xpath = "//li//a[contains(text(),'Add Employee')]")
    WebElement linkAddEmployee;
    public PIMPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    public AddEmployeePage goToAddEmployeePage(){
        linkAddEmployee.click();
        return new AddEmployeePage(driver);
    }
}
