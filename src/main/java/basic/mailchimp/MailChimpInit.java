package basic.mailchimp;

import common.InitBrowser;

public class MailChimpInit extends InitBrowser {
    MailChimpSignUp mailChimpSignUp;
    public MailChimpSignUp launchApplication(){
        startBrowser("chrome");
        mailChimpSignUp = new MailChimpSignUp(driver);
        navigateURL("https://login.mailchimp.com/signup/");
        return mailChimpSignUp;
    }
}
