package pageobjects.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

import static org.testng.AssertJUnit.assertTrue;

public class SubmitOrderTest {
    public String driverPath = "/Users/dungdao/Documents/Setup/chromedriver/chromedriver";
    public WebDriver driver;
    @BeforeClass
    public void startBrowser() {
        System.setProperty("webdriver.chrome.driver", driverPath);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }
    @Test
    public void loginThenAddToCart(){
        String productName = "ZARA COAT 3";
        LandingPage landingPage = new LandingPage(driver);
        landingPage.navigateURL("https://rahulshettyacademy.com/client");

        ProductCatalogue productCatalogue = landingPage.loginAppication("anshika@gmail.com","Iamking@000");
        List<WebElement> products = productCatalogue.getProductsList();
        productCatalogue.getProductByName(productName);
        productCatalogue.addProductToCart(productName);
        productCatalogue.goToCartPage();

        CartPage cartPage = productCatalogue.goToCartPage();
        assertTrue(cartPage.verifyProductDisplay(productName));

        CheckoutPage checkoutPage = cartPage.goToCheckout();
        checkoutPage.selectCountry("india");

        ComfirmationPage comfirmationPage = checkoutPage.submitOrder();
        System.out.println(comfirmationPage.getConfirmationMessage());
        assertTrue(comfirmationPage.getConfirmationMessage().equalsIgnoreCase("THANKYOU FOR THE ORDER."));
        /*
        List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
        boolean match = cartProducts.stream().anyMatch(cart -> cart.getText().equalsIgnoreCase(productName));
        assertTrue(match);

        driver.findElement(By.cssSelector(".totalRow button")).click();
        Actions a = new Actions(driver);
        a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")),"india").build().perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
        driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
        driver.findElement(By.cssSelector(".action_submit")).click();
        String message = driver.findElement(By.cssSelector(".hero-primary")).getText();
        assertTrue(message.equalsIgnoreCase("Thankyou for the order"));*/
    }
    @AfterClass
    public void endTest() {
        driver.quit();
    }
}
