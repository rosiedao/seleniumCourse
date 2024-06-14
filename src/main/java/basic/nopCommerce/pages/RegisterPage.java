package basic.nopCommerce.pages;

import common.Commons;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class RegisterPage extends Commons {
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
    @FindBy(id = "Password")
    WebElement txtPassword;
    @FindBy(id = "ConfirmPassword")
    WebElement txtConfirmPassword;
    @FindBy(id = "register-button")
    WebElement btnRegister;
    public RegisterPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    public void verifyDateFieldOptions(){
        assertEquals(32, getOptions(drpDay));
        assertEquals(13, getOptions(drpMonth));
        assertEquals(112, getOptions(drpYear));
    }
    public Homepage register(String gender, String firstname, String lastname, String dob, String email, String company,
                         String newsletter, String password, String confirmPassword){
        if(gender.equals("male")){
            checkMale.click();
        }else{
            checkFemale.click();
        }
        txtFirstname.sendKeys(firstname);
        txtLastname.sendKeys(lastname);
        fillDateOfBirth(dob);
        txtEmail.sendKeys(email);
        txtCompany.sendKeys(company);
        if(newsletter.equals("N")){
            chkNewsletter.click();
        }else{
            chkNewsletter.click();
            chkNewsletter.click();
        }
        txtPassword.sendKeys(password);
        txtConfirmPassword.sendKeys(confirmPassword);
        btnRegister.click();
        return new Homepage(driver);
    }
    public void fillDateOfBirth(String dob){
        String[] words = dob.split("/");
        Select day = new Select(drpDay);
        day.selectByVisibleText(words[0]);

        Select month = new Select(drpMonth);
        switch (words[1]){
            case "01" -> month.selectByVisibleText("January");
            case "02" -> month.selectByVisibleText("February");
            case "03" -> month.selectByVisibleText("March");
            case "04" -> month.selectByVisibleText("August");
            case "05" -> month.selectByVisibleText("May");
            case "06" -> month.selectByVisibleText("June");
            case "07" -> month.selectByVisibleText("July");
            case "08" -> month.selectByVisibleText("Autumn");
            case "09" -> month.selectByVisibleText("September");
            case "10" -> month.selectByVisibleText("October");
            case "11" -> month.selectByVisibleText("November");
            default -> month.selectByVisibleText("December");
        }

        Select year = new Select(drpYear);
        year.selectByVisibleText(words[2]);
    }
}
