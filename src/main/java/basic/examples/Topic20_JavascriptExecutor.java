package basic.examples;

import common.InitBrowser;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class Topic20_JavascriptExecutor extends InitBrowser {
    @BeforeClass
    public void start(){
        startBrowser("chrome");
    }
    @Test
    public void TC01(){
        //navigateURL("http://live.techpanda.org/");
        //Using JE to navigate url
        js.executeScript("window.location='http://live.techpanda.org/'");
        sleep(3);
        //Using JE to get page domain
        String domain = (String) js.executeScript("return document.domain;");
        assertEquals("live.techpanda.org",domain);
        //Using JE to get URL
        String url = (String) js.executeScript("return document.URL;");
        assertEquals("http://live.techpanda.org/",url);
        //Using JE to open Mobile page
        WebElement mobileLink = driver.findElement(By.xpath("//a[text()='Mobile']"));
        highlightElement(mobileLink);
        js.executeScript("arguments[0].click();",mobileLink);
        //Using JE to add product Samsung Galaxy to cart
        String product = "Samsung Galaxy";
        WebElement btnCartSamsung = driver.findElement(By.xpath("//a[@title='" + product + "']//ancestor::div[@class='product-info']/div[@class='actions']/button"));
        js.executeScript("arguments[0].click();",btnCartSamsung);
        sleep(3);
        //Using JE to get inner text to validate message
        String sText = js.executeScript("return document.documentElement.innerText;").toString();
        assertTrue(sText.contains("Samsung Galaxy was added to your shopping cart."));
        //Using JS to open Customer Service Page
        WebElement customerServiceLink = driver.findElement(By.xpath("//a[text()='Customer Service']"));
        highlightElement(customerServiceLink);
        js.executeScript("arguments[0].click();",customerServiceLink);
        //Using JS to scroll to element Newsletter at the end of page
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
        //Using JS to input valid email
        js.executeScript("document.getElementById('newsletter').setAttribute('value','test1@test.com');");
        //Using JS to click button Subscribe
        js.executeScript("arguments[0].click()",driver.findElement(By.cssSelector("button[title='Subscribe']")));
        //Using JS to validate message
        String innerText = js.executeScript("return document.documentElement.innerText;").toString();
        assertTrue(innerText.contains("There was a problem with the subscription: This email address is already assigned to another user."));
        //Navigate to facebook page
        js.executeScript("window.location='https://www.google.com/'");
        assertEquals(js.executeScript("return document.title;"),"Google");

    }
    public void highlightElement(WebElement element){
        String originalStyle = element.getAttribute("style");
        js.executeScript("arguments[0].setAttribute('style', arguments[1])",element, "border: 2px solid red; border-style:dashed;");
        sleep(2);
        js.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
    }
    public Object executeForBrowser(String javaScript) {
        return js.executeScript(javaScript);
    }

    public String getInnerText() {
        return (String) js.executeScript("return document.documentElement.innerText;");
    }

    public boolean isExpectedTextInInnerText(String textExpected) {
        String textActual = (String) js.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
        return textActual.equals(textExpected);
    }

    public void scrollToBottomPage() {
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public void navigateToUrlByJS(String url) {
        js.executeScript("window.location = '" + url + "'");
        sleep(3);
    }

    public void clickToElementByJS(String locator) {
        js.executeScript("arguments[0].click();", getElement(locator));
        sleep(3);
    }

    public void scrollToElementOnTop(String locator) {
        js.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
    }

    public void scrollToElementOnDown(String locator) {
        js.executeScript("arguments[0].scrollIntoView(false);", getElement(locator));
    }

    public void setAttributeInDOM(String locator, String attributeName, String attributeValue) {
        js.executeScript("arguments[0].setAttribute('" + attributeName + "', '" + attributeValue +"');", getElement(locator));
    }

    public void removeAttributeInDOM(String locator, String attributeRemove) {
        js.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(locator));
    }

    public void sendkeyToElementByJS(String locator, String value) {
        js.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(locator));
    }

    public String getAttributeInDOM(String locator, String attributeName) {
        return (String) js.executeScript("return arguments[0].getAttribute('" + attributeName + "');", getElement(locator));
    }

    public String getElementValidationMessage(String locator) {
        return (String) js.executeScript("return arguments[0].validationMessage;", getElement(locator));
    }

    public boolean isImageLoaded(String locator) {
        boolean status = (boolean) js.executeScript(
                "return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", getElement(locator));
        return status;
    }

    public WebElement getElement(String locator) {
        return driver.findElement(By.xpath(locator));
    }
    @Test
    public void TC02_ValidateErrorMessage(){
        navigateURL("https://automationfc.github.io/html5/index.html");
        WebElement btnSubmit = driver.findElement(By.className("btn"));
        WebElement name = driver.findElement(By.id("fname"));
        WebElement password = driver.findElement(By.id("pass"));
        WebElement email = driver.findElement(By.id("em"));
        WebElement address = driver.findElement(By.tagName("select"));

        //Verify message of field Name
        btnSubmit.click();
        String actualMsg = js.executeScript("return arguments[0].validationMessage;", name).toString();
        assertEquals("Please fill out this field.", actualMsg);
        //Verify message of Password
        name.sendKeys("automation");
        btnSubmit.click();
        assertEquals("Please fill out this field.",js.executeScript("return arguments[0].validationMessage;",password).toString());
        //Verify message for email
        password.sendKeys("automation");
        btnSubmit.click();
        assertEquals("Please fill out this field.",js.executeScript("return arguments[0].validationMessage;",email).toString());
        email.sendKeys("rosie@");
        btnSubmit.click();
        assertTrue(js.executeScript("return arguments[0].validationMessage;",email).toString().contains("Please enter a part following '@'. 'rosie@' is incomplete."));
        email.sendKeys("rosie@test.com");
        btnSubmit.click();
        assertEquals("Please select an item in the list.",js.executeScript("return arguments[0].validationMessage;",address).toString());
    }
    @Test
    public void TC03_HTML5ValidationMessage(){
        navigateURL("https://login.ubuntu.com/");
        WebElement email = driver.findElement(By.cssSelector("#login-form #id_email"));
        email.sendKeys("a");
        driver.findElement(By.cssSelector("#login-form button")).click();
        assertEquals("Please include an '@' in the email address. 'a' is missing an '@'.",js.executeScript("return arguments[0].validationMessage;",email).toString());
    }
    @Test
    public void TC04_RemoveAttribute(){
        navigateURL("http://demo.guru99.com/v4");
        WebElement userId = driver.findElement(By.cssSelector("input[name='uid']"));
        WebElement password = driver.findElement(By.cssSelector("input[name='password']"));
        WebElement btnSubmit = driver.findElement(By.cssSelector("input[name='btnLogin']"));
        String username = "mngr576613";
        String passwordValue = "sAsAqYr";
        js.executeScript("arguments[0].setAttribute('value','" + username + "');",userId);
        js.executeScript("arguments[0].setAttribute('value','" + passwordValue + "');",password);
        js.executeScript("arguments[0].click();", btnSubmit);
        sleep(3);
        WebElement linkNewCustomer = driver.findElement(By.linkText("New Customer"));

        js.executeScript("arguments[0].click();",linkNewCustomer);
        sleep(3);

        WebElement cus_name = driver.findElement(By.cssSelector("input[name='name']"));
        WebElement gender_Male = driver.findElement(By.cssSelector("input[name='rad1'][value='m']"));
        WebElement gender_Female = driver.findElement(By.cssSelector("input[name='rad1'][value='f']"));
        WebElement dateOfBirth = driver.findElement(By.id("dob"));
        WebElement address = driver.findElement(By.tagName("textarea"));
        WebElement city = driver.findElement(By.cssSelector("input[name='city']"));
        WebElement state = driver.findElement(By.cssSelector("input[name='state']"));
        WebElement pin = driver.findElement(By.cssSelector("input[name='pinno']"));
        WebElement mobile = driver.findElement(By.cssSelector("input[name='telephoneno']"));
        WebElement email = driver.findElement(By.cssSelector("input[name='emailid']"));
        WebElement cus_password = driver.findElement(By.cssSelector("input[name='password']"));
        WebElement btnSumitCustomer = driver.findElement(By.cssSelector("input[name='sub']"));

        js.executeScript("arguments[0].setAttribute('value', arguments[1]);",email,"icandoit@test.com");
        js.executeScript("arguments[0].setAttribute('value', arguments[1]);",city,"new york");
        js.executeScript("arguments[0].setAttribute('value', arguments[1]););",mobile,"234242342");
        js.executeScript("arguments[0].setAttribute('value', arguments[1]););",pin,"093333");
        js.executeScript("arguments[0].setAttribute('value', arguments[1]););",state,"california");
        js.executeScript("arguments[0].setAttribute('value', arguments[1]););",cus_name,"icandoit");
        js.executeScript("arguments[0].setAttribute('value', arguments[1]););",cus_password,"Admin123");
        js.executeScript("arguments[0].removeAttribute('type');",dateOfBirth);
        js.executeScript("arguments[0].setAttribute('value',arguments[1]););",dateOfBirth,"14/03/1999");
        js.executeScript("arguments[0].click();",gender_Female);
        sendkeyToElementByJS(("//textarea[@name='addr']"),"Address 123");
        //js.executeScript("arguments[0].setAttribute('value',arguments[1]););",address,"14/03/1999");
        //js.executeScript("arguments[0].click();",btnSumitCustomer);

        sleep(5);
        //System.out.println(js.executeScript("return document.title;").toString());
    }
    @Test
    public void TC05_CreateAnAccount(){
        js.executeScript("window.location='http://live.techpanda.org/';");
        sleep(3);
        WebElement linkMyAccount = driver.findElement(By.cssSelector("#header-account a[title='My Account']"));
        js.executeScript("arguments[0].click;",linkMyAccount);
        sleep(3);
        /*WebElement linkCreateAnAccount = driver.findElement(By.cssSelector(".buttons-set>a[title='Create an Account']"));
        js.executeScript("arguments[0].click;",linkCreateAnAccount);
        sleep(3);
        WebElement firstname = driver.findElement(By.id("firstname"));
        WebElement middleName = driver.findElement(By.id("middlename"));
        WebElement lastname = driver.findElement(By.id("lastname"));
        WebElement email = driver.findElement(By.id("email_address"));
        WebElement password = driver.findElement(By.id("password"));
        WebElement confirmPassword = driver.findElement(By.id("confirmation"));
        WebElement chkIsSubcribed = driver.findElement(By.id("is_subscribed"));
        WebElement btnRegister = driver.findElement(By.cssSelector("button[title='Register']"));

        inputDataUsingJS(firstname,"Hala");
        inputDataUsingJS(middleName,"Jell");
        inputDataUsingJS(lastname,"Min");
        inputDataUsingJS(email,generateRandomEmail(10));
        inputDataUsingJS(password,"Admin123");
        inputDataUsingJS(confirmPassword,"Admin123");
        js.executeScript("arguments[0].click;",chkIsSubcribed);
        js.executeScript("arguments[0].click;",btnRegister);
        assertEquals("Thank you for registering with Main Website Store.",js.executeScript("return document.documentElement.innerText;").toString());
        WebElement linkLogout = driver.findElement(By.cssSelector("#header-account a[title='Logout']"));
        js.executeScript("arguments[0].click;",linkLogout);*/
    }
    public String generateRandomEmail(int length){
        String chars = "0123456789QWERTYUIOLKJHGFDSAZXCVBNMasdfghjkloiuytrewqzxcvbnm";
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder(length);
        for(int i = 0; i< length; i++){
            int index = random.nextInt();
            stringBuilder.append(chars.charAt(index));
        }
        return stringBuilder + "@test.com";
    }
    public void inputDataUsingJS(WebElement element, String value){
        js.executeScript("arguments[0].setAttribute('value', arguments[1]);",element,value);
    }
}
