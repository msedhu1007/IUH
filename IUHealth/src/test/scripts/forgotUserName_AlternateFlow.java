package test.scripts;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import test.com.functions.BaseClass;


public class forgotUserName_AlternateFlow extends BaseClass{

	ExtentReports extent;
	ExtentTest test;

	String URL = getExcelCell("IUHealth",1,1);
	String Browser = getExcelCell("IUHealth",0,1);

	String legalFirstname = getExcelCell("IUHealth",13,0);
	String legalLastname = getExcelCell("IUHealth",13,1);
	String month = getExcelCell("IUHealth",13,2);
	String day = getExcelCell("IUHealth",13,3);
	String year = getExcelCell("IUHealth",13,4);
	String ssn = getExcelCell("IUHealth",13,5);
	String email = getExcelCell("IUHealth",13,6);
	String sec1 = getExcelCell("IUHealth",13,7);
	String sec2 = getExcelCell("IUHealth",13,8);
	String sec3 = getExcelCell("IUHealth",13,9);

	@BeforeClass
	public void setup(){
		
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("extent.html");
		htmlReporter.setAppendExisting(true);
		test = extent.createTest("Test Case 1005A", "Verify Forgot Username - Alt Flow");
		test.assignAuthor("Sedhu");


	}

	@Test
	public void forgotUsername_Alternateflow(){

		try{

			

			
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	@AfterClass
	public void tear()
	{
		extent.flush();
		driver.close();
	}


}
