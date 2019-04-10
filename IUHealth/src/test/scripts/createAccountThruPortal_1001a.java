package test.scripts;

import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import test.com.functions.BaseClass;
import test.com.functions.GetFullPageScreenShot;
import test.com.functions.business_functions;

public class createAccountThruPortal_1001a extends BaseClass{
	business_functions objBF = new business_functions();

	WebDriver driver;
	ExtentReports extent;
	ExtentTest test;
	String first_name = getExcelCell("IUHealth",21,0);
	String last_name = getExcelCell("IUHealth",21,1);
	String date = getExcelCell("IUHealth",21,2);
	String month = getExcelCell("IUHealth",21,3);
	String year = getExcelCell("IUHealth",21,4);
	String ssn = getExcelCell("IUHealth",21,5);
	String add = getExcelCell("IUHealth",21,6);
	String city = getExcelCell("IUHealth",21,7);
	String state = getExcelCell("IUHealth",21,8);
	String zipcode = getExcelCell("IUHealth",21,9);
	String ans1 = getExcelCell("IUHealth",21,10);
	String ans2 = getExcelCell("IUHealth",21,11);
	String ans3 = getExcelCell("IUHealth",21,12);
	String ans4 = getExcelCell("IUHealth",21,13);

	@BeforeTest
	public void setup(){

		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("extent.html");
		htmlReporter.setAppendExisting(true);
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		test = extent.createTest("Test Case 1001A", "Create Account through Portal - 1001A");

	}
	
	@Test
	public void createAccount(){
		try{
			Assert.assertTrue(IsElementPresent("lnk_CreateAnAccount_HomePg") && IsElementPresent("lnk_SignIn_HomePg") && IsElementPresent("img_logo_HomePg"));
			test.pass("Verify the presence of link 'Create An Account' and link 'Sign In' and logo ");
			Assert.assertTrue(IsElementPresent("img_FeaturesNewandAnnouncements_HomePg"));
			test.pass("Verify the presence of image under Featured News and Announcements");
			
			Assert.assertTrue(IsElementPresent("ele_WecomeToMyIUHealth_HomePg") && IsElementPresent("ele_ReqAppointment_HomePg") && IsElementPresent("ele_SignIn_HomePg"));
			test.pass("Verify the presence of heading Quick Pay link, Request and Appointment, Welcome to my IUHealth and SignIn");
			Click("lnk_CreateAnAccount_HomePg");
			WaitForElementPresent("ele_msg_CreateAnAccount", time);
			Assert.assertTrue(IsElementPresent("ele_msg_CreateAnAccount"));
			test.pass("Verify the description on the Form");
			Assert.assertTrue(IsElementPresent("txt_LegalFirstName_CreateAnAccount"));
			Assert.assertTrue(IsElementPresent("txt_LegalLastName_CreateAnAccount"));
			Assert.assertTrue(IsElementPresent("txt_LastFourDigitsSSN_CreateAnAccount"));
			Assert.assertTrue(IsElementPresent("txt_Address_CreateAnAccount"));
			Assert.assertTrue(IsElementPresent("txt_Apartment_CreateAnAccount"));
			Assert.assertTrue(IsElementPresent("txt_City_CreateAnAccount"));
			Assert.assertTrue(IsElementPresent("lst_State_CreateAnAccount"));
			Assert.assertTrue(IsElementPresent("txt_ZipCode_CreateAnAccount"));
			Assert.assertTrue(IsElementPresent("lst_month_DOB_CreateAnAccount"));
			Assert.assertTrue(IsElementPresent("lst_day_DOB_CreateAnAccount"));
			Assert.assertTrue(IsElementPresent("lst_year_DOB_CreateAnAccount"));
			Assert.assertTrue(IsElementPresent("chkbox_CreateAnAccount"));
			Assert.assertTrue(IsElementPresent("btn_submit_CreateAnAccount"));
			test.pass("Verify the fields in Create An Account page");
//			System.out.println(driver.getCurrentUrl());
//			Assert.assertTrue(driver.getCurrentUrl().contains("formrepository/createaccount/createaccountstep1"),"Verify the URL in Create An Account page");
	//		test.pass("Verify the URL in Create An Account page");
			Assert.assertTrue(IsElementPresent("lnk_CreateAnAccount_CreateAnAccount") && IsElementPresent("lnk_Home_CreateAnAccount"), "Verify the presence of Breadcrumbs home and Create an Account");
			test.pass("Verify the presence of Breadcrumbs home and Create an Account");
			
			Assert.assertTrue(objBF.createAnAccount(first_name, last_name, month, date, year, ssn, add, city, zipcode, state));
			SelectTextByVisibleText("lst_securityQn1_CreateAnAccount", ans1);
			SelectTextByVisibleText("lst_securityQn2_CreateAnAccount", ans2);
			SelectTextByVisibleText("lst_securityQn3_CreateAnAccount", ans3);
			SelectTextByVisibleText("lst_securityQn4_CreateAnAccount", ans4);
			
		}
		catch(Exception e){
			test.fail(e);
			e.printStackTrace();
		}
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


}
