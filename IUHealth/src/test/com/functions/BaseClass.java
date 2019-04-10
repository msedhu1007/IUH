package test.com.functions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class BaseClass extends TestListenerAdapter {

	public static WebDriver driver = null;
	public static WebDriverWait wait = null;
	public static String browser = "CH"; // IE or FF or CH
	public static String testDataPath = ".//TestData/TestData.xls";
	
	public static int time = 15;
	public static ObjectMap map = null;
	public static String objmapPath = ".//src/main/objectmap.properties";
	public static String baseUrl = null;
	public static String URL =  getExcelCell("IUHealth",1,1);  //"https://stage.myiuhealth.org/";
	public static String Username = "Thursday1234";
	public static String Password = "Passw0rd";
	public static String chromeDriver = ".//chromedriver.exe";
	public static String ieDriver = ".//IEDriverServer.exe";
	public static String ffDriver = ".//geckodriver.exe";
	public static String edgeDriver = ".//MicrosoftWebDriver.exe";
	
	//public static ExtentReports extent;
   
	
	public static void openBrowser(String browser){
		try{
			if(browser.equalsIgnoreCase("IE")){
				System.setProperty("webdriver.ie.driver",".//IEDriverServer.exe");
				driver = new InternetExplorerDriver();
			}
			else if (browser.equalsIgnoreCase("CH")){
				System.setProperty("webdriver.chrome.driver",
						".//chromedriver.exe");
				driver = new ChromeDriver();
			}
			else if(browser.equalsIgnoreCase("FF")){
				System.setProperty("webdriver.gecko.driver", ".//geckodriver.exe");
				driver = new FirefoxDriver();
			}
			else if(browser.equalsIgnoreCase("Edge")){
				System.setProperty("webdriver.edge.driver",".//MicrosoftWebDriver.exe"); //put actual location
				driver = new EdgeDriver();
			}
			driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		}
		catch(Exception e){
			e.printStackTrace();
		}

	}

	@BeforeClass
	public static void launchApp() {
		try {
			ExcelToProperty.ExcelSheetToPropertyFileConvert();
			map = new ObjectMap(objmapPath);
			baseUrl = URL;
			openBrowser(browser);
			openURL(URL);
			Thread.sleep(2000);

			//	reportPass("Launch is successful");
		} catch (Exception E) {
			Reporter.log("Launch has failed");

		}
	}


	public static void openURL(String URL){
		try{
			driver.navigate().to(URL);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	@AfterClass
	public static void tearDown(){
		driver.quit();
	}


	/**************************************************************************************************************/
	/**
	 * Wait for number of windows to be opened
	 * @param numberOfWindows
	 */
	public static void waitForNumberOfWindowsToOpen(final int numberOfWindows) {
		new WebDriverWait(driver, 60) {
		}.until(new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {                        
				return (driver.getWindowHandles().size() == numberOfWindows);
			}
		});
	}

	
	/** isElementPresent method */
	protected static boolean IsElementPresent(String objname) throws Exception {
		try {
			driver.findElement(map.getLocator(objname));
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}
	
	
	/**
	 * waitForElementPresent method (objname = the object name from objectmap
	 * file) , (time is in seconds)
	 * @throws Exception 
	 */
	public static void WaitForElementPresent(String objname, int time) {
		try{
			WebDriverWait newWait = new WebDriverWait(driver, time);
			newWait.until(ExpectedConditions.visibilityOfElementLocated(map.getLocator(objname)));
		}catch(Exception e){
			Assert.fail("timeout error: element '" + objname + "' not present");
		}
	}

	/** report information */
	public static void reportInfo(String message) {
		System.out.println(message);
		Reporter.log(message);
	}

	/** report fail */
	public static void reportFail(String message) {
		System.out.println("Fail: " + message);
		Reporter.log("Fail: " + message);
	}

	public static void reportPass(String message){
		System.err.println(message);
		Reporter.log(message);
	}


	/**
	 * selectTextFromDropdown method (objname = the object name from objectmap
	 * file)
	 */
	public static void SelectTextByValue(String objname, String value) {
		try {
			Select select = new Select(driver.findElement(map
					.getLocator(objname)));
			reportInfo("dropdown '" + objname + "' is selected");

			select.selectByValue(value);
			reportInfo("text '" + value + "' is selected from the dropdown '"
					+ objname + "'");
		} catch (Exception e) {
			reportFail("'" + objname + "' is not present or text '" + value
					+ "' is not selected");
			Assert.fail("'" + objname + "' is not present or text '" + value
					+ "' is not selected");
		}

	}


	/**
	 * selectTextFromDropdown method (objname = the object name from objectmap
	 * file)
	 */
	public static void SelectTextByVisibleText(String objname, String text) {
		try {
			Select select = new Select(driver.findElement(map
					.getLocator(objname)));
			reportInfo("dropdown '" + objname + "' is selected");
			select.selectByVisibleText(text);
			reportInfo("text '" + text + "' is selected from the dropdown '"
					+ objname + "'");
		} catch (Exception e) {
			reportFail("'" + objname + "' is not present or text '" + text
					+ "' is not selected");
			Assert.fail("'" + objname + "' is not present or text '" + text
					+ "' is not selected");
		}

	}


	/**
	 * sendKeys method (objname = the object name from objectmap file) , (value
	 * is the text to enter)
	 * @throws Exception 
	 */
	public static void SendKeys(String objname, String value) {
		try {
			driver.findElement(map.getLocator(objname)).clear();
			driver.findElement(map.getLocator(objname)).sendKeys(value);
			reportInfo("'" + value + "' is entered in '" + objname + "'");
		} catch (Exception e) {
			reportFail("element '" + objname + "' is not displayed");
			Assert.fail("element '" + objname + "' is not displayed");
		}
	}

	
	
	public static void JSClick(String objname){
		try {
			WebElement element = driver.findElement(map.getLocator(objname));
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", element);
		}
		catch(Exception E){
			E.printStackTrace();
		}
	}

	
	/***************************************************************************************************************/
	/**
	 * Load URL
	 * @author Sedhu
	 */
	public static void loadURL() {
		try{
			driver.manage().window().maximize();
	
			driver.get(URL);
		}catch(Exception e){
			reportFail("Unable to load Url '" + baseUrl + "'");
			Assert.fail("Unable to load Url '" + baseUrl + "'");
		}
	}
	

	/**
	 * selectTextFromDropdown method (objname = the object name from objectmap
	 * file)
	 * @throws Exception 
	 */
	public static void SelectTextByIndex(String objname, int index) {
		try {
			Select select = new Select(driver.findElement(map
					.getLocator(objname)));
			reportInfo("dropdown '" + objname + "' is selected");

			select.selectByIndex(index);
			reportInfo("text '" + index + "' is selected from the dropdown '"
					+ objname + "'");
		} catch (Exception e) {
			reportFail("'" + objname + "' is not present or text '" + index
					+ "' is not selected");
			Assert.fail("'" + objname + "' is not present or text '" + index
					+ "' is not selected");
		}

	}


	/**
	 * click method (objname = the object name from objectmap file)
	 * @throws Exception 
	 */
	public static void Click(String objname) {
		try {
			driver.findElement(map.getLocator(objname)).click();
			reportInfo("clicked on '" + objname + "'");
		} catch (Exception e) {
			reportFail("element '" + objname + "' is not displayed");
			Assert.fail("element '" + objname + "' is not displayed");
		}
	}



	/**************************************************************************************************************/
	/**
	 * Read Excel Data
	 * @param Sheet
	 * @param intRow
	 * @param intCol
	 * @return Cell Data
	 * @author Sedhu
	 */
	public static String getExcelCell(String Sheet, int intRow, int intCol){
		try{
			File file = new File(testDataPath);
			FileInputStream inputStream = new FileInputStream(file);
			HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
			HSSFSheet worksheet = workbook.getSheet(Sheet);
			HSSFRow row = worksheet.getRow(intRow);
			HSSFCell cellA1 = row.getCell(intCol);
			return cellA1.toString();
		}catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		return null;
	}

	//--------------------------------------------------------------------------------------------------------------------------
	/**
	 * Get Text
	 * @param Object Name
	 * @throws Exception
	 * @author Sedhu
	 */
	//Function to retrieve the text fom web element
	public static String getText(String objname){
		String strValue = null;
		try{
			strValue = driver.findElement(map.getLocator(objname)).getText();
		}
		catch(Exception E){
			E.printStackTrace();
		}
		return strValue;
	}


	/**************************************************************************************************************/
	/**
	 * Switch to Frame
	 * @param frame index
	 * @throws Exception
	 * @author Sedhu
	 */


	/** Switch Between Frames */
	public static void switchToFrame(int frameNumber) {
		try{
			driver.switchTo().defaultContent();
			driver.switchTo().frame(frameNumber);
		}catch(Exception e){
			reportFail("Frame '" + frameNumber + "' is not found");
			Assert.fail("Frame '" + frameNumber + "' is not found");
		}
	}


	/**************************************************************************************************************/
	/** Switch to SubFrame */
	public static void switchToChildFrame(int frameNumber) {
		try{
			driver.switchTo().frame(frameNumber);
		}catch(Exception e){
			reportFail("Childframe '" + frameNumber + "' is not found");
			Assert.fail("Childframe '" + frameNumber + "' is not found");
		}
	}



	/**************************************************************************************************************/
	/** Iterate first column in Excel 
	 *   @author Sedhu
	 * */
	
	public static int excelIterate(String classname){
		int ctr =0;
		try{
			InputStream inp = new FileInputStream(".//TestData/TestData.xls");
			Workbook wb =  new HSSFWorkbook(inp);
			Sheet sheet = wb.getSheetAt(0);
			Row row = null;
			Cell cell = null;
			boolean isNull = false;
			do{
				try{
					row = sheet.getRow(ctr);
					cell = row.getCell(0);
					if(cell.toString().equalsIgnoreCase(classname)){
						System.out.println(cell.toString());
						return ctr+1;
					}
					ctr++;
				} catch(Exception e) {
					isNull = true;
				}
			}while(isNull!=true);
			inp.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return ctr-2;
	}

	
	
	/**************************************************************************************************************/
	/**
	 * Read Excel Data
	 * @param Sheet
	 * @param intRow
	 * @param intCol
	 * @return Cell Data
	 * @author Sedhu
	 */
	public static String getExcelValue(String Sheet, int intRow, String colName){
		try{
			File file = new File(testDataPath);
			FileInputStream inputStream = new FileInputStream(file);
			HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
			HSSFSheet worksheet = workbook.getSheet(Sheet);
			HSSFRow row = worksheet.getRow(intRow);
			Iterator<Cell> cells = row.cellIterator();
			while(cells.hasNext()){
				if(cells.next().getStringCellValue().equalsIgnoreCase(colName)){
					System.err.println("test success");
				}
			}
		//	HSSFCell cellA1 = row.getCell(intCol);
		//	return cellA1.toString();
		}catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		return null;
	}

	
	 /*********************************************************************************************************************/
	/**************************************************************************************************************/
    /**
    * Set Value using Javascript
    * @param set value in text field
    * @param object name, data to be entered
    * @author Sedhu
    */	
	
	public static synchronized void setValue(String objname, String value) {
			// Object lock1 = new Object();
			
			try {
				// synchronized(lock1){
				
				driver.findElement(map.getLocator(objname)).clear();
				((JavascriptExecutor) driver).executeScript(
						"arguments[0].value = arguments[1]",
						driver.findElement(map.getLocator(objname)), value);
				// }
				reportInfo("'" + value + "' is entered in '" + objname + "'");

			} catch (Exception e) {

				reportFail("element '" + objname + "' is not displayed");
				Assert.fail("element '" + objname + "' is not displayed");
			}
		}

		
	    /**************************************************************************************************************/
	    /**
	    * Click Link By Index Number
	    * @param linkText (Text to Click)
	    * @param index (Index Number of the Link)
	    * @author Sedhu
	    */
	    public static void clickLinkByIndex(String linkText, int index){
	           try{
	                  WebDriverWait myWait = new WebDriverWait(driver, 60);
	                  myWait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(linkText)));
	                  driver.findElement(By.xpath("(//a[contains(text(),'"+linkText+"')])["+index+"]")).click();
	                  reportInfo("clicked on '" + linkText + "'");
	           }catch(Exception e){
	                  reportFail("element '" + linkText + "' is not displayed");
	                  Assert.fail("element '" + linkText + "' is not displayed");
	           }
	    }
	    

	    
	    /**************************************************************************************************************/
	    /**
	    * Write to Excel's specified cell
	    * @param Sheet
	    * @param intRow
	    * @param intCell
	    * @param strSetCell
	    * @return Entered string
	    * @author Sedhu
	    */
	    public static String setExcelCell(String Sheet,int intRow,int intCell,String strSetCell)
	    {
	           try {
	                  File file = new File(testDataPath);
	                  FileInputStream inputStream = new FileInputStream(file);

	                  HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
	                  HSSFSheet worksheet = workbook.getSheet(Sheet);
	                  HSSFRow row = worksheet.getRow(intRow);
	                  HSSFCell cellA1 = row.getCell(intCell);
	                  cellA1.setCellValue(strSetCell);
	                  inputStream.close();
	                  FileOutputStream outFile = new FileOutputStream(new File(testDataPath));
	                  workbook.write(outFile);
	                  outFile.close();
	                  return cellA1.toString();
	           }
	           catch (FileNotFoundException e) 
	           {
	                  e.printStackTrace();
	           } 
	           catch (IOException e) 
	           {
	                  e.printStackTrace();
	           }
	           return null;
	    }

		/**************************************************************************************************************/
	    /**
	    * Switch to second window
	    * @param firstWinHandle (Handle of Main Window)
	    * @author Sedhu
	    */
	    public static void switchToSecondWindow(String firstWinHandle){
	           waitForNumberOfWindowsToOpen(2);
	           Set<String> handles = driver.getWindowHandles();
	           handles.remove(firstWinHandle);
	           String winHandle = handles.iterator().next();
	           if(winHandle != firstWinHandle){
	                  String secondWinHandle = winHandle;
	                  driver.switchTo().window(secondWinHandle);
	           }
	    }
	
	    
	    /**************************************************************************************************************/
		/**
		 * Generate Four Digit Random Number
		 * @return Four Digit Number
		 * @author Sedhu
		 */
		public static String RandomNumber() {
			double number = Math.random();
			number = number * 10000;
			int Number = (int) (number);

			return "" + Number;
		}
		
		
		
	 }


