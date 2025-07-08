package nav10;


import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignuPNTN10 {

	public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        // WebDriver driver = new FirefoxDriver();
        driver.get("https://ntn10/login.aspx");
        driver.getTitle();

        // Maximize window
        driver.manage().window().maximize();

        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(6));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_cpholder_shopperctl_pnlNewAcct")));
     		
        // Find Tabs and send data
        driver.findElement(By.id("ctl00_cpholder_shopperctl_txtFName")).sendKeys("Tst12345");
        
        // Get the typed value
// 		String typedValue = ((WebElement) driver).getAttribute("value");
//        int maxlength = typedValue.length();
		//Condition to check firstname length 
       // if (maxlength == 30)                                                                                                                                                                                     
//        {
//            System.out.println("Max character functionality is working fine");
//        }
//        else
//        {
//            System.out.println("Max character functionality is not working fine");
//        }
// 
        
        driver.findElement(By.id("ctl00_cpholder_shopperctl_txtMname")).sendKeys(""); 
        driver.findElement(By.id("ctl00_cpholder_shopperctl_txtLName")).sendKeys("User");
        driver.findElement(By.id("ctl00_cpholder_shopperctl_txtEmail")).sendKeys("TstUser1514@yopmail.com");
        driver.findElement(By.id("ctl00_cpholder_shopperctl_txtUID")).sendKeys("TestUser11501");
        driver.findElement(By.id("ctl00_cpholder_shopperctl_tcacceptance")).click();
        driver.findElement(By.id("ctl00_cpholder_shopperctl_submit")).click();
     
        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement ctl00_cpholder_shopperctl_status = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_cpholder_shopperctl_lUID")));
        
        // Condition for pass or fail
        if (ctl00_cpholder_shopperctl_status.isDisplayed())
        {
            System.out.println("The test case validating for successful Signup has failed");
        } else
        {
            System.out.println("The test case validating for successful Signup has passed");
        }
    }
}