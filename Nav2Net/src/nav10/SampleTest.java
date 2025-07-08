package nav10;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SampleTest 
{
	
	public static void main(String []args)
	{
		WebDriver driver = new ChromeDriver();
        driver.get("http://www.selenium.dev/");
        driver.quit();

	}
}
