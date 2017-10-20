package com.ninemax.ssmmodule.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtils {

	private static Workbook loadExcel(InputStream in) {
		Workbook wb = null;
		try {
			wb = WorkbookFactory.create(in);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return wb;
	}
	
	// 把excel表里面的数据读取到map集合中
	public static Map<String, List<String>> readDataToMap(InputStream in) {
		Workbook wb = loadExcel(in);
		Sheet sheet = wb.getSheetAt(0);

		Map<String, List<String>> excelData = new HashMap<String, List<String>>();
		List<String> keyList = new ArrayList<String>();

		for (Cell cell : sheet.getRow(0)) {
			keyList.add(cell.getStringCellValue());
			excelData.put(cell.getStringCellValue(), new ArrayList<String>());
		}
		for (Row row : sheet) {
			if (row.getRowNum() == 0) continue;
			
			for (Cell cell : row) {
				if (cell.getColumnIndex() > 1) { //针对    英文期刊论文（请标引标题、关键词、摘要）.xlsx文件     格式的特点
					break;
				} else {
					List<String> valList = excelData.get(keyList.get(cell.getColumnIndex()));
					valList.add(cell.getStringCellValue());
				}
			}
		}

		return excelData;
	}
	
	
	private static Workbook wb;
	private static Integer colNum;
	private static AtomicInteger count = new AtomicInteger(1);
	// 在当前excel表中添加一个列再将其导出到原来的文件中
	public synchronized static void exportDataToExcel(int index, String data, String fileName) {
		try {
			if (wb == null) wb = loadExcel(new FileInputStream(fileName));
		} catch (Exception e1) {
		}
		
		Sheet sheet = wb.getSheetAt(0);
		Row row = sheet.getRow(0);
		int rowNum = sheet.getLastRowNum();
		if (colNum == null) colNum = row.getPhysicalNumberOfCells();

		CellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		
		if (row.getCell(colNum) == null) {
			row.createCell(colNum).setCellValue("结果集");
		} 
		
		row = sheet.getRow(index);
		Cell cell = row.createCell(colNum);
		
		if (! data.equals("[]")) {
			cell.setCellValue(data.replace("[", "").replace("]", "").replace("\"", ""));
		} else {
			cell.setCellValue("无结果集");
		}
		System.out.println("====" + index);
		if (count.getAndIncrement() == rowNum) {
			System.out.println("================");
			try {
				FileOutputStream out = new FileOutputStream(fileName);
				wb.write(out);
				out.flush();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			} 
		}
	}

}
