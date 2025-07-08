package nav10;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

public class ContactCreate  {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @DataProvider(name = "signupData")
    public Object[][] signupData() {
        return new Object[][] {
            {"Tst12345", "", "User", "TstUser15145@yopmail.com", "TestUser150"},
            {"TestUserMaxLength12345678", "", "LastName", "maxuse@yopmail.com", "MaTest"},
            {"Short", "", "User", "shortyuse1r@yopmail.com", "Shor001"}
        };
    }

    @Test(dataProvider = "signupData")
    public void testSignup(String firstName, String middleName, String lastName, String email, String userId) {
        driver.get("https://ntn10/login.aspx");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_cpholder_shopperctl_pnlNewAcct")));

        driver.findElement(By.id("ctl00_cpholder_shopperctl_txtFName")).clear();
        driver.findElement(By.id("ctl00_cpholder_shopperctl_txtFName")).sendKeys(firstName);

        driver.findElement(By.id("ctl00_cpholder_shopperctl_txtMname")).clear();
        driver.findElement(By.id("ctl00_cpholder_shopperctl_txtMname")).sendKeys(middleName);

        driver.findElement(By.id("ctl00_cpholder_shopperctl_txtLName")).clear();
        driver.findElement(By.id("ctl00_cpholder_shopperctl_txtLName")).sendKeys(lastName);

        driver.findElement(By.id("ctl00_cpholder_shopperctl_txtEmail")).clear();
        driver.findElement(By.id("ctl00_cpholder_shopperctl_txtEmail")).sendKeys(email);

        driver.findElement(By.id("ctl00_cpholder_shopperctl_txtUID")).clear();
        driver.findElement(By.id("ctl00_cpholder_shopperctl_txtUID")).sendKeys(userId);

        driver.findElement(By.id("ctl00_cpholder_shopperctl_tcacceptance")).click();
        driver.findElement(By.id("ctl00_cpholder_shopperctl_submit")).click();

        WebElement statusElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_cpholder_shopperctl_lUID")));

        Assert.assertTrue(statusElement.isDisplayed(), "Signup failed: UID label is not displayed.");
        System.out.println("Signup test passed for user: " + firstName + " " + lastName);
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
