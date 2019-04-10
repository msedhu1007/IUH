package test.com.functions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.openqa.selenium.Keys;

public class business_functions extends BaseClass{


	public static Boolean signIn(String userName, String password){
		Boolean strResult = false;
		try{
			openURL(URL);
			WaitForElementPresent("lnk_SignIn_HomePg", time);
			Click("lnk_SignIn_HomePg");
			WaitForElementPresent("txt_UserName", time);
			SendKeys("txt_UserName",userName);
			SendKeys("txt_Password",password);
			Click("btn_SignIn");
			WaitForElementPresent("ele_Cursor_HomePg", time);
			strResult = true;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return strResult;
	}

	public Boolean submit_forgot_Username(String legalFirstNm,String legalLastNm,String month,String day,String year,String ssn,String email){
		Boolean strResult = false;
		try{
			SendKeys("txt_LegalFirstName_ForgotUserName", legalFirstNm );
			SendKeys("txt_LegalLastName_ForgotUserName", legalLastNm );
			SelectTextByVisibleText("lst_DateOfBirth_Month_ForgotUserName", month);
			SelectTextByVisibleText("lst_DateOfBirth_Day_ForgotUserName", day);
			SelectTextByVisibleText("lst_DateOfBirth_Year_ForgotUserName", year);
			SendKeys("txt_lastFourDigitsofSSN_ForgotUserName", ssn );
			SendKeys("txt_EmailAddress_ForgotUserName", email );
			Thread.sleep(1000);
			JSClick("btn_Submit_ForgotUserName");
			WaitForElementPresent("txt_Mandatory1", time);
			strResult = true;
			return strResult;

		}
		catch(Exception e){
			e.printStackTrace();
			return strResult;
		}

	}


	public Boolean submit_forgot_Username_invalid(String legalFirstNm,String legalLastNm,String month,String day,String year,String ssn,String email){
		Boolean strResult = false;
		try{
			SendKeys("txt_LegalFirstName_ForgotUserName", legalFirstNm );
			SendKeys("txt_LegalLastName_ForgotUserName", legalLastNm );
			SelectTextByVisibleText("lst_DateOfBirth_Month_ForgotUserName", month);
			SelectTextByVisibleText("lst_DateOfBirth_Day_ForgotUserName", day);
			SelectTextByVisibleText("lst_DateOfBirth_Year_ForgotUserName", year);
			SendKeys("txt_lastFourDigitsofSSN_ForgotUserName", ssn );
			SendKeys("txt_EmailAddress_ForgotUserName", email );
			Click("btn_Submit_ForgotUserName");
			WaitForElementPresent("btn_OK_alert_ForgotUserName_Pg2", time);
			strResult = true;
			return strResult;

		}
		catch(Exception e){
			e.printStackTrace();
			return strResult;
		}

	}


	public Boolean forgotUsernamePage2(String val1, String val2, String val3){

		Boolean strResult = false; 
		try{
			WaitForElementPresent("txt_Mandatory1", time);
			SendKeys("txt_Mandatory1", val1);
			SendKeys("txt_Mandatory2", val2);
			SendKeys("txt_Mandatory3", val3);
			JSClick("btn_Submit_ForgotUserName_Pg2");
			WaitForElementPresent("txt_Mandatory1", time);
			strResult = true;
			return strResult;
		}
		catch(Exception e){
			e.printStackTrace();
			return strResult;
		}
	}


	public Boolean forgotUsernamePage2_invalid(String val1, String val2, String val3){

		Boolean strResult = false; 
		try{
			WaitForElementPresent("txt_Mandatory1", time);
			SendKeys("txt_Mandatory1", val1);
			SendKeys("txt_Mandatory2", val2);
			SendKeys("txt_Mandatory3", val3);
			JSClick("btn_Submit_ForgotUserName_Pg2");

			strResult = true;
			return strResult;
		}
		catch(Exception e){
			e.printStackTrace();
			return strResult;
		}
	}

	public Boolean usernameRecovery(String address, String city, String state, String zipcode){
		boolean strResult = false;
		try{
			SendKeys("txt_Address_UsernameRecovery", address);
			SendKeys("txt_City_UsernameRecovery", city);
			SelectTextByVisibleText("lst_State_UsernameRecovery", state);
			SendKeys("txt_Zipcode_UsernameRecovery", zipcode);
			JSClick("btn_Submit_UsernameRecovery");
			WaitForElementPresent("lst_SecurityQuestions1_ForgotPwd", time);
			strResult = true;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return strResult;
	}


	public Boolean usernameRecoveryInvalid(String address, String city, String state, String zipcode){
		boolean strResult = false;
		try{
			Thread.sleep(2000);
			SendKeys("txt_Address_UsernameRecovery", address);
			SendKeys("txt_City_UsernameRecovery", city);
			SelectTextByVisibleText("lst_State_UsernameRecovery", state);
			SendKeys("txt_Zipcode_UsernameRecovery", zipcode);
			driver.findElement(map.getLocator("txt_Zipcode_UsernameRecovery")).sendKeys(Keys.ENTER);

			//JSClick("btn_Submit_UsernameRecovery");
			Thread.sleep(2000);
			WaitForElementPresent("btn_OK_alert_UsernameRecovery", time);
			System.out.println(driver.findElement(map.getLocator("popup_Message_ForgotUsername")).getText());
			strResult = true;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return strResult;
	}

	public Boolean createAnAccount(String first_name, String last_name, String month, String date, String year, String ssn, String add, String city, String zipcode, String state){
		Boolean strResult = false;
		try{
			SendKeys("txt_LegalFirstName_CreateAnAccount", first_name);
			SendKeys("txt_LegalLastName_CreateAnAccount", last_name);
			SelectTextByVisibleText("lst_month_DOB_CreateAnAccount", month);
			SelectTextByVisibleText("lst_day_DOB_CreateAnAccount", date);
			SelectTextByVisibleText("lst_year_DOB_CreateAnAccount", year);
			SendKeys("txt_LastFourDigitsSSN_CreateAnAccount", ssn);
			SendKeys("txt_Address_CreateAnAccount", add);
			SendKeys("txt_City_CreateAnAccount", city);
			SendKeys("txt_ZipCode_CreateAnAccount", zipcode);
			SelectTextByVisibleText("lst_State_CreateAnAccount", state);
			Click("btn_submit_CreateAnAccount");
			WaitForElementPresent("lst_securityQn1_CreateAnAccount", time);
			strResult = true;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return strResult;
	}

	public void openDBconnOracle(String url, String username, String password, String query, String colname1, String colname2){

		try{

			String driverName = "oracle.jdbc.driver.OracleDriver";
			Class.forName(driverName);
			Connection conn = DriverManager.getConnection(url, username, password);
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			while(rs.next()) {
				int EmpId= rs.getInt(colname1);
				String EmpName= rs.getString(colname2);
				System.out.println(EmpId+" "+EmpName);
			}
			conn.close();
		}

		catch(Exception e){
			e.printStackTrace();
		}
	}

}
