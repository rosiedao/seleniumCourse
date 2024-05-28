package pageobjects.test;

import org.testng.annotations.Test;
import pageobjects.common.InitBaseTest;
import pageobjects.login.ProductCatalogue;

public class ErrorValidationTest extends InitBaseTest {
    @Test
    public void loginThenAddToCart() {
        String productName = "ZARA COAT 3";

        ProductCatalogue productCatalogue = landingPage.loginAppication("anshika@gmail.com", "Iamking@000111");
        System.out.println(landingPage.getErrorMessage());
    }
}
