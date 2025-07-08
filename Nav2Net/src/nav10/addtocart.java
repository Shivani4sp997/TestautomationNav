package nav10;

import java.time.Duration;

//import javax.swing.text.Document;

import org.openqa.selenium.By;
//import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
public class addtocart {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		WebDriver driver = new ChromeDriver();
		//WebDriver driver = new FirefoxDriver();
        driver.get("https://ntn10/login.aspx");
       // driver.get("https://ntn10ex.dvp.net/");
        driver.getTitle();
        System.out.println("Website navigation Pass");
        
        //Maximize window 
        driver.manage().window().maximize();
        
        //Find Tabs and send data
        driver.findElement(By.id("txtUserName")).sendKeys("shivani");
        driver.findElement(By.id("ctl00_cpholder_txtPassword")).sendKeys("shivani");
        
        //Find Button and click 
        driver.findElement(By.id("ctl00_cpholder_bLogin")).click(); 
        
        //Wait till loggedIn 
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50, 1));
        System.out.println("User logged in successfully");
        
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("ctl00_cpholder_bLogin")));
        
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("navAcctBtn")));
		
		 //Find Tabs and send data
        driver.findElement(By.id("ctl00_ctl07_NavButtonsXS_SearchBar1_txtkeyword")).click();
        driver.findElement(By.id("ctl00_ctl07_NavButtonsXS_SearchBar1_txtkeyword")).sendKeys("60077");
        
        
        //Find Button and click 
        driver.findElement(By.id("ctl00_ctl07_NavButtonsXS_SearchBar1_bGo")).click(); 
        System.out.println("Item 60077 navigation successful");
     
        //Wait till loggedIn 
        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(30, 1));
        
       // wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("ctl00_cpholder_itemlist_ChildItems_rptchilditems_ctl01_HyperLink1")));
        
        wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_cpholder_itemlist_ChildItems_rptchilditems_ctl01_HyperLink1")));
      //Find Button and click 
        driver.findElement(By.id("ctl00_cpholder_itemlist_ChildItems_rptchilditems_ctl01_HyperLink1")).click(); 

        
        wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_cpholder_ctl00_reviewScore1_starOut")));
      //Find Button and click 
        driver.findElement(By.id("ctl00_cpholder_ctl00_ibAddToCart_ibAddToCart")).click(); 
        
        wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_cpholder_ctl00_ibAddToCart_pnlSuccessWrap")));
        System.out.println("Item 60077 added to cart successful");
        
        //Find Button and click 
          driver.findElement(By.id("ctl00_NavBar1_NavButtonsXS_TopCartPreview_upCart")).click(); 
          
          wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_NavBar1_NavButtonsXS_TopCartSummary_lnkgift")));
          //Find Button and click 
            driver.findElement(By.id("ctl00_NavBar1_NavButtonsXS_TopCartSummary_lnkgift")).click(); 
            System.out.println("Navigation to item summary/ shopping cart successful");
            
            //Find Button and click 
            driver.findElement(By.id("ctl00_cpholder_CartInfoCtl_butCheckout")).click(); 
            
            wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_cpholder_cartinfodisplayctl_cartsubtotal")));
            //Find Button and click 
              driver.findElement(By.id("ctl00_cpholder_cartinfodisplayctl_cartsubtotal")).click(); 
              System.out.println("Navigation to item Checkout page successful");
              
     
		 // Locate the dropdown element by its ID, name, or other locator strategy
        WebElement dropdownElement = driver.findElement(By.id("ctl00_cpholder_dpduse")); // Adjust as necessary

        // Create a Select object to interact with the dropdown
        Select dropdown = new Select(dropdownElement);

        // Select the item "abc" from the dropdown
        dropdown.selectByVisibleText("Use Billing Address");

        // Optionally, verify that the selection was successful
        String selectedOption = dropdown.getFirstSelectedOption().getText();
        System.out.println("Selected option: " + selectedOption);
        
     // Wait for the second dropdown (123) to be populated
        WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait2.until(ExpectedConditions.elementToBeClickable(By.id("ctl00_cpholder_dLShipment"))); // Adjust as necessary

        // Locate the second dropdown element (123)
        WebElement dropdownElement123 = driver.findElement(By.id("ctl00_cpholder_dLShipment")); // Adjust as necessary

        // Create a Select object to interact with the second dropdown
        Select dropdown123 = new Select(dropdownElement123);

        // Select the item "456" from the second dropdown
        dropdown123.selectByVisibleText("Canada Post International Shipping - $46.31");
        System.out.println("Selected option from Shipping Method:Canada Post International Shipping - $46.31");
        
        // Wait for any loading indicators to disappear
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("ctl00_GlobalIndicator_LoadingIndicatorTarget")));

        // Locate the checkbox by its ID
        WebElement checkbox = driver.findElement(By.id("ctl00_cpholder_chkconfirmation"));

        // Click the checkbox
        checkbox.click();
        
        //Find Button and click 
        driver.findElement(By.id("ctl00_cpholder_bSubmit")).click(); 
        
        System.out.println("Order placed successfully");
        
       // wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_cpholder_thankyouctl1_lOrderNo")));
        
        WebElement orderNoElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_cpholder_thankyouctl1_lOrderNo")));
		String orderNumber = orderNoElement.getText();
        System.out.println("Order Number: " + orderNumber);
    
      //Find Button and click 
        driver.findElement(By.id("navAcctBtn")).click();
        
        wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_ctl07_NavButtonsXS_accountmenu1_histviewlink")));
        
        //Find Button and click 
          driver.findElement(By.id("ctl00_ctl07_NavButtonsXS_accountmenu1_histviewlink")).click();
    
      
        
	}

}
