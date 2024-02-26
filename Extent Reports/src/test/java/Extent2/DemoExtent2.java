package Extent2;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class DemoExtent2 {
	
	WebDriver driver;
	ExtentReports extent;
	
	@BeforeSuite
	public void setUp() {
		extent= new ExtentReports();
		ExtentSparkReporter spark = new ExtentSparkReporter("Spark.html");
		
		spark.config().setTheme(Theme.DARK);
		spark.config().setDocumentTitle("Extetnt Report");
		spark.config().setReportName("My Report");
		extent.attachReporter(spark);
	}
	
	@AfterSuite
	public void tearDwon() {
		extent.flush();
	}
	
	@Test
	public void attachScreenShotTest() {
		ExtentTest test=extent.createTest("First Test");
		
	    System.setProperty("driver.firefox.mariontee", "C:\\eclipse");
	    driver= new FirefoxDriver();
	    
	    test.pass("browser oprns");
	    driver.get("https://www.google.com/");
	    
	    driver.findElement(By.name("q")).sendKeys("Automaton",Keys.ENTER);
	    
	    test.pass("Test Finished");
		
	}

}
