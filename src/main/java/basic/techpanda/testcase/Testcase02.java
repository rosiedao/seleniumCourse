package basic.techpanda.testcase;

import basic.techpanda.InitApplication;
import basic.techpanda.pages.*;
import org.testng.annotations.Test;

import java.util.HashMap;

public class Testcase02 extends InitApplication {
    HomePage homePage = launchApplication();
    AccountPage account = homePage.goToMyAccountPage();
    @Test
    public void registerSuccess(){
        CreateAccountPage createAccount = account.goToCreateAccountPage();
        /**
         * Create data
         */
        HashMap<String, String> data = new HashMap<>();
        data.put("firstname","Anh");
        data.put("middlename","Lan");
        data.put("lastname","Tran");
        data.put("email_address",createAccount.generateRandomEmail());
        data.put("password","Abc@123");
        data.put("confirmation","Abc@123");
        MyDashboardPage myDashboardPage = createAccount.fillAccountInformation(data.get("firstname"),data.get("middlename"),data.get("lastname"),
                data.get("email_address"), data.get("password"), data.get("confirmation"),false);

        myDashboardPage.verifyMessageWelcome(data.get("firstname"),data.get("middlename"),data.get("lastname"));
        myDashboardPage.verifyInformation(data.get("firstname"),data.get("middlename"),data.get("lastname"),
                data.get("email_address"));
    }
    @Test
    public void submitReviewProduct(){
        MyDashboardPage myDashboardPage = account.login("rosiedao@test.com","Abc@123");
        MobileListingPage mobileListingPage = myDashboardPage.gotoMobileListingPage();
        ProductDetailPage productDetailPage = mobileListingPage.goToProductDetail("Samsung Galaxy");
        ProductReviewPage productReviewPage = productDetailPage.goToProductReviewPage();
        productReviewPage.submitReview(3,"It's amazing","Good","Rosie");

    }
    @Test
    public void logout(){
        MyDashboardPage myDashboardPage = account.login("rosiedao@test.com","Abc@123");
        HomePage homePage1 = myDashboardPage.logout();

    }
}
