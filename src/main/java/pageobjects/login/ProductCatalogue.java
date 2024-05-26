package pageobjects.login;

import common.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class ProductCatalogue extends AbstractComponent {
    WebDriver driver;
    public ProductCatalogue(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(css=".mb-3")
    List<WebElement> products;
    @FindBy(css = "[routerlink*='cart'")
            WebElement btnCart;
    By productsBy = By.cssSelector(".mb-3");
    By addToCart = By.cssSelector(".card-body button:last-of-type");
    By toastMessage = By.cssSelector("#toast-container");
    By aminating = By.cssSelector(".ng-animating");
    public List<WebElement> getProductsList(){
        waitForElementToAppear(productsBy,5);
        return products;
    }

    public WebElement getProductByName(String productName){
        return getProductsList().stream().filter(product ->
                product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
    }

    public void addProductToCart(String productName){
        getProductByName(productName).findElement(addToCart).click();
        waitForElementToAppear(toastMessage,5);
        waitForElementToDisappear(aminating,3);
    }

    public CartPage goToCartPage(){
        btnCart.click();
        return new CartPage(driver);
    }
}
