package nav10;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
//import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Guestcheckout {

    WebDriver driver;
    WebDriverWait wait;
    Properties prop = new Properties();

    @BeforeMethod
    public void setup() throws IOException {
        FileInputStream fis = new FileInputStream("config.properties");
        prop.load(fis);

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(prop.getProperty("url"));
        wait = new WebDriverWait(driver, Duration.ofSeconds(50));
    }

    @Test
    public void guestCheckoutFlow() throws InterruptedException {
        // Search for product
        driver.findElement(By.id("ctl00_ctl07_NavButtonsXS_SearchBar1_txtkeyword"))
              .sendKeys(prop.getProperty("searchKeyword"));
        driver.findElement(By.id("ctl00_ctl07_NavButtonsXS_SearchBar1_bGo")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_cpholder_itemlist_ChildItems_rptchilditems_ctl01_HyperLink1")));
        driver.findElement(By.id("ctl00_cpholder_itemlist_ChildItems_rptchilditems_ctl01_HyperLink1")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_cpholder_ctl00_ibAddToCart_ibAddToCart")));
        driver.findElement(By.id("ctl00_cpholder_ctl00_ibAddToCart_ibAddToCart")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_cpholder_ctl00_ibAddToCart_pnlSuccessWrap")));

        // Proceed to cart
        driver.findElement(By.id("ctl00_NavBar1_NavButtonsXS_TopCartPreview_upCart")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_NavBar1_NavButtonsXS_TopCartSummary_lnkgift")));
        driver.findElement(By.id("ctl00_NavBar1_NavButtonsXS_TopCartSummary_lnkgift")).click();

        driver.findElement(By.id("ctl00_cpholder_CartInfoCtl_butCheckout")).click();

        // 💡 Wait for and Click "Continue as Guest" button safely
        By guestBtn = By.id("ctl00_cpholder_btnContinueAsGuest_btnButton");
        wait.until(ExpectedConditions.visibilityOfElementLocated(guestBtn));
        wait.until(ExpectedConditions.elementToBeClickable(guestBtn));

        WebElement continueAsGuestBtn = driver.findElement(guestBtn);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", continueAsGuestBtn);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", continueAsGuestBtn);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_cpholder_billingtitlelbl")));
        
        //Fill billing info
        driver.findElement(By.id("ctl00_cpholder_BillAddressctl_bcompany_TextBox1")).sendKeys("Test company1"); 
        driver.findElement(By.id("ctl00_cpholder_BillAddressctl_txtBFname_TextBox1")).sendKeys("Guest");
        driver.findElement(By.id("ctl00_cpholder_BillAddressctl_txtBLname_TextBox1")).sendKeys("User");
        driver.findElement(By.id("ctl00_cpholder_BillAddressctl_txtBaddress_TextBox1")).sendKeys("123 Guest Street");
        driver.findElement(By.id("ctl00_cpholder_BillAddressctl_txtBCity_TextBox1")).sendKeys("Toronto");
        Select dropdown1 = new Select(driver.findElement(By.id("ctl00_cpholder_BillAddressctl_cs_country")));
        dropdown1.selectByVisibleText("Canada");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("ctl00_GlobalIndicator_LoadingIndicatorTarget")));
        Select dropdown2 = new Select(driver.findElement(By.id("ctl00_cpholder_BillAddressctl_cs_state")));
        dropdown2.selectByVisibleText("Ontario");
        driver.findElement(By.id("ctl00_cpholder_BillAddressctl_txtBpostal_TextBox1")).sendKeys("M1M1M1");
        driver.findElement(By.id("ctl00_cpholder_BillAddressctl_txtBphone_TextBox1")).sendKeys("1234567890");
        driver.findElement(By.id("ctl00_cpholder_BillAddressctl_txtBemail_TextBox1")).sendKeys("guestuser@example.com");

        // Fill out checkout
        Select dropdown = new Select(driver.findElement(By.id("ctl00_cpholder_dpduse")));
        dropdown.selectByVisibleText("Use Billing Address");
        
        WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait2.until(ExpectedConditions.elementToBeClickable(By.id("ctl00_cpholder_dLShipment")));
        
        WebDriverWait wait3 = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait3.until(ExpectedConditions.elementToBeClickable(By.id("ctl00_cpholder_Couponctl_couponno")));
        driver.findElement(By.id("ctl00_cpholder_Couponctl_couponno")).sendKeys("KATIEORDER%OFF");
        driver.findElement(By.id("ctl00_cpholder_Couponctl_couponno")).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("ctl00_GlobalIndicator_LoadingIndicatorTarget")));

        
        Select shippingDropdown = new Select(driver.findElement(By.id("ctl00_cpholder_dLShipment")));
        shippingDropdown.selectByVisibleText(prop.getProperty("shippingOption"));
        
        driver.findElement(By.id("ctl00_cpholder_byaccount_0")).click();
      //  WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(20));
        
        WebElement checkbox = driver.findElement(By.id("ctl00_cpholder_chkconfirmation"));
        if (!checkbox.isSelected()) checkbox.click();

        driver.findElement(By.id("ctl00_cpholder_bSubmit")).click();

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

   // @AfterMethod
  //  public void tearDown() {
    //    if (driver != null) {
      //      driver.quit();
        
   // }
//}
}
