package test.scripts;

import org.testng.Assert;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import test.com.functions.BaseClass;
import test.com.functions.GetFullPageScreenShot;

public class Verify_Test extends BaseClass{
	ExtentHtmlReporter htmlReporter;
	ExtentReports extent;
	ExtentTest test;
	
	@BeforeClass
	public void setup(){
	
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("extent.html");
		htmlReporter.setAppendExisting(true);
		test = extent.createTest("Test Case sample", "Verify Forgot Username");
		test.assignAuthor("Sedhu");
		extent.setSystemInfo("OS","Windows 10");
		extent.setSystemInfo("Executed by","Sedhu");

	}
	
	@AfterMethod
	public void getResult(ITestResult result) throws Exception{ 
		if(result.getStatus()== ITestResult.FAILURE){
			String screenshotpath = GetFullPageScreenShot.capture(driver, "screenshotname");
			test.log(Status.FAIL,MarkupHelper.createLabel(result.getName()+" Test Case FAILED due to below issues:", ExtentColor.RED));
			test.fail(result.getThrowable());
			test.fail("Snapshot below: "+test.addScreenCaptureFromPath(screenshotpath));
		}
		else if(result.getStatus()==ITestResult.SUCCESS){
			test.log(Status.PASS, MarkupHelper.createLabel(result.getName()+" Test Case PASSED",ExtentColor.GREEN));
		}
		else{
			test.log(Status.SKIP, MarkupHelper.createLabel(result.getName()+" Test Case SKIPPED",ExtentColor.ORANGE));
			test.skip(result.getThrowable());
		}
		extent.flush();
	}
	
	@Test
	public void testWhetherDataPickedFromExcelCorrectly(){
		int ctr	= excelIterate(Verify_Test.class.getSimpleName());
	//	System.out.println(ctr);
	//	System.out.println(getExcelCell(Sheet, intRow, intCol));
	//	System.out.println(getExcelValue("IUHealth",ctr,"Pwd"));
		System.out.println(driver.getCurrentUrl());
		Assert.assertTrue(driver.getCurrentUrl().equalsIgnoreCase("abc"),"Verify Current URL");
	}
}
