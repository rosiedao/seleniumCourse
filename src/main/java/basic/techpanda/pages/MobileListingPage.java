package basic.techpanda.pages;

import common.Commons;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class MobileListingPage extends Commons {
    WebDriver driver;
    By listProductCSS = By.cssSelector(".product-name a");
    public MobileListingPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    public ProductDetailPage goToProductDetail(String productName){
        List<WebElement> listProduct = driver.findElements(listProductCSS);
        WebElement product = listProduct.stream().filter(e -> e.getText().contains(productName.toUpperCase())).findFirst().orElse(null);
        assert product != null;
        product.click();
        return new ProductDetailPage(driver);
    }
}
