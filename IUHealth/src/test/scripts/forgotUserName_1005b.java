package test.scripts;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

import test.com.functions.BaseClass;

import test.com.functions.GetFullPageScreenShot;
import test.com.functions.business_functions;

public class forgotUserName_1005b extends BaseClass{

	
	String URL = getExcelCell("IUHealth",1,1);
	String Browser = getExcelCell("IUHealth",0,1);

	String legalFirstname_invalid = getExcelCell("IUHealth",17,0);
	String legalLastname_invalid = getExcelCell("IUHealth",17,1);
	String ssn_invalid = getExcelCell("IUHealth",17,2);
	String email_invalid = getExcelCell("IUHealth",17,3);
	String month_invalid = getExcelCell("IUHealth",17,4);
	String day_invalid = getExcelCell("IUHealth",17,5);
	String year_invalid = getExcelCell("IUHealth",17,6);

	String fName_nos = getExcelCell("IUHealth",17,7);
	String lName_nos = getExcelCell("IUHealth",17,8);

	String month_14 = getExcelCell("IUHealth",17,9);
	String day_14 = getExcelCell("IUHealth",17,10);
	String year_14 = getExcelCell("IUHealth",17,11);
	String ssn_2digit = getExcelCell("IUHealth",17,12);
	String email_invalidformat = getExcelCell("IUHealth",17,13);

	String legalFirstname = getExcelCell("IUHealth",17,14);
	String legalLastname = getExcelCell("IUHealth",17,15);
	String month = getExcelCell("IUHealth",17,16);
	String day = getExcelCell("IUHealth",17,17);
	String year = getExcelCell("IUHealth",17,18);
	String ssn = getExcelCell("IUHealth",17,19);
	String email = getExcelCell("IUHealth",17,20);

	String invalidAnswer1 = getExcelCell("IUHealth", 17, 21);
	String invalidAnswer2 = getExcelCell("IUHealth", 17, 22);
	String invalidAnswer3 = getExcelCell("IUHealth", 17, 23);

	String address_invalid = getExcelCell("IUHealth", 17, 24);
	String city_invalid = getExcelCell("IUHealth", 17, 25);
	String state_invalid = getExcelCell("IUHealth", 17, 26);
	String zipcode_invalid = getExcelCell("IUHealth", 17, 27);

	String address = getExcelCell("IUHealth", 17, 28);
	String city = getExcelCell("IUHealth", 17, 29);
	String state = getExcelCell("IUHealth", 17, 30);
	String zipcode = getExcelCell("IUHealth", 17, 31);

	business_functions objBF = new business_functions();
	
	ExtentReports extent;
	ExtentTest test;
	
	@BeforeTest
	public void setup(){

		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("extent.html");
		htmlReporter.setAppendExisting(true);
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		test = extent.createTest("Test Case 1005B", "Verify Forgot Username - Alternate Flow");
		
	}


	@Test
	public void verify_forgotUserName_1005b() throws Exception{

		try{
			
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			WaitForElementPresent("lnk_SignIn_HomePg", time);
			Click("lnk_SignIn_HomePg");
			WaitForElementPresent("txt_UserName", time);

			// Navigate to Forgot UserName page
			Click("lnk_forgotUsername");
			WaitForElementPresent("txt_LegalFirstName_ForgotUserName", 20);
			Click("btn_Submit_ForgotUserName");
			Thread.sleep(2000);
			try{
				WebElement err_msg = driver.findElement(map.getLocator("msg_ErrorAlert_ForgotUsername"));
				test.pass("Verify Error messages appear on clicking Submit: Error messages appear successfully");
			}
			catch(Exception a){
				String screenShotPath6 = GetFullPageScreenShot.capture(driver, "MyFullPageScreenshot");
				test.fail("Verify Error messages appear on clicking Submit: Error messages appear successfully").addScreenCaptureFromPath(screenShotPath6);


			}
			Assert.assertTrue(driver.findElement(map.getLocator("err_LegalFirstName_ForgotUsername")).getText().contains("Please enter your legal first name") );
			test.pass("Verify Error messages appear on clicking Submit: Error message for Legal First Name appear successfully");
			Assert.assertTrue(driver.findElement(map.getLocator("err_LegalLastName_ForgotUsername")).getText().contains("Please enter your legal last name") );
			test.pass("Verify Error messages appear on clicking Submit: Error message for Legal Last Name appear successfully");

			Assert.assertTrue(driver.findElement(map.getLocator("err_SSS_ForgotUsername")).getText().contains("Please enter the last four digits of your social security number") );
			test.pass("Verify Error messages appear on clicking Submit: Error message for SSN appear successfully");

			Assert.assertTrue(driver.findElement(map.getLocator("err_Email_ForgotUsername")).getText().contains("Please enter email address") );
			test.pass("Verify Error messages appear on clicking Submit: Error message for Email appear successfully");

			Click("btn_Cancel_ForgotUserName");
			WaitForElementPresent("msg_ErrorAlert_ForgotUsername1", time);
			test.pass("Verify the message on clicking Cancel button: Are you sure you want to Cancel?");
			Click("btn_Yes_popup");
			WaitForElementPresent("txt_UserName", time);
			Click("lnk_forgotUsername");
			WaitForElementPresent("txt_LegalFirstName_ForgotUserName", 20);
			Thread.sleep(2000);
			Click("btn_Cancel_ForgotUserName");
			Click("btn_No_popup");
			WaitForElementPresent("btn_Cancel_ForgotUserName", time);
			Assert.assertTrue(objBF.submit_forgot_Username_invalid(legalFirstname_invalid,legalLastname_invalid,month_invalid,day_invalid,year_invalid,ssn_invalid,email_invalid));
			test.pass("Enter wrong details in all fields and Submit: Error Message appears successfully");

			Click("btn_OK_alert_ForgotUserName_Pg2");
			SendKeys("txt_LegalFirstName_ForgotUserName", fName_nos );
			SendKeys("txt_LegalLastName_ForgotUserName", lName_nos );
			Click("btn_Submit_ForgotUserName");
			Thread.sleep(2000);

			Assert.assertEquals(driver.findElement(map.getLocator("err_LegalFirstName_ForgotUsername")).getText(),"First and Last name should not contain numeric and special characters except when a hyphen is used");
			Assert.assertEquals(driver.findElement(map.getLocator("err_LegalLastName_ForgotUsername")).getText(),"Last and First name should not contain numeric and special characters except when a Hyphen is used");
			test.pass("Enter numbers in name fields and verify the message");


			driver.findElement(map.getLocator("txt_LegalFirstName_ForgotUserName")).clear();
			driver.findElement(map.getLocator("txt_LegalLastName_ForgotUserName")).clear();

			SelectTextByVisibleText("lst_DateOfBirth_Month_ForgotUserName", month_14);
			SelectTextByVisibleText("lst_DateOfBirth_Day_ForgotUserName", day_14);
			SelectTextByVisibleText("lst_DateOfBirth_Year_ForgotUserName", year_14);
			Click("btn_Submit_ForgotUserName");
			Thread.sleep(1000);
			Assert.assertTrue(driver.findElement(map.getLocator("err_dob_ForgotUsername")).isDisplayed());
			test.pass("Enter DOB less than 14 years and verify the message");


			SendKeys("txt_lastFourDigitsofSSN_ForgotUserName",ssn_2digit);
			SendKeys("txt_EmailAddress_ForgotUserName",email_invalidformat);
			Click("btn_Submit_ForgotUserName");
			Thread.sleep(1000);

			Assert.assertEquals(driver.findElement(map.getLocator("err_Email_ForgotUsername")).getText(),"Please enter a valid email address");
			test.pass("Enter invalid Email address: Error Alert is displayed");

			Assert.assertEquals(driver.findElement(map.getLocator("err_SSS_ForgotUsername")).getText(),"Please enter the last four digits of your social security number");
			test.pass("Enter SSN less than 4 digits: Error Alert is displayed");

			driver.findElement(map.getLocator("txt_LegalFirstName_ForgotUserName")).clear();
			driver.findElement(map.getLocator("txt_LegalLastName_ForgotUserName")).clear();
			driver.findElement(map.getLocator("txt_EmailAddress_ForgotUserName")).clear();
			driver.findElement(map.getLocator("txt_lastFourDigitsofSSN_ForgotUserName")).clear();
			Thread.sleep(1000);

			driver.findElement(map.getLocator("txt_LegalFirstName_ForgotUserName")).sendKeys(Keys.SPACE);
			driver.findElement(map.getLocator("txt_LegalLastName_ForgotUserName")).sendKeys(Keys.SPACE);
			driver.findElement(map.getLocator("txt_EmailAddress_ForgotUserName")).sendKeys(Keys.SPACE);
			Click("btn_Submit_ForgotUserName");

			Thread.sleep(1000);

			Assert.assertEquals(driver.findElement(map.getLocator("err_LegalFirstName_ForgotUsername")).getText(),"Please enter a valid entry. Only spaces not allowed");
			Assert.assertEquals(driver.findElement(map.getLocator("err_LegalLastName_ForgotUsername")).getText(),"Please enter a valid entry. Only spaces not allowed");
			Assert.assertEquals(driver.findElement(map.getLocator("err_Email_ForgotUsername")).getText(),"Please enter a valid entry. Only spaces not allowed");
			test.pass("Enter only spaces in fields and Verify the message");

			Assert.assertTrue(objBF.submit_forgot_Username(legalFirstname,legalLastname,month,day,year,ssn,email));
			test.pass("Enter valid information in all fields and Submit");
			Click("btn_Submit_ForgotUserName_Pg2");

			Assert.assertEquals(driver.findElement(map.getLocator("err1_securityAnswers_ForgotUsername_pg2")).getText(), "Please enter an Answer");
			Assert.assertEquals(driver.findElement(map.getLocator("err2_securityAnswers_ForgotUsername_pg2")).getText(), "Please enter an Answer");
			Assert.assertEquals(driver.findElement(map.getLocator("err3_securityAnswers_ForgotUsername_pg2")).getText(), "Please enter an Answer");
			test.pass("Verify Error message on Submit by leaving all fields Blank:forgot username page2");

			Assert.assertTrue(objBF.forgotUsernamePage2_invalid(invalidAnswer1,invalidAnswer2,invalidAnswer3));
			WaitForElementPresent("msg_InvalidAttempt_ForgotUsername_pg2", time);
			System.out.println(driver.findElement(map.getLocator("msg_InvalidAttempt_ForgotUsername_pg2")).getText());
			Assert.assertTrue(objBF.forgotUsernamePage2_invalid(invalidAnswer1,invalidAnswer2,invalidAnswer3));
			WaitForElementPresent("msg_InvalidAttempt_ForgotUsername_pg2", time);
			Assert.assertTrue(objBF.forgotUsernamePage2_invalid(invalidAnswer1,invalidAnswer2,invalidAnswer3));
			Thread.sleep(1000);
			test.pass("Enter Invalid Answers in Forgot Username Step2 3 attempts: Verify the Error message 'Sorry! The answers you provided do not match the answers we have on record..if you '");
			Click("lnk_ClickHere_Forgotusername_pg2");
			WaitForElementPresent("txt_LegalFirstName_UsernameRecovery", time);

			Assert.assertTrue(objBF.usernameRecoveryInvalid(address_invalid,city_invalid,state_invalid,zipcode_invalid),"Verify Providing invalid details in Username Recovery page");
			Assert.assertTrue(driver.findElement(map.getLocator("popup_Message_ForgotUsername")).getText().contains("Sorry! We could not match your record. To access your My IU Health account, please try again or call Health Information Management at 317.963.1661 for assistance. To pay your bill without an account, please use"));
			Click("btn_OK_alert_UsernameRecovery");	
			test.pass("Enter Invalid details and submit - Username Recovery page");
			Assert.assertTrue(objBF.usernameRecovery(address,city,state,zipcode),"Verify providing Valid details and submit Username Recovery page");
			test.pass("Enter Valid details and submit - Username Recovery page");

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


	@AfterTest
	public void tear()
	{
		driver.quit();

	}


}
