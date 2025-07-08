package nav10;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
//import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

//this class fetches data such as credentials, itemno, etc from config file for NTN10 
public class DynamicAddtoCartsuite  {

    WebDriver driver;
    WebDriverWait wait;
    Properties prop = new Properties();

    @BeforeMethod
    public void setup() throws IOException {
        // Load properties from config file
        FileInputStream fis = new FileInputStream("config.properties");
        prop.load(fis);

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(prop.getProperty("url"));
        wait = new WebDriverWait(driver, Duration.ofSeconds(50));
    }

    @Test
    public void placeOrderTest() {
        // Login
        driver.findElement(By.id("txtUserName")).sendKeys(prop.getProperty("username"));
        driver.findElement(By.id("ctl00_cpholder_txtPassword")).sendKeys(prop.getProperty("password"));
        driver.findElement(By.id("ctl00_cpholder_bLogin")).click();

        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("ctl00_cpholder_bLogin")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("navAcctBtn")));

        // Search item dynamically
        driver.findElement(By.id("ctl00_ctl07_NavButtonsXS_SearchBar1_txtkeyword")).sendKeys(prop.getProperty("searchKeyword"));
        driver.findElement(By.id("ctl00_ctl07_NavButtonsXS_SearchBar1_bGo")).click();

        WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        shortWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_cpholder_itemlist_ChildItems_rptchilditems_ctl01_HyperLink1")));
        driver.findElement(By.id("ctl00_cpholder_itemlist_ChildItems_rptchilditems_ctl01_HyperLink1")).click();

        shortWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_cpholder_ctl00_reviewScore1_starOut")));
        driver.findElement(By.id("ctl00_cpholder_ctl00_ibAddToCart_ibAddToCart")).click();

        shortWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_cpholder_ctl00_ibAddToCart_pnlSuccessWrap")));

        // Proceed to cart
        driver.findElement(By.id("ctl00_NavBar1_NavButtonsXS_TopCartPreview_upCart")).click();
        shortWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_NavBar1_NavButtonsXS_TopCartSummary_lnkgift")));
        driver.findElement(By.id("ctl00_NavBar1_NavButtonsXS_TopCartSummary_lnkgift")).click();

        driver.findElement(By.id("ctl00_cpholder_CartInfoCtl_butCheckout")).click();
        shortWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_cpholder_cartinfodisplayctl_cartsubtotal")));

        // Fill out checkout
        Select dropdown = new Select(driver.findElement(By.id("ctl00_cpholder_dpduse")));
        dropdown.selectByVisibleText("Use Billing Address");

        WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait2.until(ExpectedConditions.elementToBeClickable(By.id("ctl00_cpholder_dLShipment")));

        Select shippingDropdown = new Select(driver.findElement(By.id("ctl00_cpholder_dLShipment")));
        shippingDropdown.selectByVisibleText(prop.getProperty("shippingOption"));
        
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("ctl00_GlobalIndicator_LoadingIndicatorTarget")));
        
     // Select Credit Card as the payment method
        driver.findElement(By.id("ctl00_cpholder_byaccount_1")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("ctl00_GlobalIndicator_LoadingIndicatorTarget")));
        
        WebElement checkbox = driver.findElement(By.id("ctl00_cpholder_chkconfirmation"));
        if (!checkbox.isSelected()) checkbox.click();

           driver.findElement(By.id("ctl00_cpholder_bSubmit")).click();
           
           
          // Step 2: Wait for the iframe to be available, then switch to it
           By iframeLocator = By.cssSelector("iframe[src*='versapay']");
           wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(iframeLocator));
           wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("tl00_cpholder_BamboraCustomCheckoutControl_BamboraCreditCardContainer")));  

           By cardholderLocator = By.id("ctl00_cpholder_BamboraCustomCheckoutControl_tbToken");
           By cardNumberLocator = By.id("bambora-card-number");
           By expDateLocator    = By.id("bambora-cvv");
           By cvvLocator        = By.id("bambora-expiry");

           // Step 4: Wait for elements to be visible and enabled
           wait.until(ExpectedConditions.visibilityOfElementLocated(cardholderLocator));
           wait.until(ExpectedConditions.elementToBeClickable(cardNumberLocator));
           wait.until(ExpectedConditions.visibilityOfElementLocated(expDateLocator));
           wait.until(ExpectedConditions.elementToBeClickable(cvvLocator));

           // Step 5: Fill in the form
           driver.findElement(cardholderLocator).sendKeys("John Test");
           driver.findElement(cardNumberLocator).sendKeys("4005550000000001");
           driver.findElement(expDateLocator).sendKeys("12/30");
           driver.findElement(cvvLocator).sendKeys("123");
     // Step 1: Always switch back to top-level document first
    // driver.switchTo().defaultContent();

     // Step 2: Wait for the iframe to be available, then switch to it
    // By iframeLocator = By.cssSelector("iframe[src*='versapay']");
    // wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(iframeLocator));

     // Step 3: Define the correct field locators
//     By cardholderLocator = By.id("cardholderName");
//     By cardNumberLocator = By.id("accountNoDiv");
//     By expDateLocator    = By.id("expDateDiv");
//     By cvvLocator        = By.id("cvv");
//
//     // Step 4: Wait for elements to be visible and enabled
//     wait.until(ExpectedConditions.visibilityOfElementLocated(cardholderLocator));
//     wait.until(ExpectedConditions.elementToBeClickable(cardNumberLocator));
//     wait.until(ExpectedConditions.visibilityOfElementLocated(expDateLocator));
//     wait.until(ExpectedConditions.elementToBeClickable(cvvLocator));
//
//     // Step 5: Fill in the form
//     driver.findElement(cardholderLocator).sendKeys("John Test");
//     driver.findElement(cardNumberLocator).sendKeys("4111111111111111");
//     driver.findElement(expDateLocator).sendKeys("12/30");
//     driver.findElement(cvvLocator).sendKeys("123");
//
//     // Step 6: Return to main content
//     driver.switchTo().defaultContent();

     
     //WebElement checkbox = driver.findElement(By.id("ctl00_cpholder_chkconfirmation"));
     //if (!checkbox.isSelected()) checkbox.click();

        driver.findElement(By.id("ctl00$cpholder$BamboraCustomCheckoutControl$btnSubmitPayment")).click();

        // Order confirmation
        WebElement orderNoElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_cpholder_thankyouctl1_lOrderNo")));
        String orderNumber = orderNoElement.getText();
        System.out.println("Order placed successfully. Order Number: " + orderNumber);

        // Order history
        driver.findElement(By.id("navAcctBtn")).click();
        WebDriverWait shortWait2 = new WebDriverWait(driver, Duration.ofSeconds(30));
        shortWait2.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_ctl07_NavButtonsXS_accountmenu1_histviewlink")));
        driver.findElement(By.id("ctl00_ctl07_NavButtonsXS_accountmenu1_histviewlink")).click();
    }

//    @AfterMethod
//    public void tearDown() {
//        if (driver != null) {
//            driver.quit();
//        }
//    }
}
