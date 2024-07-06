package basic.examples;

import common.InitBrowser;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.testng.AssertJUnit.*;

public class PopupIframeWindow extends InitBrowser {
    WebDriverWait explicitWait;
    @BeforeClass
    public void start(){
        startBrowser("chrome");
        explicitWait = new WebDriverWait(driver,Duration.ofSeconds(10));
    }
    @Test
    public void TC01_FixedPopup() throws InterruptedException {
        navigateURL("https://ngoaingu24h.vn/");
        driver.findElement(By.cssSelector("button.login_")).click();
        // Checking popup is displayed
        WebElement popup = driver.findElement(By.cssSelector("div[id='modal-login-v1'][style]>div"));
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[id='modal-login-v1'][style]>div")));
        assertTrue(popup.isDisplayed());
        // Fill in form
        popup.findElement(By.cssSelector("input#account-input")).sendKeys("a");
        popup.findElement(By.cssSelector("input#password-input")).sendKeys("a");
        popup.findElement(By.cssSelector("button.btn-login-v1")).click();
        // Check error message
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[id='modal-login-v1'][style]>div div.row.error-login-panel")));
        assertEquals("Mật khẩu sai!",popup.findElement(By.cssSelector("div.row.error-login-panel")).getText());
        //close popup
        popup.findElement(By.cssSelector("button.close")).click();
        sleep(3);
        assertFalse(popup.isDisplayed());
    }
    @Test
    public void TC02_FixedPopup(){
        navigateURL("https://skills.kynaenglish.vn/dang-nhap");
        WebElement popup = driver.findElement(By.cssSelector("#k-popup-account-login-mb .modal-content"));
        assertTrue(popup.isDisplayed());
        //Fill in form
        popup.findElement(By.id("user-login")).sendKeys("automation");
        popup.findElement(By.id("user-password")).sendKeys("automation");
        popup.findElement(By.id("btn-submit-login")).click();
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password-form-login-message")));
        assertEquals("Sai tên đăng nhập hoặc mật khẩu",popup.findElement(By.id("password-form-login-message")).getText());
    }
    @Test
    public void TC03_FixedPopupNotInDom(){
        navigateURL("https://tiki.vn/");
        driver.findElement(By.cssSelector("#VIP_BUNDLE img[alt='close-icon']")).click();
        driver.findElement(By.cssSelector("div[data-view-id='header_header_account_container']")).click();
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.ReactModal__Content")));
        WebElement popup1 = driver.findElement(By.cssSelector("div.ReactModal__Content"));
        assertTrue(popup1.isDisplayed());
        popup1.findElement(By.cssSelector("p.login-with-email")).click();
        popup1.findElement(By.cssSelector("form button")).click();
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.ReactModal__Content span.error-mess")));
        List<WebElement> errors = popup1.findElements(By.cssSelector("span.error-mess"));
        errors.forEach(s -> System.out.println(s.getText()));
        assertEquals("Email không được để trống",errors.get(0).getText());
        assertEquals("Mật khẩu không được để trống",errors.get(1).getText());
        popup1.findElement(By.cssSelector(".btn-close")).click();
        //assertFalse(popup1.isDisplayed()); => Just use this way when dom in source code
        assertEquals(0,driver.findElements(By.cssSelector("div.ReactModal__Content")).size());
    }
    @Test
    public void TC04_FixedPopupNotInDom() throws InterruptedException {
        navigateURL("https://www.facebook.com/");
        driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
        WebElement signUpPopup = driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/parent::div"));
        assertTrue(signUpPopup.isDisplayed());
        signUpPopup.findElement(By.tagName("img")).click();
        sleep(3);
        assertEquals(0,driver.findElements(By.xpath("//div[text()='Sign Up']/parent::div/parent::div")).size());
    }
    @Test
    public void TC05_1_RandomPopupInDom(){
        navigateURL("https://vnk.edu.vn/");
        if(driver.findElement(By.cssSelector("div.pum div.pum-container")).isDisplayed()){
            explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.pum div.pum-container button.pum-close"))).click();
            System.out.println("Popup is displayed");
        }else{
            System.out.println("Popup is not displayed");
        }
        driver.findElement(By.cssSelector("button.btn-danger")).click();
        sleep(10);
        assertEquals("https://vnk.edu.vn/lich-khai-giang/",driver.getCurrentUrl());
    }
    @Test
    public void TC05_2_RandomPopupInDom(){
        navigateURL("https://www.kmplayer.com/home");
        if(driver.findElement(By.cssSelector("#layer2 div.pop-container")).isDisplayed()) {
            explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#layer2 div.pop-container div.check_wrap_box div.close"))).click();
            System.out.println("Popup is displayed");
        }else{
            System.out.println("Popup is not displayed");
        }
    }
    @Test
    public void TC06_RandomPopupNotInDom(){
        navigateURL("https://www.javacodegeeks.com/");
        //sleep(10);
        By popupBy = By.cssSelector("div.lepopup-popup-container>div:not([style^='display:none'])");
        String search = "Agile Testing Explained";
        if(!driver.findElements(popupBy).isEmpty() && driver.findElements(popupBy).get(0).isDisplayed()){
            System.out.println("Popup is displayed");
            driver.findElement(By.cssSelector("div.lepopup-popup-container>div:not([style^='display:none']) div.lepopup-element-html-content a")).click();
        }else{
            System.out.println("Popup is not displayed");
        }
        driver.findElement(By.cssSelector("input#search-input")).sendKeys(search);
        driver.findElement(By.cssSelector("button#search-submit span")).click();
        sleep(5);
        List<WebElement> titles = driver.findElements(By.cssSelector(".post-details h2.post-title"));
        assertTrue(titles.get(0).getText().contains(search));
    }
    @Test
    public void TC07_RandomPopupNotInDom() {
        navigateURL("https://dehieu.vn/");
        sleep(5);
        By popup = By.cssSelector("div.modal-dialog div.modal-content");
        if(driver.findElement(popup).isDisplayed()){
            driver.findElement(By.cssSelector("div.modal-dialog div.modal-content button.close")).click();
        }else{
            System.out.println("Popup is not displayed");
        }
    }
    @Test
    public void TC08_ShadowDOM(){
        navigateURL("https://automationfc.github.io/shadow-dom");
        //Find base on html structure
        WebElement shadowHost = driver.findElement(By.cssSelector("div#shadow_host"));
        SearchContext shadowContext = shadowHost.getShadowRoot();

        String someText = shadowContext.findElement(By.cssSelector("span#shadow_content>span")).getText();
        System.out.println(someText);
        //shadow root level 2
        WebElement nestedShadowHost = shadowContext.findElement(By.cssSelector("div#nested_shadow_host"));
        SearchContext nestedShadowRootContext = nestedShadowHost.getShadowRoot();
        System.out.println(nestedShadowRootContext.findElement(By.cssSelector("div#nested_shadow_content>div")).getText());

        WebElement checkbox = shadowContext.findElement(By.cssSelector("input[type='checkbox']"));
        assertFalse(checkbox.isSelected());
        List<WebElement> inputs = shadowContext.findElements(By.cssSelector("input"));
        System.out.println(inputs.size());
    }
    @Test
    public void TC09_ShadowRootHandle(){
        navigateURL("https://shopee.vn/");
        sleep(3);
        //Login to shopee
        driver.findElement(By.cssSelector("input[name='loginKey']")).sendKeys("0988013304");
        driver.findElement(By.cssSelector("input[name='password']")).sendKeys("17121992");
        driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();
        sleep(5);

        WebElement shadowRootElement = driver.findElement(By.cssSelector("div.home-popup #shadow-root"));
        SearchContext shadowContext = shadowRootElement.getShadowRoot();

        WebElement closeButton = shadowContext.findElement(By.cssSelector("div.shopee-popup__close-btn"));
        assertTrue(closeButton.isDisplayed());
        closeButton.click();
    }
    @Test
    public void TC10_Iframe(){
        navigateURL("https://toidicodedao.com/");
        WebElement inputSearch = driver.findElement(By.cssSelector("#search-5 input[type='search']"));
        assertTrue(inputSearch.isDisplayed());
        //Verify Facebook Iframe is displayed
        WebElement fbIframe = driver.findElement(By.cssSelector("div.fb-page iframe"));
        assertTrue(fbIframe.isDisplayed());
        //Verify follower number
        driver.switchTo().frame(fbIframe);
        assertEquals("406,271 followers",driver.findElement(By.cssSelector("div.lfloat>div~div")).getText());
        //Get out of iframe
        driver.switchTo().defaultContent();
        sleep(2);
        inputSearch.sendKeys("java selenium");
        // submit the form if needed (enter after typing)
        inputSearch.submit();
        sleep(3);
        assertEquals("Search Results for: java selenium",driver.findElement(By.cssSelector("h1.page-title")).getText());
    }
    @Test
    public void TC11_Iframe(){
        navigateURL("https://www.formsite.com/templates/education/campus-safety-survey/");
        driver.findElement(By.cssSelector("#imageTemplateContainer img")).click();
        sleep(5);
        WebElement iframe = driver.findElement(By.cssSelector("#formTemplateContainer iframe"));
        assertTrue(iframe.isDisplayed());
        driver.switchTo().frame(iframe);
        new Select(driver.findElement(By.cssSelector("#RESULT_RadioButton-2"))).selectByVisibleText("Sophomore");
        new Select(driver.findElement(By.cssSelector("#RESULT_RadioButton-3"))).selectByVisibleText("North Dorm");
        driver.findElement(By.cssSelector("label[for='RESULT_RadioButton-4_1']")).click();
        driver.findElement(By.cssSelector("input.submit_button")).click();
        sleep(2);
        assertEquals("Please review the form and correct the highlighted items.",driver.findElement(By.cssSelector("div.invalid_message")).getText());

        driver.switchTo().defaultContent();
        driver.findElement(By.cssSelector("nav.header--desktop-floater a[title='Log in']")).click();
        sleep(3);
        driver.findElement(By.cssSelector("button#login")).click();
        assertEquals("Username and password are both required.",driver.findElement(By.cssSelector("#message-error")).getText());
    }
    @Test
    public void TC12_FrameBank(){
        navigateURL("https://netbanking.hdfcbank.com/netbanking/");
        WebElement loginFrame = driver.findElement(By.cssSelector("frame[name='login_page']"));
        assertTrue(loginFrame.isDisplayed());
        driver.switchTo().frame(loginFrame);
        assertTrue(driver.findElement(By.cssSelector("input[name='fldLoginUserId'")).isDisplayed());
        driver.findElement(By.cssSelector("input[name='fldLoginUserId'")).sendKeys("automation");
        driver.findElement(By.cssSelector("a.login-btn")).click();
        sleep(5);
        driver.switchTo().defaultContent();
        assertTrue(driver.findElement(By.cssSelector("input#keyboard")).isDisplayed());
    }
    @Test
    public void TC12_WindowTab(){
        navigateURL("https://automationfc.github.io/basic-form/index.html");
        WebElement googleLink = driver.findElement(By.xpath("//a[text()='GOOGLE']"));
        googleLink.click();
        switchToWindowByTitle("Google");
        driver.findElement(By.cssSelector("textarea[name='q']")).sendKeys("Selenium Automation");
        driver.close();
        // Back to parent window
        switchToWindowByTitle("Selenium WebDriver");
        driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
        switchToWindowByTitle("Facebook");
        assertEquals("Facebook – log in or sign up",driver.getTitle());
        driver.close();

        switchToWindowByTitle("Selenium WebDriver");
        driver.findElement(By.xpath("//a[text()='TIKI']")).click();
        switchToWindowByTitle("Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");
        sleep(5);
        if(driver.findElement(By.cssSelector("div#VIP_BUNDLE")).isDisplayed()){
            driver.findElement(By.cssSelector("div#VIP_BUNDLE img[alt='close-icon']")).click();
        }else{
            System.out.println("Popup is not displayed");
        }
        driver.close();
        switchToWindowByTitle("Selenium WebDriver");
        assertEquals("Selenium WebDriver",driver.getTitle());
    }
    public void switchToWindowByTitle(String expectedTitle){
        Set<String> windows = driver.getWindowHandles();
        for (String window : windows) {
            driver.switchTo().window(window);
            if (driver.getTitle().equals(expectedTitle)) {
                break;
            }
        }
    }
    @Test
    public void TC14_WindowTab(){
        navigateURL("http://live.techpanda.org/");
        driver.findElement(By.xpath("//a[text()='Mobile']")).click();
        driver.findElement(By.xpath("//h2[@class='product-name']/a[text()='Sony Xperia']//parent::h2//following-sibling::div[@class='actions']//a[@class='link-compare']")).click();
        System.out.println(driver.findElement(By.cssSelector("li.success-msg span")).getText());
        //addToCompare("Sony Xperia");
        sleep(5);
        driver.findElement(By.xpath("//h2[@class='product-name']/a[text()='Samsung Galaxy']//parent::h2//following-sibling::div[@class='actions']//a[@class='link-compare']")).click();
        System.out.println(driver.findElement(By.cssSelector("li.success-msg span")).getText());
        sleep(5);
        driver.findElement(By.xpath("//h2[@class='product-name']/a[text()='IPhone']//parent::h2//following-sibling::div[@class='actions']//a[@class='link-compare']")).click();
        System.out.println(driver.findElement(By.cssSelector("li.success-msg span")).getText());
        //addToCompare("Samsung Galaxy");
        driver.findElement(By.cssSelector("button[title='Compare']")).click();
        switchToWindowByTitle("Products Comparison List - Magento Commerce");
        assertEquals("Products Comparison List - Magento Commerce",driver.getTitle());
        driver.close();
        switchToWindowByTitle("Mobile");
        driver.findElement(By.linkText("Clear All")).click();
        explicitWait.until(ExpectedConditions.alertIsPresent()).accept();
        assertEquals("The comparison list was cleared.",driver.findElement(By.cssSelector("li.success-msg span")).getText());
    }
    @Test
    public void TC14_AddProductToCompare() {
        navigateURL("http://live.techpanda.org/");
        driver.findElement(By.xpath("//a[text()='Mobile']")).click();
        addToCompare("IPhone");
        addToCompare("Samsung Galaxy");
        addToCompare("Sony Xperia");

        driver.findElement(By.cssSelector("button[title='Compare']")).click();
        switchToWindowByTitle("Products Comparison List - Magento Commerce");
        assertEquals("Products Comparison List - Magento Commerce",driver.getTitle());
        driver.close();
        switchToWindowByTitle("Mobile");
        driver.findElement(By.linkText("Clear All")).click();
        explicitWait.until(ExpectedConditions.alertIsPresent()).accept();
        assertEquals("The comparison list was cleared.",driver.findElement(By.cssSelector("li.success-msg span")).getText());

    }
    public void addToCompare(String productName){
        driver.findElement(By.xpath("//a[@title='" + productName + "']//ancestor::div[@class='product-info']//a[@class='link-compare']")).click();
        sleep(3);
        System.out.println(driver.findElement(By.cssSelector("li.success-msg span")).getText());
        assertEquals("The product " + productName +" has been added to comparison list.",driver.findElement(By.cssSelector("li.success-msg span")).getText());

        List<WebElement> comparedProductList = driver.findElements(By.cssSelector("ol#compare-items p.product-name a"));
        WebElement selectedCompareProduct = comparedProductList.stream().filter(e -> e.getText().equals(productName.toUpperCase())).findFirst().orElse(null);
        assert selectedCompareProduct != null;
        assertTrue(selectedCompareProduct.isDisplayed());
        sleep(5);
    }
    @Test
    public void TC15_WindowTab(){
        navigateURL("https://dictionary.cambridge.org/");
        driver.findElement(By.xpath("//span[text()='Log in']")).click();
        switchToWindowByTitle("Login");
        driver.findElement(By.cssSelector("input[value='Log in']")).click();
        sleep(3);
        assertEquals("This field is required",driver.findElement(By.cssSelector("#gigya-login-form input[name='username']~span")).getText());
        assertEquals("This field is required",driver.findElement(By.cssSelector("#gigya-login-form input[name='password']~span")).getText());
        driver.close();
        switchToWindowByTitle("Cambridge Dictionary | English Dictionary, Translations & Thesaurus");
        driver.findElement(By.cssSelector("input[name='q']")).sendKeys("competitor");
        driver.findElement(By.cssSelector("input[name='q']")).submit();
        driver.findElements(By.cssSelector("span.headword span")).forEach(e -> assertEquals("competitor",e.getText()));
    }
    @Test
    public void TC16_WindowTab(){
        navigateURL("https://courses.dce.harvard.edu/");
        driver.findElement(By.cssSelector("a[data-action='login']")).click();
        sleep(10);
        System.out.println(driver.getWindowHandles().size());
        Iterator<String> listWindows = driver.getWindowHandles().iterator();
        String parentWindow = listWindows.next();
        String subWindow = listWindows.next();
        driver.switchTo().window(subWindow);
        System.out.println(driver.getTitle());
        //switchToWindowByTitle("Harvard Division of Continuing Education Login Portal");
        assertTrue(driver.findElement(By.cssSelector("#custom-prompt-logo~h1")).isDisplayed());
        driver.close();
        driver.switchTo().window(parentWindow);
        //switchToWindowByTitle("Harvard DCE Course Search");
        assertTrue(driver.findElement(By.cssSelector("div#sam-wait")).isDisplayed());
        driver.findElement(By.cssSelector("div#sam-wait button.fa")).click();
        sleep(2);
        driver.findElement(By.cssSelector("input#crit-keyword")).sendKeys("Data Science: An Artificial Ecosystem");
        new Select(driver.findElement(By.cssSelector("select#crit-srcdb"))).selectByVisibleText("Harvard Summer School 2024");
        new Select(driver.findElement(By.cssSelector("select#crit-summer_school"))).selectByVisibleText("Harvard College");
        new Select(driver.findElement(By.cssSelector("select#crit-session"))).selectByVisibleText("Any Part of Term");
        driver.findElement(By.cssSelector("button#search-button")).click();
        sleep(5);
        List<WebElement> result = driver.findElements(By.cssSelector("div.result span.result__title"));
        result.forEach(e -> assertEquals("Data Science: An Artificial Ecosystem",e.getText()));
    }

}
