/* author: Sedhu Madhavan 
 * Date: 16- Aug - 2017
 * Test Case:1005a - Forgot Username 
 * */

package test.scripts;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
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

public class forgotUserName_1005a extends BaseClass{

	
	ExtentReports extent;
	ExtentTest test;

	String URL = getExcelCell("IUHealth",1,1);
	String Browser = getExcelCell("IUHealth",0,1);

	String legalFirstname = getExcelCell("IUHealth",9,0);
	String legalLastname = getExcelCell("IUHealth",9,1);
	String month = getExcelCell("IUHealth",9,2);
	String day = getExcelCell("IUHealth",9,3);
	String year = getExcelCell("IUHealth",9,4);
	String ssn = getExcelCell("IUHealth",9,5);
	String email = getExcelCell("IUHealth",9,6);
	String sec1 = getExcelCell("IUHealth",9,7);
	String sec2 = getExcelCell("IUHealth",9,8);
	String sec3 = getExcelCell("IUHealth",9,9);
	String sec4 = getExcelCell("IUHealth",9,10);
	String sec5 = getExcelCell("IUHealth",9,11);
	String sec6 = getExcelCell("IUHealth",9,12);
	String sec7 = getExcelCell("IUHealth",9,13);
	String sec8 = getExcelCell("IUHealth",9,14);
	String sec9 = getExcelCell("IUHealth",9,15);


	business_functions objBF = new business_functions();
	String n = RandomNumber();

	@BeforeClass
	public void setup(){

		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("extent.html");
		htmlReporter.setAppendExisting(true);
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		test = extent.createTest("Test Case 1005A", "Verify Forgot Username");
		test.assignAuthor("Sedhu");
		extent.setSystemInfo("OS","Windows 10");
		extent.setSystemInfo("Executed by","Sedhu");
		htmlReporter.config().setChartVisibilityOnOpen(true);
		htmlReporter.config().setDocumentTitle("IUHealth Automation Report");
		htmlReporter.config().setReportName("Test Execution Report");
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		htmlReporter.config().setTheme(Theme.STANDARD);

	}

	
	@Test
	public void verify_ForgotUserName() throws Exception{

		try{
			driver.manage().window().maximize();
			WebElement txt_username = driver.findElement(map.getLocator("txt_UserName"));
			WebElement txt_pwd = driver.findElement(map.getLocator("txt_Password"));
			WebElement lnk_forgotUsername = driver.findElement(map.getLocator("lnk_forgotUsername"));
			WebElement lnk_frgotPwd = driver.findElement(map.getLocator("lnk_forgotPassword"));

			Assert.assertTrue(txt_username.isDisplayed() && txt_pwd.isDisplayed() && lnk_forgotUsername.isDisplayed() && lnk_frgotPwd.isDisplayed(),"Verify the link forgot username, forgot password, fields username and password are present");
			test.pass("Verify the text field Username, Password and link ForgotUsername, ForgotPassword are displayed");
			Click("lnk_forgotUsername");
			Assert.assertTrue(driver.getCurrentUrl().contains("formrepository/usernamerecovery/forgotusername_step1"), "Verify the URL of the page");
			test.pass("Verify the URL of the page");
			Assert.assertTrue(driver.findElement(map.getLocator("lnk_SignIn_forgotUserName")).isDisplayed() && driver.findElement(map.getLocator("lnk_CreateAnAccount_ForgotUserName")).isDisplayed(), "Verify the presence of link Sign In and Create An Account");
			test.pass("Verify the presence of link Sign In and Create An Account");
			Assert.assertTrue(driver.findElement(map.getLocator("header_ForgotUsername_ForgotUsername")).isDisplayed());

			Assert.assertTrue(driver.findElement(map.getLocator("txtHeading_ForgotUsername")).isDisplayed());
			Assert.assertTrue(driver.findElement(map.getLocator("txtHeading_Mandatory_ForgotUsername")).isDisplayed());
			test.pass("Verify the heading forgotusername,denotes mandtory field heading");
			Assert.assertTrue(IsElementPresent("txt_LegalFirstName_ForgotUserName") );
			Assert.assertTrue(IsElementPresent("txt_LegalLastName_ForgotUserName") );
			Assert.assertTrue(IsElementPresent("lst_DateOfBirth_Month_ForgotUserName") );
			Assert.assertTrue(IsElementPresent("lst_DateOfBirth_Year_ForgotUserName") );
			Assert.assertTrue(IsElementPresent("lst_DateOfBirth_Day_ForgotUserName") );
			Assert.assertTrue(IsElementPresent("txt_lastFourDigitsofSSN_ForgotUserName") );
			Assert.assertTrue(IsElementPresent("txt_EmailAddress_ForgotUserName"),"Verify the button Submit" );

			Assert.assertTrue(driver.findElement(map.getLocator("btn_Submit_ForgotUserName")).isDisplayed() );
			Assert.assertTrue(IsElementPresent("btn_Submit_ForgotUserName"),"Verify the Submit button is present" );
			Assert.assertTrue(IsElementPresent("btn_Cancel_ForgotUserName"),"Verify the Cancel button is present" );
			test.pass("Verify the fields FirstName, Last Name, DOB, SSN, Email text fields, Submit and Cancel buttons");
			Assert.assertTrue(IsElementPresent("txt_ContactInformation_Footer") && IsElementPresent("txt_PhoneNumber_Footer") && IsElementPresent("lnk_LegalNotices_Footer") && IsElementPresent("lnk_PrivacyPolicy_Footer") && IsElementPresent("lnk_TermsOfUse_Footer") && IsElementPresent("lnk_ContactUs_Footer") && IsElementPresent("lnk_HelpGuide_Footer") && IsElementPresent("lnk_IUHealth_Footer") && IsElementPresent("lnk_HelpUsImprove_Footer"),"Verify the presence of links in Footer" );
			test.pass("Verify the footer of the page");
			Assert.assertTrue(objBF.submit_forgot_Username(legalFirstname,legalLastname,month,day,year,ssn,email));
			test.pass("Verify submitting correct details in Forgot Password page");
			Assert.assertTrue(objBF.forgotUsernamePage2_invalid(sec1,sec2,sec3));	
			test.pass("Verify submitting in valid details in Forgot Password page: First Attempt");
			WaitForElementPresent("msg_InvalidAttempt_ForgotUsername_pg2", time);
			Assert.assertTrue(objBF.forgotUsernamePage2_invalid(sec4,sec5,sec6));	
			test.pass("Verify submitting in valid details in Forgot Password page: Second Attempt");
			WaitForElementPresent("msg_InvalidAttempt_ForgotUsername_pg2", time);
			Assert.assertTrue(objBF.forgotUsernamePage2(sec7,sec8,sec9));
			WaitForElementPresent("btn_OK_alert_ForgotUserName_Pg2", time);

			WebElement msg = driver.findElement(map.getLocator("msg_successfulEmail_ForgotUsername_pg2"));
			Assert.assertTrue(msg.getText().contains("Please check the email address registered to your My IU Health account"),"Verify Entering Correct answers in Forgot UserName Page2 - third Attempt");
			test.pass("Verify submitting correct details in Forgot Password page: 3rd Attempt");
		}
		catch (Exception e) {
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