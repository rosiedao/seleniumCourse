package basic.techpanda.testcase;

import basic.techpanda.InitApplication;
import basic.techpanda.pages.HomePage;
import basic.techpanda.pages.MobileListingPage;
import org.testng.annotations.Test;

public class ProductTest extends InitApplication {
    HomePage homePage = launchApplication();
    @Test
    public void addProductToCompare(){
        MobileListingPage mobileListingPage = homePage.goToMobilePage();

    }
}
