package basic.guru99bank;

import basic.guru99bank.pages.LoginPage;
import common.InitBrowser;

public class InitApplication extends InitBrowser {
    LoginPage loginPage;
    public LoginPage launchApplication(){
        startBrowser("chrome");
        navigateURL("https://demo.guru99.com/v4/");
        loginPage = new LoginPage(driver);
        return loginPage;
    }
}
