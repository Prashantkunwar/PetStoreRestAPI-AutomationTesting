package api.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
	
	
	public static FileInputStream fi;
	public static FileOutputStream fo;
	public static XSSFWorkbook workbook;
	public static XSSFSheet sheet;
	public static XSSFRow row;
	public static XSSFCell cell;
	public static CellStyle style;
	String path;
	
	
	public ExcelUtils(String path) {
		this.path= path;	
		}
	
	
	public int getRowCount(String xlsheet) throws IOException {
		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(xlsheet);
		int rowcount = sheet.getLastRowNum();
		workbook.close();
		fi.close();
		return rowcount;
		
	}
	
	
	public int getCellCount(String xlsheet, int rownum ) throws IOException {
		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(xlsheet);
		row = sheet.getRow(rownum);
		int cellcount =	row.getLastCellNum();
		workbook.close();
		fi.close();
		return cellcount;
	}
	
	public  String getCellData(String xlsheet, int rownum, int colnum) throws IOException {
		
		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(xlsheet);
		row = sheet.getRow(rownum);
		cell = row.getCell(colnum);
		String data;
		try {
			//data = cell.toString();
			
			DataFormatter formatter = new DataFormatter();
			data = formatter.formatCellValue(cell); //Returns the formatted cell value of a cell as a String regardless it's data type
			
		}
		catch(Exception e){
			
			data = "";
			  
		}
		workbook.close();
		fi.close();
		return data;
		
	}
	
	public void setCellData( String sheetName, int rownum, int column, String data) throws IOException {
		
		File xlfile = new File(path);
		
		if(!xlfile.exists()) {   //If File not exists it creates a new file 
			workbook = new XSSFWorkbook();
			fo= new FileOutputStream(path);
			workbook.write(fo);
			
		}
		
		fi = new FileInputStream(path);
		
	
		workbook = new XSSFWorkbook(fi);
		if (workbook.getSheetIndex(sheetName)== -1) {  //If sheet doesnot exists it creates a new file
			workbook.createSheet();
			sheet = workbook.getSheet(sheetName);
		}
			
		
		sheet = workbook.getSheet(sheetName);
		if(sheet.getRow(rownum)==null)          //If row doesnot exists then creates a new Row
			sheet.createRow(rownum);
		row = sheet.getRow(rownum);
		
		
		
		cell = row.createCell(column);
		cell.setCellValue(data);
		fo = new FileOutputStream(path);
		workbook.write(fo);
		workbook.close();
		fo.close();
		fi.close();
	}
	
	public void fillGreenColor(String xlfile, String xlsheet, int rownum, int colnum) throws IOException {
		fi= new FileInputStream(xlfile);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(xlsheet);
		row = sheet.getRow(rownum);
		cell = row.getCell(colnum);
		
		style = workbook.createCellStyle();
			
		style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		cell.setCellStyle(style);
		fo = new FileOutputStream(xlfile);
		workbook.write(fo);
		workbook.close();
		fi.close();
		fo.close();
		
		
	}
	
	public void fillRedColor(String xlfile, String xlsheet, int rownum, int colnum) throws IOException {
		fi= new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(xlsheet);
		row = sheet.getRow(rownum);
		cell = row.getCell(colnum);
		
		style = workbook.createCellStyle();
		
		style.setFillForegroundColor(IndexedColors.RED.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		cell.setCellStyle(style);
		fo = new FileOutputStream(xlfile);
		workbook.write(fo);
		workbook.close();
		fi.close();
		fo.close();
		
		
	}
	
	
	
	
	
}
