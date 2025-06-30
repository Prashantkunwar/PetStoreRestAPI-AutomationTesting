package api.utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	
	@DataProvider(name="Data")
	public String[][] getAllData() throws IOException{
		
		String sheetName = "Sheet1";
		String path = System.getProperty("user.dir")+"\\TestData\\DDT.xlsx";
		ExcelUtils ex = new ExcelUtils(path);
		
		
		int row = ex.getRowCount(sheetName);
		int column = ex.getCellCount(sheetName, 1);
		
		String [][] apiData = new String[row][column];
		
		for (int r= 1; r<=row;r++) {
			for(int c=0;c<column;c++ ) {
				apiData[r-1][c] = ex.getCellData(sheetName, r, c);
			}
		}
		return apiData;	
	}
	
	@DataProvider(name="UserNames")
	public String [][] getUserNames() throws IOException{
		
		String sheetName = "Sheet1";
		String path = System.getProperty("user.dir")+"\\TestData\\DDT.xlsx";
		ExcelUtils ex = new ExcelUtils(path);
		
		int row = ex.getRowCount(sheetName);
		
		String [][] userName = new String [row][1];
		
		for (int i=1;i<=row;i++) {
			
				userName[i-1][0]= ex.getCellData(sheetName, i, 1);
		}
		return userName;	
		
	}

}
