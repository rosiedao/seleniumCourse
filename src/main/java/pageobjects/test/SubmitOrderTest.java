package pageobjects.test;

import pageobjects.common.InitBaseTest;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import pageobjects.common.OrderPage;
import pageobjects.login.CartPage;
import pageobjects.login.CheckoutPage;
import pageobjects.login.ComfirmationPage;
import pageobjects.login.ProductCatalogue;

import java.util.List;

import static org.testng.AssertJUnit.assertTrue;

public class SubmitOrderTest extends InitBaseTest {
    String productName = "ZARA COAT 3";
    @Test
    public void loginThenAddToCart(){
        ProductCatalogue productCatalogue = landingPage.loginAppication("anshika@gmail.com","Iamking@000");
        List<WebElement> products = productCatalogue.getProductsList();
        productCatalogue.getProductByName(productName);
        productCatalogue.addProductToCart(productName);
        productCatalogue.goToCartPage();

        CartPage cartPage = productCatalogue.goToCartPage();
        assertTrue(cartPage.verifyProductDisplay(productName));

        CheckoutPage checkoutPage = cartPage.goToCheckout();
        checkoutPage.selectCountry("india");

        ComfirmationPage comfirmationPage = checkoutPage.submitOrder();
        System.out.println(comfirmationPage.getConfirmationMessage());
        assertTrue(comfirmationPage.getConfirmationMessage().equalsIgnoreCase("THANKYOU FOR THE ORDER."));
    }

    @Test (dependsOnMethods = {"loginThenAddToCart"})
    public void orderHistoryTest(){
        ProductCatalogue productCatalogue = landingPage.loginAppication("anshika@gmail.com","Iamking@000");
        OrderPage orderPage = productCatalogue.goToOrderPage();
        assertTrue(orderPage.verifyOrderDisplay(productName));
    }
}
