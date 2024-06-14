package basic.guru99bank.pages;

import common.Commons;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static org.testng.AssertJUnit.assertEquals;

public class CustomerDetailPage extends Commons {
    WebDriver driver;
    By columns = By.cssSelector("table#customer td");
    public CustomerDetailPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    public String getCustomerId(){
        return getValueByColumn("Customer ID");
    }
    public String getValueByColumn(String column){
        List<WebElement> info = driver.findElements(columns);
        String value = "";
        for(WebElement e : info) {
            if (e.getText().equals(column))
                value = e.findElement(By.xpath("following-sibling::td")).getText();
        }
        //System.out.println(value);
        return value;
    }
    public void verifyCustomerDetailInfo(String name, String gender, String dob, String address, String city, String state,
                                         String pin, String mobile, String email){
        assertEquals(getValueByColumn("Customer Name"),name);
        assertEquals(getValueByColumn("Gender"),gender);
        assertEquals(getValueByColumn("Birthdate"),convertDateFormat(dob));
        assertEquals(getValueByColumn("Address"),address);
        assertEquals(getValueByColumn("City"),city);
        assertEquals(getValueByColumn("State"),state);
        assertEquals(getValueByColumn("Pin"),pin);
        assertEquals(getValueByColumn("Mobile No."),mobile);
        assertEquals(getValueByColumn("Email"),email);
    }
}
