package basic.orangehrm;

import basic.orangehrm.pages.LoginPage;
import common.InitBrowser;

public class InitApplication extends InitBrowser {
    LoginPage loginPage;
    public LoginPage launchApplication(){
        startBrowser("chrome");
        loginPage = new LoginPage(driver);
        navigateURL("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        return loginPage;
    }
}
