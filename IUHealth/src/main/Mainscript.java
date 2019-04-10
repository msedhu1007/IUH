package main;

import net.randomsync.testng.excel.ExcelTestNGRunner;

public class Mainscript {
	
		
	public static void main(String[] args) {
  				
		try {
			ExcelTestNGRunner runner = new ExcelTestNGRunner(".//TestSuite.xls");
			
				runner.run();
            }
			catch(Exception E1)
            {
                  System.out.println("The Main Test Suite File is not been found. Please check the path of the file and Re-Execute");
                  System.out.println(E1.getMessage());
            }
	}
}
