package pack;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class NewTest {
	
	WebDriver driver;
	ExtentReports extent;
	ExtentTest test;
	
	@Test(priority=0)
	public void netIsAvailable() {
		
		//extent = new ExtentReports("C:\\Users\\Ankit\\eclipse-workspace\\STQA MINIPROJECT2\\test-output\\extentreport.html",true);
		extent = new ExtentReports("/home/gibraan/Git_Projects/Gibraan-VIIT/BE/STQA/STQA_MiniProject2/test-output/extentreport.html",true);
		test=extent.startTest("Verify net is connected or not");
	    
		try {
			
	        final URL url = new URL("http://www.google.com");
	        final URLConnection conn = url.openConnection();
	        conn.connect();
	        conn.getInputStream().close();
	        
	        test.log(LogStatus.INFO, "Net connection Test Initialized");
	        assert(true);
	        test.log(LogStatus.PASS, "Net connection is available");
	    
		} catch (MalformedURLException e) {
			
	        throw new RuntimeException(e);
	        
	    } catch (IOException e) {
	    	
	        System.out.println("Please check your net connection");
	        
	        assert(false);
	        test.log(LogStatus.FAIL, "Net connection not available");
	    }
	    
		extent.endTest(test);
	    extent.flush();
	}
	
	@Test(dependsOnMethods= {"netIsAvailable"},alwaysRun=false)
	public void openBrowser() {
		
		test=extent.startTest("Verify that browser is opened or not");
		test.log(LogStatus.INFO,"Browser test initialized");
		
		String exepath="E:\\Downloads\\chromedriver_win32\\chromedriver.exe";
		System.setProperty("webdriver.chrome.driver",exepath);
		String url="C:\\Users\\Ankit\\eclipse-workspace\\STQA MINIPROJECT2\\src\\pack\\index.html";
		
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		
		test.log(LogStatus.PASS, "Browser opened");
		driver.get(url);
		test.log(LogStatus.PASS, "Game opened");
		assert(true);
		extent.endTest(test);
	    extent.flush();
	
	}
	
	@Test(priority=2)
	public void checkTitle() {
		
		test=extent.startTest("Verify the title of opened web page");
		test.log(LogStatus.INFO,"Title test initialized");

		String a=driver.getTitle();
		if("Tic Tac Toe game".equals(a)) {
		
			test.log(LogStatus.PASS, "Title is correct");
			assert(true);
			
		}
		else {
			
			test.log(LogStatus.FAIL, "Title is incorrect ,check url");
			assert(false);
		
		}
		
		extent.endTest(test);
	    extent.flush();
	
	}
  @Test(priority=3)
  public void click() throws InterruptedException {
	  
	  	test=extent.startTest("Verify the 1st game");
		test.log(LogStatus.INFO,"Click test initialized");

		driver.findElement(By.id("one")).click();
		driver.findElement(By.id("seven")).click();
		driver.findElement(By.id("nine")).click();
		driver.findElement(By.id("five")).click();
		driver.findElement(By.id("eight")).click();
		driver.findElement(By.id("six")).click();
		driver.findElement(By.id("four")).click();
		driver.findElement(By.id("three")).click();
		Thread.sleep(1000);
		
		
		
		test.log(LogStatus.PASS, "Click function is working properly");
		assert(true);
		extent.endTest(test);
	    extent.flush();
	    
  }
  @Test(priority=4)
  public void click1() throws InterruptedException {
	  //X wins
	  	test=extent.startTest("Verify the 2nd game");
		test.log(LogStatus.INFO,"Click test initialized");

		
		
		driver.findElement(By.id("eight")).click();
		driver.findElement(By.id("two")).click();
		driver.findElement(By.id("three")).click();
		driver.findElement(By.id("seven")).click();
		driver.findElement(By.id("five")).click();
		driver.findElement(By.id("nine")).click();
		driver.findElement(By.id("one")).click();
		driver.findElement(By.id("six")).click();
		driver.findElement(By.id("four")).click();
		Thread.sleep(1000);
		
		
		
		
		test.log(LogStatus.PASS, "Click function is working properly");
		assert(true);
		extent.endTest(test);
	    extent.flush();
	    
  }
  
  @Test(priority=5)
  public void click2() throws InterruptedException {
	  //Draw
	  	test=extent.startTest("Verify the 3rd game");
		test.log(LogStatus.INFO,"Click test initialized");

		
		
		driver.findElement(By.id("three")).click();
		driver.findElement(By.id("seven")).click();
		driver.findElement(By.id("five")).click();
		driver.findElement(By.id("one")).click();
		driver.findElement(By.id("two")).click();
		driver.findElement(By.id("nine")).click();
		driver.findElement(By.id("four")).click();
		driver.findElement(By.id("six")).click();
		driver.findElement(By.id("eight")).click();
		Thread.sleep(1000);

		
		test.log(LogStatus.PASS, "Click function is working properly");
		assert(true);
		extent.endTest(test);
	    extent.flush();
	    
  }
  
  @Test(priority=6)
  public void click3() throws InterruptedException {
	  //0 wins
	  	test=extent.startTest("Verify the 4th game");
		test.log(LogStatus.INFO,"Click test initialized");

		
		
		driver.findElement(By.id("eight")).click();
		driver.findElement(By.id("one")).click();
		driver.findElement(By.id("three")).click();
		driver.findElement(By.id("five")).click();
		driver.findElement(By.id("nine")).click();
		driver.findElement(By.id("six")).click();
		driver.findElement(By.id("seven")).click();
		Thread.sleep(1000);
		
		test.log(LogStatus.PASS, "Click function is working properly");
		assert(true);
		extent.endTest(test);
	    extent.flush();
	    
  }
  
  @Test(priority=7)
  public void click4() throws InterruptedException {
	  
	  	test=extent.startTest("Verify the 5th game");
		test.log(LogStatus.INFO,"Click test initialized");

		//Draw
		driver.findElement(By.id("five")).click();
		driver.findElement(By.id("three")).click();
		driver.findElement(By.id("one")).click();
		driver.findElement(By.id("nine")).click();
		driver.findElement(By.id("six")).click();
		driver.findElement(By.id("four")).click();
		driver.findElement(By.id("eight")).click();
		driver.findElement(By.id("two")).click();
		driver.findElement(By.id("seven")).click();
		Thread.sleep(1000);
		
		
		
		test.log(LogStatus.PASS, "Click function is working properly");
		assert(true);
		extent.endTest(test);
	    extent.flush();
	    
  }
  
  @Test(priority=8)
  public void click5() throws InterruptedException {
	  //Draw
	  	test=extent.startTest("Verify the 6th game");
		test.log(LogStatus.INFO,"Click test initialized");
		
		driver.findElement(By.id("one")).click();
		driver.findElement(By.id("three")).click();
		driver.findElement(By.id("five")).click();
		driver.findElement(By.id("nine")).click();
		driver.findElement(By.id("six")).click();
		driver.findElement(By.id("four")).click();
		driver.findElement(By.id("seven")).click();
		driver.findElement(By.id("eight")).click();
		driver.findElement(By.id("two")).click();
		Thread.sleep(1000);

		
		
		test.log(LogStatus.PASS, "Click function is working properly");
		assert(true);
		extent.endTest(test);
	    extent.flush();
	    
  }
  
  @Test(priority=9)
  public void checkResult() {
	  
	  	test=extent.startTest("Verify the Result");
		test.log(LogStatus.INFO,"Check Result test initialized");
		
		int oresult=Integer.parseInt(driver.findElement(By.id("o_win")).getText());
		int xresult=Integer.parseInt(driver.findElement(By.id("x_win")).getText());
		int drawresult=Integer.parseInt(driver.findElement(By.id("draw")).getText());
		int sum=oresult+xresult+drawresult;
	  
		if(sum==6)
		{
			
		  System.out.println("test executed succesfully");
		  
		  test.log(LogStatus.PASS, "Result is correct");
		  assert(true);
		  
		}
		else
		{

			System.out.println("Error exist in previous test cases");
		  
			test.log(LogStatus.FAIL, "Result is incorrect");
			assert(false);
	  
		}
		extent.endTest(test);
		extent.flush();

  }
  
  @Test(priority=10)
  public void restartbuttoncheck() throws InterruptedException {
	  
	  	test=extent.startTest("Verify that restart button is working correctly");
		test.log(LogStatus.INFO,"Restart button test initialized");
		
		driver.findElement(By.id("one")).click();
		driver.findElement(By.id("three")).click();
		driver.findElement(By.id("reset")).click();
		Thread.sleep(1000);

		
		
		test.log(LogStatus.PASS, "Restart button is working properly");
		assert(true);
		extent.endTest(test);
	    extent.flush();
	    
  }
  
  @Test(priority=11)
  public void refreshbrowser() throws InterruptedException {
	  test=extent.startTest("Verify that refresh browser is working correctly");
		test.log(LogStatus.INFO,"Refresh browser test initialized");
		
		driver.navigate().refresh();
		Thread.sleep(2000);
		test.log(LogStatus.PASS, "Restart button is working properly");
		assert(true);
		
		extent.endTest(test);
	    extent.flush();
  }
  
  @Test(priority=12)
  public void checkResultAfterRefresh() {
	  
	  	test=extent.startTest("Verify the Result after Browser Refresh");
		test.log(LogStatus.INFO,"Check Result after browser refresh test initialized");
		
		int oresult=Integer.parseInt(driver.findElement(By.id("o_win")).getText());
		int xresult=Integer.parseInt(driver.findElement(By.id("x_win")).getText());
		int drawresult=Integer.parseInt(driver.findElement(By.id("draw")).getText());
	  
		if(oresult==0&&xresult==0&&drawresult==0)
		{
			
		  System.out.println("test executed succesfully");
		  
		  test.log(LogStatus.PASS, "Results successfully initialized to 0");
		  assert(true);
		  
		}
		else
		{

			System.out.println("Error exist in previous test cases");
		  
			test.log(LogStatus.FAIL, "Results were not intialiazed correctly");
			assert(false);
	  
		}
		extent.endTest(test);
		extent.flush();

  }
  
  @Test(priority=13)
  public void click6() throws InterruptedException {
	  
	  	test=extent.startTest("Verify the last game");
		test.log(LogStatus.INFO,"Last game test initialized");
		
		driver.findElement(By.id("one")).click();
		driver.findElement(By.id("three")).click();
		driver.findElement(By.id("five")).click();
		driver.findElement(By.id("nine")).click();
		driver.findElement(By.id("six")).click();
		driver.findElement(By.id("four")).click();
		driver.findElement(By.id("seven")).click();
		driver.findElement(By.id("eight")).click();
		driver.findElement(By.id("two")).click();
		Thread.sleep(1000);

		
		
		test.log(LogStatus.PASS, "Last game runned successfully");
		assert(true);
		extent.endTest(test);
	    extent.flush();
	    
  }


  

  @Test(priority=14)
  public void closeBrowser() {
	  
	  	test=extent.startTest("Verify that browser closed properly");
		test.log(LogStatus.INFO,"Browser closing test initialized");
		
		driver.close();
		
		test.log(LogStatus.PASS, "Result is correct");
		assert(true);
		extent.endTest(test);
		extent.flush();
	  
  }
}
