package basic.techpanda;

import basic.techpanda.pages.HomePage;
import common.InitBrowser;

public class InitApplication extends InitBrowser {
    HomePage homePage;

    public HomePage launchApplication(){
        startBrowser("chrome");
        homePage = new HomePage(driver);
        navigateURL("http://live.techpanda.org/");
        return homePage;
    }
}
