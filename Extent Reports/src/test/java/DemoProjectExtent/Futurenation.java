package DemoProjectExtent;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
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

public class Futurenation {
	
	WebDriver driver;
	ExtentReports extent;

	
	@BeforeSuite
	public void ExtxtsetUp() {
		extent= new ExtentReports();
		ExtentSparkReporter spark = new ExtentSparkReporter("Screen.html");
		
		spark.config().setTheme(Theme.DARK);
		spark.config().setDocumentTitle("Extetnt Report");
		spark.config().setReportName("My Report");
		extent.attachReporter(spark);
	}
	
	@BeforeMethod
	public void url() throws InterruptedException {
		
		System.setProperty("C:\\eclipse\\chrome-win64\\chrome.exe", "C:\\eclipse");
		
		driver= new ChromeDriver();
		
		driver.get("https://platform.futurenation.gov.bd/");
		driver.manage().window().maximize();
		Thread.sleep(2000);
		String Exp_title="Futurenation | Homepage";
		String Act_title=driver.getTitle();
		System.out.println(Act_title);
		Thread.sleep(2000);
		Assert.assertEquals(Exp_title, Act_title);
		
	}
	
	@Test(priority=1,description="valid Email and password")
	public void validSignin() throws InterruptedException {
		ExtentTest test=extent.createTest("Sign In","Signing In").assignAuthor("Nasif").assignCategory("Senity").assignDevice("Windows 11");

		Thread.sleep(1000);
		driver.findElement(By.xpath("/html/body/div[2]/div/div/header/div/div/div/button")).click();
		test.pass("SignIn Page opens");
		Thread.sleep(1000);
		driver.findElement(By.id("username")).sendKeys("nasifsadiq16@gmail.com");
		test.info("Valid Email entered");
		Thread.sleep(1000);
		driver.findElement(By.id("password")).sendKeys("Nasifsadiq16@");
		test.info("Valid Password entered");
		Thread.sleep(1000);
		driver.findElement(By.xpath("/html/body/div/div/div/div/div/div/div[1]/form/div[4]/input[2]")).click();
		Thread.sleep(1000);
		test.info("Sign In Button Clicked");
		String Exp_title="Futurenation | Homepage";
		String Act_title=driver.getTitle();
		System.out.println(Act_title);
		Thread.sleep(2000);
		Assert.assertEquals(Exp_title, Act_title);
		 test.pass("Loged In successfully",MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64()).build());
		
		
	}
	
	@Test(priority=1,description="Invalid Email and Valid Password")
	public void invalidsignup() throws InterruptedException {
		ExtentTest test=extent.createTest("Sign Up","Signing UP").assignAuthor("Nasif").assignCategory("Senity").assignDevice("Windows 11");

		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div/header/div/div/div/a")).click();
		Thread.sleep(1000);
		test.pass("Sign Up Page opens");
		
		JavascriptExecutor js= (JavascriptExecutor)driver;
		
		WebElement invalidemail= driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div/div/form/div/div/div[3]/div/div/div/input"));
		js.executeScript("arguments[0].value='xy@gmail.com'", invalidemail);
		test.info("email entered");
		
		Thread.sleep(1000);
		WebElement validpass1=driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div/div/form/div/div/div[4]/div/div/div/input"));
		js.executeScript("arguments[0].value='Ppassword123'", validpass1);
		test.info("Password entered");
		
		Thread.sleep(1000);
		WebElement validpass2=driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div/div/form/div/div/div[5]/div/div/div/input"));
		js.executeScript("arguments[0].value='Ppassword123'", validpass2);
		test.info("Re-Password entered");
		
		Thread.sleep(1000);
		
		WebElement button=driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div/div/form/div/div/div[6]/button"));
		js.executeScript("arguments[0].click();", button);
	  
		Throwable t= new Throwable("This is a throw able exception");
		
		String exp_result="invalid email";
		String act_result= driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div/div/form/div/div/div[3]/div/div/p")).getText();
		
		test.fail(t,MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64()).build());

		Assert.assertEquals(exp_result, act_result);
	}
	
	@AfterMethod
	public void tearDown1() {
		
		driver.close();
	}

	@AfterSuite
	public void tearDwon() {
		extent.flush();
		
	}
	public String getBase64() {
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
	}

}
