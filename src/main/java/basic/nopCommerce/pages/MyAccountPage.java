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
        verifyInputtedValue(txtFirstname,firstname);
        verifyInputtedValue(txtLastname,lastname);
        verifyInputtedValue(txtEmail,email);
        verifyInputtedValue(txtCompany,company);

        if(newsletter.equals("Y")){
            assertTrue(chkNewsletter.isSelected());
        }else{
            assertFalse(chkNewsletter.isSelected());
        }

        String[] words = dob.split("/");
        verifySelectedValue(drpDay,words[0]);
        switch (words[1]){
            case "01" -> verifySelectedValue(drpMonth,"January");
            case "02" -> verifySelectedValue(drpMonth,"February");
            case "03" -> verifySelectedValue(drpMonth,"March");
            case "04" -> verifySelectedValue(drpMonth,"August");
            case "05" -> verifySelectedValue(drpMonth,"May");
            case "06" -> verifySelectedValue(drpMonth,"June");
            case "07" -> verifySelectedValue(drpMonth,"July");
            case "08" -> verifySelectedValue(drpMonth,"Autumn");
            case "09" -> verifySelectedValue(drpMonth,"September");
            case "10" -> verifySelectedValue(drpMonth,"October");
            case "11" -> verifySelectedValue(drpMonth,"November");
            default ->   verifySelectedValue(drpMonth,"December");
        }
        verifySelectedValue(drpYear,words[2]);
    }
}
