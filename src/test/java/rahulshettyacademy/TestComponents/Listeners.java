package rahulshettyacademy.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import rahulshettyacademy.resources.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener {
    ExtentTest test;
	ExtentReports extent = ExtentReporterNG.getReportObject();
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	 @Override
	    public void onTestStart(ITestResult result) {
		   test = extent.createTest(result.getMethod().getMethodName());
		   extentTest.set(test);
	        
	    }

	    @Override
	    public void onTestSuccess(ITestResult result) {
	    	extentTest.get().log(Status.PASS, "Test Passed");
	    }

	    @Override
	    public void onTestFailure(ITestResult result) {
	    	 extentTest.get().fail(result.getThrowable());
	    	 try {
				driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
			} catch (Exception e1) {
				e1.printStackTrace();
			} 
	    	String filePath = null;
			try {
				filePath = getScreenshot(result.getMethod().getMethodName(),driver);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
	    }

	    @Override
	    public void onTestSkipped(ITestResult result) {
	        System.out.println("Test Skipped: " + result.getName());
	    }

	    @Override
	    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	        // Optional
	    }

	    @Override
	    public void onStart(ITestContext context) {
	        System.out.println("Test Started for context: " + context.getName());
	    }

	    @Override
	    public void onFinish(ITestContext context) {
	        extent.flush();
	    }
}
