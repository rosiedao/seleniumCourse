package basic.nopCommerce;

import basic.nopCommerce.pages.Homepage;
import common.InitBrowser;

import java.time.Duration;

public class InitApplication extends InitBrowser {
    Homepage homepage;
    public Homepage launchApplication(){
        startBrowser("chrome");
        homepage = new Homepage(driver);
        navigateURL("https://demo.nopcommerce.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        return homepage;
    }
}
