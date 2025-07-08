package nav10;

	import java.time.Duration;

import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait; 


	public class Loginnav {


		public static void main(String[] args) {
			// TODO Auto-generated method stub
			
			//WebDriver driver = new ChromeDriver();
			 WebDriver driver = new ChromeDriver();
		        //WebDriver driver = new FirefoxDriver();
		        //WebDriver driver = new FirefoxDriver();
		        driver.get("http://ntn10bc24/BC/SignIn?ReturnUrl=%2FBC%2FDefault%3Ftenant%3Ddefaulthttps://ntn10/login.aspx");
		        driver.getTitle();
		        
		        //Maximize window 
		        driver.manage().window().maximize();
		        
		        //Find Tabs and send data
		        driver.findElement(By.id("UserName")).sendKeys("Shivani");
		        driver.findElement(By.id("Password")).sendKeys("HHwk#888wMcYz");
		        
		        //Find Button and click 
		        driver.findElement(By.id("submitButton")).click(); 
		        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30, 1));
		        
		        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("submitButton")));
		        
		        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("badge--fKCZNqO2j_duENJouAZk thm-color--style-subordinate_minflat_45--not_FCM thm-font-size-medium2 thm-segoeSemilight")));
			
		}

	}
