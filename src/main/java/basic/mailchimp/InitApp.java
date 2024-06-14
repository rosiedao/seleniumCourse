package basic.mailchimp;

import common.InitBrowser;

public class InitApp extends InitBrowser {
    public PageElement launchApplication(){
        startBrowser("chrome");
        PageElement pageElement =new PageElement(driver);
        navigateURL("https://automationfc.github.io/basic-form/index.html");
        return pageElement;
    }
}
