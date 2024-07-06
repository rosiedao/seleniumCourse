package basic.techpanda.pages;

import common.Commons;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class MobileListingPage extends Commons {
    WebDriver driver;
    By listProductCSS = By.cssSelector(".product-name a");
    By listProductCompared = By.cssSelector("ol#compare-items p.product-name a");
    @FindBy(css = "li.success-msg span")
    WebElement successMsg;
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
    public void addToCompare(String productName){
        driver.findElement(By.xpath("//a[@title='" + productName + "']//ancestor::div[@class='product-info']//a[@class='link-compare']")).click();
        sleep(3);
        //System.out.println(successMsg.getText());
        assertEquals("The product " + productName +" has been added to comparison list.",successMsg.getText());

        List<WebElement> comparedProductList = driver.findElements(listProductCompared);
        WebElement selectedCompareProduct = comparedProductList.stream().filter(e -> e.getText().equals(productName.toUpperCase())).findFirst().orElse(null);
        assert selectedCompareProduct != null;
        assertTrue(selectedCompareProduct.isDisplayed());
        sleep(5);
    }
}
