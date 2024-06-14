package basic.mailchimp;
import org.testng.annotations.Test;

public class ApplicationTC extends InitApp {
    PageElement pageElement = launchApplication();
    @Test
    public void testCase1(){
        pageElement.checkElementDisplayed();
        pageElement.checkUser5Displayed();
        pageElement.checkEnableElement();
    }
    @Test
    public void testcase2(){
        pageElement.checkElementChecked();
    }
}
