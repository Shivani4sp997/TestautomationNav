package nav10;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Addtocartsuite {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://ntn10/login.aspx");
        wait = new WebDriverWait(driver, Duration.ofSeconds(50));
    }

    @Test
    public void placeOrderTest() {
        // Login
        driver.findElement(By.id("txtUserName")).sendKeys("shivani");
        driver.findElement(By.id("ctl00_cpholder_txtPassword")).sendKeys("shivani");
        driver.findElement(By.id("ctl00_cpholder_bLogin")).click();

        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("ctl00_cpholder_bLogin")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("navAcctBtn")));

        // Search item
        driver.findElement(By.id("ctl00_ctl07_NavButtonsXS_SearchBar1_txtkeyword")).sendKeys("60077");
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
        shippingDropdown.selectByVisibleText("Canada Post International Shipping - $46.31");

        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("ctl00_GlobalIndicator_LoadingIndicatorTarget")));

        WebElement checkbox = driver.findElement(By.id("ctl00_cpholder_chkconfirmation"));
        if (!checkbox.isSelected()) checkbox.click();

        driver.findElement(By.id("ctl00_cpholder_bSubmit")).click();

        // Order confirmation
        WebElement orderNoElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_cpholder_thankyouctl1_lOrderNo")));
        String orderNumber = orderNoElement.getText();
        System.out.println("Order placed successfully. Order Number: " + orderNumber);

        // Order history
        driver.findElement(By.id("navAcctBtn")).click();
        shortWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_ctl07_NavButtonsXS_accountmenu1_histviewlink")));
        driver.findElement(By.id("ctl00_ctl07_NavButtonsXS_accountmenu1_histviewlink")).click();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
