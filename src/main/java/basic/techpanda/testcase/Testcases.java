package basic.techpanda.testcase;

import basic.techpanda.InitApplication;
import basic.techpanda.pages.AccountPage;
import basic.techpanda.pages.CreateAccountPage;
import basic.techpanda.pages.HomePage;
import org.testng.annotations.Test;

public class Testcases extends InitApplication {
    HomePage homePage = launchApplication();
    AccountPage account = homePage.goToMyAccountPage();
    /** Test case 01
     * Step 1: Access page https://live.techpanda.org/
     * Step 2: Click link MY ACCOUNT on footer
     * Step 3: Verify url of Login Page
     * Step 4: Click CREATE AN ACCOUNT button
     * Step 5: Verify url of Register Page
     */
    @Test
    public void verifyCurrentURL(){
        account.verifyCurrentURLOfPage("http://live.techpanda.org/index.php/customer/account/login/");
        CreateAccountPage createAccount = account.goToCreateAccountPage();
        createAccount.verifyCurrentURLOfPage("http://live.techpanda.org/index.php/customer/account/create/");
    }
    /** Test case 02
     * Step 1: Access page https://live.techpanda.org/
     * Step 2: Click link MY ACCOUNT on footer
     * Step 3: Verify title of Login Page
     * Step 4: Click CREATE AN ACCOUNT button
     * Step 5: Verify title of Register Page
     */
    @Test
    public void verifyCurrentTitle(){
        account.verifyCurrentTitleOfPage("Customer Login");
        CreateAccountPage createAccount = account.goToCreateAccountPage();
        account.verifyCurrentTitleOfPage("Create New Customer Account");
    }
    /** Test case 03
     * Step 1: Access page https://live.techpanda.org/
     * Step 2: Click link MY ACCOUNT on footer
     * Step 3: Click CREATE AN ACCOUNT button
     * Step 4: Verify url of Register Page
     * Step 5: Back to Login Page
     * Step 6: Verify url of Login Page
     * Step 7: Forward to Register Page
     * Step 8: Verify title of Register Page
     */
    @Test
    public void backAndForwardPage(){
        CreateAccountPage createAccount = account.goToCreateAccountPage();
        createAccount.verifyCurrentURLOfPage("http://live.techpanda.org/index.php/customer/account/create/");
        account.back();
        account.verifyCurrentURLOfPage("http://live.techpanda.org/index.php/customer/account/login/");
        createAccount.forward();
        account.verifyCurrentTitleOfPage("Create New Customer Account");
    }
    /** Test case 04
     * Step 1: Access page https://live.techpanda.org/
     * Step 2: Click link MY ACCOUNT on footer
     * Step 3: Verify Login Page contains text Login or Create an Account
     * Step 4: Click CREATE AN ACCOUNT button
     * Step 5: Verify Register Page contains text Create an account
     */
    @Test
    public void getPageSource(){
        account.verifyContainTextInSourceCode("Login or Create an Account");
        CreateAccountPage createAccount = account.goToCreateAccountPage();
        createAccount.verifyContainTextInSourceCode("Create an Account");
    }
    @Test
    public void verifyEmptyEmailPassword(){
        account.verifyMessageDisplay();
    }
}
