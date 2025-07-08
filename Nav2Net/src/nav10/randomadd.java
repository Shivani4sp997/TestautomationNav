package nav10;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

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

public class randomadd {

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
        System.out.println("✅ Browser launched and navigated to site.");
    }

    private static class CanadianAddress {
        String company;
        String firstName;
        String lastName;
        String address;
        String city;
        String province;
        String postalCode;
        String phone;
        String email;

        CanadianAddress(String company, String firstName, String lastName,
                        String address, String city, String province, String postalCode,
                        String phone, String email) {
            this.company = company;
            this.firstName = firstName;
            this.lastName = lastName;
            this.address = address;
            this.city = city;
            this.province = province;
            this.postalCode = postalCode;
            this.phone = phone;
            this.email = email;
        }
    }

    private CanadianAddress getRandomCanadianAddress() {
        CanadianAddress[] addresses = new CanadianAddress[] {
            new CanadianAddress("Maple Corp", "Alice", "Smith", "123 Maple St", "Toronto", "Ontario", "M5V2T6", "4161234567", "alice.smith@example.com"),
            new CanadianAddress("Northern Co", "Bob", "Johnson", "456 Northern Rd", "Vancouver", "British Columbia", "V6B3K9", "6049876543", "bob.johnson@example.com"),
            new CanadianAddress("Eastside Ltd", "Carol", "Brown", "789 Eastside Ave", "Montreal", "Quebec", "H2X1S1", "5145551234", "carol.brown@example.com"),
            new CanadianAddress("Prairie Inc", "David", "Wilson", "321 Prairie Dr", "Calgary", "Alberta", "T2P1J9", "4032223333", "david.wilson@example.com"),
            new CanadianAddress("Lakeside LLC", "Eva", "Davis", "654 Lakeside Blvd", "Ottawa", "Ontario", "K1P5G7", "6134445555", "eva.davis@example.com")
        };
        return addresses[new Random().nextInt(addresses.length)];
    }

    @Test
    public void guestCheckoutFlow() throws InterruptedException {
        driver.findElement(By.id("ctl00_ctl07_NavButtonsXS_SearchBar1_txtkeyword"))
              .sendKeys(prop.getProperty("searchKeyword"));
        driver.findElement(By.id("ctl00_ctl07_NavButtonsXS_SearchBar1_bGo")).click();
        System.out.println("✅ Search initiated.");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_cpholder_itemlist_ChildItems_rptchilditems_ctl01_HyperLink1")));
        driver.findElement(By.id("ctl00_cpholder_itemlist_ChildItems_rptchilditems_ctl01_HyperLink1")).click();
        System.out.println("✅ Product selected.");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_cpholder_ctl00_ibAddToCart_ibAddToCart")));
        driver.findElement(By.id("ctl00_cpholder_ctl00_ibAddToCart_ibAddToCart")).click();
        System.out.println("✅ Product added to cart.");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_cpholder_ctl00_ibAddToCart_pnlSuccessWrap")));

        driver.findElement(By.id("ctl00_NavBar1_NavButtonsXS_TopCartPreview_upCart")).click();
        System.out.println("✅ Cart preview opened.");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_NavBar1_NavButtonsXS_TopCartSummary_lnkgift")));
        driver.findElement(By.id("ctl00_NavBar1_NavButtonsXS_TopCartSummary_lnkgift")).click();
        System.out.println("✅ Cart view clicked.");

        driver.findElement(By.id("ctl00_cpholder_CartInfoCtl_butCheckout")).click();
        System.out.println("✅ Proceeded to checkout.");

        By guestBtn = By.id("ctl00_cpholder_btnContinueAsGuest_btnButton");
        wait.until(ExpectedConditions.visibilityOfElementLocated(guestBtn));
        wait.until(ExpectedConditions.elementToBeClickable(guestBtn));

        WebElement continueAsGuestBtn = driver.findElement(guestBtn);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", continueAsGuestBtn);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", continueAsGuestBtn);
        System.out.println("✅ Clicked Continue as Guest.");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_cpholder_billingtitlelbl")));

        CanadianAddress addr = getRandomCanadianAddress();

        driver.findElement(By.id("ctl00_cpholder_BillAddressctl_bcompany_TextBox1")).sendKeys(addr.company); 
        driver.findElement(By.id("ctl00_cpholder_BillAddressctl_txtBFname_TextBox1")).sendKeys(addr.firstName);
        driver.findElement(By.id("ctl00_cpholder_BillAddressctl_txtBLname_TextBox1")).sendKeys(addr.lastName);
        driver.findElement(By.id("ctl00_cpholder_BillAddressctl_txtBaddress_TextBox1")).sendKeys(addr.address);
        driver.findElement(By.id("ctl00_cpholder_BillAddressctl_txtBCity_TextBox1")).sendKeys(addr.city);
        System.out.println("✅ Entered billing address.");

        Select dropdown1 = new Select(driver.findElement(By.id("ctl00_cpholder_BillAddressctl_cs_country")));
        dropdown1.selectByVisibleText("Canada");

        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("ctl00_GlobalIndicator_LoadingIndicatorTarget")));

        Select dropdown2 = new Select(driver.findElement(By.id("ctl00_cpholder_BillAddressctl_cs_state")));
        dropdown2.selectByVisibleText(addr.province);
        System.out.println("✅ Selected country and province.");

        driver.findElement(By.id("ctl00_cpholder_BillAddressctl_txtBpostal_TextBox1")).sendKeys(addr.postalCode);
        driver.findElement(By.id("ctl00_cpholder_BillAddressctl_txtBphone_TextBox1")).sendKeys(addr.phone);
        driver.findElement(By.id("ctl00_cpholder_BillAddressctl_txtBemail_TextBox1")).sendKeys(addr.email);
        System.out.println("✅ Entered postal code, phone, and email.");

        Select dropdown = new Select(driver.findElement(By.id("ctl00_cpholder_dpduse")));
        dropdown.selectByVisibleText("Use Billing Address");
        System.out.println("✅ Shipping address set to billing.");

        new WebDriverWait(driver, Duration.ofSeconds(20))
            .until(ExpectedConditions.elementToBeClickable(By.id("ctl00_cpholder_dLShipment")));

        new WebDriverWait(driver, Duration.ofSeconds(20))
            .until(ExpectedConditions.elementToBeClickable(By.id("ctl00_cpholder_Couponctl_couponno")));

        driver.findElement(By.id("ctl00_cpholder_Couponctl_couponno")).sendKeys("KATIEORDER%OFF");
        driver.findElement(By.id("ctl00_cpholder_Couponctl_couponno")).click();

        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("ctl00_GlobalIndicator_LoadingIndicatorTarget")));
        System.out.println("✅ Coupon applied.");

        Select shippingDropdown = new Select(driver.findElement(By.id("ctl00_cpholder_dLShipment")));
        shippingDropdown.selectByVisibleText(prop.getProperty("shippingOption"));
        System.out.println("✅ Shipping option selected.");

        driver.findElement(By.id("ctl00_cpholder_byaccount_0")).click();
        System.out.println("✅ Guest checkout confirmed.");
        
        driver.findElement(By.id("ctl00_cpholder_submissionForm1_dvpform1_ctl00_dvpcheck_0")).click();
        driver.findElement(By.id("ctl00_cpholder_submissionForm1_dvpform1_ctl01_TextBox")).sendKeys("test");

        WebElement checkbox = driver.findElement(By.id("ctl00_cpholder_chkconfirmation"));
        if (!checkbox.isSelected()) checkbox.click();
        System.out.println("✅ Terms and conditions checkbox ticked.");

        driver.findElement(By.id("ctl00_cpholder_bSubmit")).click();
        System.out.println("✅ Order submitted.");

        WebElement orderNoElement = wait.until(
            ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_cpholder_thankyouctl1_lOrderNo")));
        String orderNumber = orderNoElement.getText();
        System.out.println("Order placed successfully. Order Number: " + orderNumber);

        driver.findElement(By.id("navAcctBtn")).click();
        new WebDriverWait(driver, Duration.ofSeconds(30))
            .until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_ctl07_NavButtonsXS_accountmenu1_histviewlink")));
        driver.findElement(By.id("ctl00_ctl07_NavButtonsXS_accountmenu1_histviewlink")).click();
        System.out.println("✅ Navigated to order history form");
    }

    // @AfterMethod
    // public void tearDown() {
    //     if (driver != null) {
    //         driver.quit();
    //     }
    // }
}
