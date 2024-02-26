package Extent1;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class DemoExtent1 {
	@Test
	public void eventtest() {
		ExtentReports extent= new ExtentReports();
		ExtentSparkReporter spark = new ExtentSparkReporter("Spark.html");
		ExtentSparkReporter fspark = new ExtentSparkReporter("Failed-Spark.html").filter().statusFilter().as(new Status[] {Status.FAIL,Status.SKIP}).apply();

		spark.config().setTheme(Theme.DARK);
		spark.config().setDocumentTitle("Extetnt Report");
		spark.config().setReportName("My Report");
		extent.attachReporter(spark,fspark);
		
		fspark.config().setTheme(Theme.DARK);
		fspark.config().setDocumentTitle("Extetnt Report");
		fspark.config().setReportName("My Report");
		
		
	   ExtentTest test=extent.createTest("LogIn Test").assignAuthor("Nasif").assignCategory("Smoke Test").assignDevice("Chrome");
	   test.pass("Log In test Starts successfully");
	   test.info("url opens");
	   test.info("Value entered");
	   test.pass("test passed");
	   
	   ExtentTest test1=extent.createTest("SignUp Test").assignAuthor("nasif").assignCategory("Smoke").assignDevice("Chrome");
	   test1.pass("SignUptest Starts successfully");
	   test1.info("url opens");
	   test1.info("Value entered");
	   test1.fail("test passed");
	   
	   extent.flush();
	}


}
