package basic.techpanda.pages;

import common.Commons;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.testng.AssertJUnit.assertTrue;

public class ProductReviewPage extends Commons {
    WebDriver driver;
    @FindBy(id = "review_field")
    WebElement areaReview;
    @FindBy(id = "summary_field")
    WebElement txtSummary;
    @FindBy(id = "nickname_field")
    WebElement txtNickname;
    @FindBy(id = "Quality 1_1")
    WebElement chkQuantityOne;
    @FindBy(id = "Quality 1_2")
    WebElement chkQuantityTwo;
    @FindBy(id = "Quality 1_3")
    WebElement chkQuantityThree;
    @FindBy(id = "Quality 1_4")
    WebElement chkQuantityFour;
    @FindBy(id = "Quality 1_5")
    WebElement chkQuantityFive;
    @FindBy(css = "button[title='Submit Review']")
    WebElement btnSubmitReview;
    @FindBy(css = ".success-msg span")
    WebElement msgSubmitSuccess;
    public ProductReviewPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    public void submitReview(int quantity, String review, String summary, String nickname){
        switch (quantity) {
            case 1 -> chkQuantityOne.click();
            case 2 -> chkQuantityTwo.click();
            case 3 -> chkQuantityThree.click();
            case 4 -> chkQuantityFour.click();
            default -> chkQuantityFive.click();
        }
        areaReview.sendKeys(review);
        txtSummary.sendKeys(summary);
        txtNickname.sendKeys(nickname);
        btnSubmitReview.click();
        assertTrue(msgSubmitSuccess.getText().contains("Your review has been accepted for moderation."));
    }
}
