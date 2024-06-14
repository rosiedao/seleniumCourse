package basic.techpanda.pages;

import common.Commons;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductDetailPage extends Commons {
    WebDriver driver;
    @FindBy(xpath = "//a[contains(text(),'Add Your Review')]")
    WebElement linkAddYourReview;
    public ProductDetailPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    public ProductReviewPage goToProductReviewPage(){
        linkAddYourReview.click();
        return new ProductReviewPage(driver);
    }
}
