package basic.orangehrm.pages;

import common.Commons;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;
import java.util.List;

public class DashboardPage extends Commons {
    WebDriver driver;
    By leftMenuBy = By.cssSelector(".oxd-main-menu-item-wrapper span");
    @FindBy(css = ".oxd-userdropdown-tab i")
    WebElement arrowAccount;
    @FindBy(linkText = "Logout")
    WebElement linkLogout;
    public DashboardPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    public void selectMenuFromLeftSide(String menu){
        List<WebElement> menuList = driver.findElements(leftMenuBy);
        WebElement selectedMenu = menuList.stream().filter(e -> e.getText().contains(menu)).findFirst().orElse(null);
        assert selectedMenu != null;
        System.out.println(selectedMenu.isDisplayed());
        selectedMenu.click();
    }
    public PIMPage goToPIMPage(){
        selectMenuFromLeftSide("PIM");
        return new PIMPage(driver);
    }
    public LoginPage logout(){
        arrowAccount.click();
        linkLogout.click();
        return new LoginPage(driver);
    }
    public PersonalDetailPage goToMyInfo(){
        selectMenuFromLeftSide("My Info");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        return new PersonalDetailPage(driver);
    }
}
