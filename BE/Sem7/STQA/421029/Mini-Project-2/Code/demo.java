package aas;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class demo {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "D:\\stqa\\exe\\chrome\\chromedriver.exe");
		WebDriver driver =new ChromeDriver();
		driver.get("https://www.facebook.com");
		driver.manage().window().maximize();
		
		
		driver.findElement(By.id("email")).sendKeys("vinay.bojja@viit.ac.in");
		driver.findElement(By.id("u_0_m")).sendKeys("vinay");

	
		
		driver.findElement(By.name("websubmit")).click();
		
		String at = driver.getTitle();
		String et = "gmail";
		//driver.close();
		
		if(at.equalsIgnoreCase(et))
				{
			System.out.println("Test Case Successfull;");
				}
		else
		{
			System.out.println("Test Case Unsuccessfull");
		}
	}

}
