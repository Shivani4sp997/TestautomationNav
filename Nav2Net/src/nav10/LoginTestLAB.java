package nav10;

	import java.time.Duration;

	import org.openqa.selenium.By;
	//import org.openqa.selenium.TimeoutException;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.support.ui.ExpectedConditions;
	import org.openqa.selenium.support.ui.WebDriverWait;
	//import org.testng.Assert;

	public class LoginTestLAB {
	    public static void main(String[] args)
	    {
	       WebDriver driver = new ChromeDriver();
	        //WebDriver driver = new FirefoxDriver();
	      //  driver.get("https://ntn10/login.aspx");
	        driver.get("https://ntn10/login.aspx");
	        driver.getTitle();
	        
	        //Maximize window 
	        driver.manage().window().maximize();
	        
	        //Find Tabs and send data
	        driver.findElement(By.id("txtUserName")).sendKeys("shivani");
	        driver.findElement(By.id("ctl00_cpholder_txtPassword")).sendKeys("shivani");
	        
	        //Find Button and click 
	        driver.findElement(By.id("ctl00_cpholder_bLogin")).click(); 
	     
	        //Wait till loggedIn 
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50, 1));
	        
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("ctl00_cpholder_bLogin")));
	        
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("navAcctBtn")));
	        
	     // Show result pass / fail 
	        String actualUrl="https://ntn10/loggedinwelcome.aspx";
	        String expectedUrl= driver.getCurrentUrl();
	        
	        if(actualUrl == expectedUrl)
	        {
	            System.out.println("The test case validating successful login with both the correct ID and password has passed");
	        }
	        else
	        {
	            System.out.println("The test case validating successful login with both the correct ID and password has failed");
	        }
	    
	       // WebElement navAcctBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("navAcctBtn")));
	        
	        driver.findElement(By.id("navAcctBtn")).click();      
	        
	        //Signout
	        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(5, 1));
	        
	        wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_ctl07_NavButtonsXS_accountmenu1_LogoutPanel")));
	        
	        driver.findElement(By.id("ctl00_ctl07_NavButtonsXS_accountmenu1_LogoutPanel")).click();
	          
	        //wait for signout
	        WebDriverWait wait3 = new WebDriverWait(driver, Duration.ofSeconds(20, 1));
	        
	        wait3.until(ExpectedConditions.invisibilityOfElementLocated(By.id("ctl00_ctl07_NavButtonsXS_accountmenu1_LogoutPanel")));
	        
	        wait3.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_NavBar1_NavButtonsXS_navLoginBtn")));
	    
	        driver.findElement(By.id("ctl00_NavBar1_NavButtonsXS_navLoginBtn")).click(); 
	        
	        //Wait for Sign in to navigate 
	        WebDriverWait wait4 = new WebDriverWait(driver, Duration.ofSeconds(5, 1));
	        
	        wait4.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtUserName")));
	        
	        // SighIn test case @2 - Wrong ID and password 
	        driver.findElement(By.id("txtUserName")).sendKeys("shivani");
	        driver.findElement(By.id("ctl00_cpholder_txtPassword")).sendKeys("test");
	        
	        //Find Button and click 
	        driver.findElement(By.id("ctl00_cpholder_bLogin")).click();      
	        String actualUrl1="https://ntn10/loggedinwelcome.aspx";
	        String expectedUrl1= driver.getCurrentUrl();
	        driver.getCurrentUrl();
	        
	        if(actualUrl1 == expectedUrl1)
	        {
	            System.out.println("The test case validating successful login with both the correct ID and password has passed");
	        }
	        else
	        {
	            System.out.println("The test case validating successful login with both the correct ID and password has failed");
	        }
	       }
	   }

