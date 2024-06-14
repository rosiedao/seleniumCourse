package basic.nopCommerce.pages;

import common.Commons;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import static org.testng.AssertJUnit.*;

public class MyAccountPage extends Commons {
    WebDriver driver;
    @FindBy(id = "gender-male")
    WebElement checkMale;
    @FindBy(id = "gender-female")
    WebElement checkFemale;
    @FindBy(id = "FirstName")
    WebElement txtFirstname;
    @FindBy(id = "Email")
    WebElement txtEmail;
    @FindBy(id = "LastName")
    WebElement txtLastname;
    @FindBy(css = "select[name='DateOfBirthDay']")
    WebElement drpDay;
    @FindBy(css = "select[name='DateOfBirthMonth']")
    WebElement drpMonth;
    @FindBy(css = "select[name='DateOfBirthYear']")
    WebElement drpYear;
    @FindBy(id = "Company")
    WebElement txtCompany;
    @FindBy(id = "Newsletter")
    WebElement chkNewsletter;
    public MyAccountPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    public void verifyCustomerInfo(String gender, String firstname, String lastname, String dob, String email, String company,String newsletter){
        if(gender.equals("male")){
            assertTrue(checkMale.isSelected());
        }else{
            assertTrue(checkFemale.isSelected());
        }
        assertEquals(txtFirstname.getAttribute("value"),firstname);
        assertEquals(txtLastname.getAttribute("value"),lastname);
        assertEquals(txtEmail.getAttribute("value"),email);
        assertEquals(txtCompany.getAttribute("value"),company);
        if(newsletter.equals("Y")){
            assertTrue(chkNewsletter.isSelected());
        }else{
            assertFalse(chkNewsletter.isSelected());
        }
        String[] words = dob.split("/");
        Select day = new Select(drpDay);
        assertEquals(day.getFirstSelectedOption().getText(),words[0]);
        Select month = new Select(drpMonth);
        switch (words[1]){
            case "01" -> assertEquals(month.getFirstSelectedOption().getText(),"January");
            case "02" -> assertEquals(month.getFirstSelectedOption().getText(),"February");
            case "03" -> assertEquals(month.getFirstSelectedOption().getText(),"March");
            case "04" -> assertEquals(month.getFirstSelectedOption().getText(),"August");
            case "05" -> assertEquals(month.getFirstSelectedOption().getText(),"May");
            case "06" -> assertEquals(month.getFirstSelectedOption().getText(),"June");
            case "07" -> assertEquals(month.getFirstSelectedOption().getText(),"July");
            case "08" -> assertEquals(month.getFirstSelectedOption().getText(),"Autumn");
            case "09" -> assertEquals(month.getFirstSelectedOption().getText(),"September");
            case "10" -> assertEquals(month.getFirstSelectedOption().getText(),"October");
            case "11" -> assertEquals(month.getFirstSelectedOption().getText(),"November");
            default -> assertEquals(month.getFirstSelectedOption().getText(),"December");
        }
        Select year = new Select(drpYear);
        assertEquals(year.getFirstSelectedOption().getText(),words[2]);
    }
}
