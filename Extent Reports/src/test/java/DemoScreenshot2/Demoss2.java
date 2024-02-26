package DemoScreenshot2;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Demoss2 {
	
	WebDriver driver;
	ExtentReports extent;
	
	@BeforeSuite
	public void setUp() {
		extent= new ExtentReports();
		ExtentSparkReporter spark = new ExtentSparkReporter("Screen.html");
		
		spark.config().setTheme(Theme.DARK);
		spark.config().setDocumentTitle("Extetnt Report");
		spark.config().setReportName("My Report");
		extent.attachReporter(spark);
		
		
	   
	}
	
	@BeforeMethod
	public void setUp2() {
		  System.setProperty("driver.firefox.mariontee", "C:\\eclipse");
		    driver= new FirefoxDriver();
		    driver.get("https://www.google.com/");
	}
	
	@AfterSuite
	public void tearDwon() {
		extent.flush();
		
	}
	
	@AfterMethod
	public void tearDown1() {
		
		driver.close();
	}
	
	@Test(priority=1)
	public void attachScreenShotTest() {
		ExtentTest test=extent.createTest("First Test","Searching Automayion in google").assignAuthor("Nasif").assignCategory("Senity").assignDevice("Windows 11");
		
	   
	    
	    test.pass("browser oprns");
	   
	    driver.findElement(By.name("q")).sendKeys("Automaton",Keys.ENTER);
	    
	    test.pass("Test Finished",MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64()).build());
		
	}
	@Test(priority=2)
	public void attachScreenShotTest1() {
		ExtentTest test=extent.createTest("Second Test").assignAuthor("Nasif").assignCategory("Senity").assignDevice("Windows 11");
		
	   
	    
	    test.pass("browser oprns");
	   
	    driver.findElement(By.name("q")).sendKeys("Web Automaton",Keys.ENTER);
	    
	    test.fail("Test Finished",MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64()).build());
		
	}
	@Test(priority=2)
	public void attachScreenShotTest3() {
		ExtentTest test=extent.createTest("Second Test").assignAuthor("Nasif").assignCategory("Senity").assignDevice("Windows 11");
		
		Throwable t= new Throwable("This is a throw able exception");
	    
	    test.pass("browser oprns");
	   
	    driver.findElement(By.name("q")).sendKeys("Web Automaton",Keys.ENTER);
	    
	    test.fail(t,MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64()).build());
		
	}
	
	public String getBase64() {
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
	}


}
