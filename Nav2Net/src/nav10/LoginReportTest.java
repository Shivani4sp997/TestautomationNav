package nav10;

	import org.testng.annotations.Test;
	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.support.ui.ExpectedConditions;
	import org.openqa.selenium.support.ui.WebDriverWait;
	import java.time.Duration;

	public class LoginReportTest {

	    @Test
	    public void testCase1() {
	        WebDriver driver = new ChromeDriver();
	        try {
	            driver.get("https://ntn10/login.aspx");
	            driver.manage().window().maximize();

	            // Handle browser warning if any
	            try {
	                driver.findElement(By.id("details-button")).click();
	                // driver.findElement(By.id("proceed-link")).click(); // if needed
	            } catch (Exception e) {
	                // If not present, skip
	            }

	            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

	            wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("ctl00$cpholder$txtUserName")));
	            driver.findElement(By.name("ctl00$cpholder$txtUserName")).sendKeys("shivani");
	            driver.findElement(By.id("ctl00_cpholder_txtPassword")).sendKeys("shivani");
	            driver.findElement(By.id("ctl00_cpholder_bLogin")).click();

	            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("ctl00_cpholder_bLogin")));
	            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("navAcctBtn")));
	            driver.findElement(By.id("navAcctBtn")).click();

	            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_ctl07_NavButtonsXS_accountmenu1_LogoutPanel")));
	            driver.findElement(By.id("ctl00_ctl07_NavButtonsXS_accountmenu1_LogoutPanel")).click();

	            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("ctl00_ctl07_NavButtonsXS_accountmenu1_LogoutPanel")));
	            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_NavBar1_NavButtonsXS_navLoginBtn")));
	            driver.findElement(By.id("ctl00_NavBar1_NavButtonsXS_navLoginBtn")).click();

	            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtUserName")));

	        } finally {
	            driver.quit(); // Ensure browser closes
	        }
	    }

	    @Test
	    public void testCase2() {
	        WebDriver driver = new ChromeDriver();
	        try {
	            driver.get("https://ntn10/login.aspx");
	            driver.manage().window().maximize();

	            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtUserName")));

	            driver.findElement(By.id("txtUserName")).sendKeys("shivani");
	            driver.findElement(By.id("ctl00_cpholder_txtPassword")).sendKeys("test");
	            driver.findElement(By.id("ctl00_cpholder_bLogin")).click();

	            // Add assertion or wait here for failed login indicator (optional)

	        } finally {
	            driver.quit(); // Ensure browser closes
	        }
	    }
	}


