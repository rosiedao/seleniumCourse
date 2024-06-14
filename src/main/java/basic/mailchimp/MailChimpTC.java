package basic.mailchimp;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class MailChimpTC extends MailChimpInit{
    WebDriver driver;
    @Test
    public void testcase1(){
        MailChimpSignUp signUp = launchApplication();
        signUp.validateEmailInput("automation@test.com");
        signUp.validatePasswordHasLowerCase("abc");
        signUp.validatePasswordHasUpperCase("ABC");
        signUp.validatePasswordHasNumberCase("123");
        signUp.validatePasswordHasSpecialCase("!123");
        signUp.validatePasswordHasLengthCase("12345678");
        signUp.validatePasswordHasUsernameCase("automation");
    }
}
