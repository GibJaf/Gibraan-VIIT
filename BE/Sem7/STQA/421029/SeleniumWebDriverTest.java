import org.testng.annotations.Test;
import org.automationtesting.excelreport.Xl;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumWebDriverTest {
	WebDriver driver;

	@Test
	public void Test1() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "G:\\chromedriver.exe");
		 driver = new ChromeDriver();
	       String expectedTitle = "Amazon Sign In";
	        String actualTitle = "";
	    	
	    		driver.manage().window().maximize();
	    		driver.get("https://www.amazon.com/ap/signin?_encoding=UTF8&ignoreAuthState=1&openid.assoc_handle=usflex&openid.claimed_id=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.identity=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.mode=checkid_setup&openid.ns=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0&openid.ns.pape=http%3A%2F%2Fspecs.openid.net%2Fextensions%2Fpape%2F1.0&openid.pape.max_auth_age=0&openid.return_to=https%3A%2F%2Fwww.amazon.com%2F%3Fref_%3Dnav_signin&switch_account=");	    	
	    	
	        if (actualTitle.contentEquals(expectedTitle)){
	            System.out.println("Test Passed!");
	        } else {
	            System.out.println("Test Failed");
	        }
	}

	@Test
	public void Test2() throws InterruptedException 
	{
		String title = driver.getTitle();
		System.out.print("Current page title is : " + title);
		WebElement user = driver.findElement(By.name("email"));
		user.sendKeys("test");
		WebElement pwd = driver.findElement(By.name("password"));
		pwd.sendKeys("test");
		WebElement signin = driver.findElement(By.id("signInSubmit"));
		signin.click();
	}


	@Test
	public void Test3() throws InterruptedException {
	String title = driver.getTitle();
		System.out.print("Current page title is : " + title);

		WebElement signin = driver.findElement(By.id("createAccountSubmit"));
		signin.click();
	}
	@Test
	public void Test4() throws InterruptedException {
		String title = driver.getTitle();
		System.out.print("Current page title is : " + title);

		WebElement signin = driver.findElement(By.linkText("Conditions of Use"));
		signin.click();
		System.out.print("\n'SUCCESSFUL EXECUTION!!!");
	}
	@Test
	public void Test5() throws InterruptedException {
		String title = driver.getTitle();
		System.out.print("Current page title is : " + title);

		WebElement pwd = driver.findElement(By.name("field-keywords"));
		pwd.sendKeys("Toys");
		WebElement signin = driver.findElement(By.className("nav-input"));
		signin.click();
		System.out.print("\n'SUCCESSFUL EXECUTION!!!");
	}
	
	@Test
	public void Test6() throws InterruptedException {
		String title = driver.getTitle();
		System.out.print("Current page title is : " + title);

		WebElement signin = driver.findElement(By.id("nav-cart"));
		signin.click();
		System.out.print("\n'SUCCESSFUL EXECUTION!!!");
	}
	
	@Test
	public void Test7() throws InterruptedException {
		String title = driver.getTitle();
		System.out.print("Current page title is : " + title);

		WebElement signin = driver.findElement(By.className("nav-line-2"));
		signin.click();
		System.out.print("\n'SUCCESSFUL EXECUTION!!!");
	}
	
	@Test
	public void Test8()throws InterruptedException {
		String title = driver.getTitle();
		System.out.print("Current page title is : " + title);

		WebElement signin = driver.findElement(By.id("nav-shop"));
		signin.click();
	
		System.out.print("\n'SUCCESSFUL EXECUTION!!!");
	}
	
	@Test
	public void Test9()throws InterruptedException {
		String title = driver.getTitle();
		System.out.print("Current page title is : " + title);

		WebElement signin = driver.findElement(By.id("nav-logo"));
		signin.click();
	
		System.out.print("\n'SUCCESSFUL EXECUTION!!!");
	}
	@Test
	public void Test12() throws InterruptedException {
		String title = driver.getTitle();
		System.out.print("Current page title is : " + title);

		WebElement pwd = driver.findElement(By.name("field-keywords"));
		pwd.sendKeys("Arduino");
		WebElement signin = driver.findElement(By.className("nav-input"));
		signin.click();
		System.out.print("\n'SUCCESSFUL EXECUTION!!!");
	}
	
	@Test
	public void Test10()throws InterruptedException {
		String title = driver.getTitle();
		System.out.print("Current page title is : " + title);

		WebElement signin = driver.findElement(By.linkText("Privacy Notice"));
		signin.click();
	
		System.out.print("\n'SUCCESSFUL EXECUTION!!!");
	}
	
	@Test
	public void Test11()throws Exception {
		String title = driver.getTitle();
		System.out.print("Current page title is : " + title);

		WebElement signin = driver.findElement(By.className("a-button-input"));
		signin.click();
	
		System.out.print("\n'SUCCESSFUL EXECUTION!!!");

        Xl.generateReport("excel-report.xlsx");
        
	}
	

	


}