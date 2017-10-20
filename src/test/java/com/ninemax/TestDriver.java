package com.ninemax;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFComment;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellRangeAddress;
import org.junit.Test;

public class TestDriver {

	private Workbook wb;
	private Sheet sheet;
	private Row row;

	public void readAndPrintDataExcelData(InputStream in) {
		try {
			wb = WorkbookFactory.create(in);
		} catch (Exception e1) {

		}

		sheet = wb.getSheetAt(0);
		row = sheet.getRow(0);
		int rowNum = sheet.getLastRowNum();
		int colNum = row.getPhysicalNumberOfCells();

		for (int j = 0; j <= rowNum; j++) {
			row = sheet.getRow(j);
			for (int i = 0; i < colNum; i++) {
				System.out.print(row.getCell(i).getStringCellValue() + "\t");
			}
			System.out.println();
		}

		try {
			if (in != null)
				in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void exportExcelWithNewCol(InputStream in, OutputStream out) {
		try {
			wb = WorkbookFactory.create(in);
		} catch (Exception e1) {
		}

		sheet = wb.getSheetAt(0);
		row = sheet.getRow(0);
		int rowNum = sheet.getLastRowNum();
		int colNum = row.getPhysicalNumberOfCells();

		CellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);

		for (int j = 0; j <= rowNum; j++) {
			row = sheet.getRow(j);
			Cell cell = row.createCell(colNum);
			if (j == 0) {
				cell.setCellValue("�����ֶ�");
			} else {
				cell.setCellValue("xxx");
			}
		}

		try {
			wb.write(out);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void createASimpleExcel(OutputStream out) {
		wb = new HSSFWorkbook();
		Sheet sheet = wb.createSheet("��һ�¹�����");

		Row row = sheet.createRow(0);
		row.createCell(0).setCellValue("���");
		row.createCell(1).setCellValue("���");
		row.createCell(2).setCellValue("ʱ��");
		row.createCell(3).setCellValue("�۸�");

		Row row2 = sheet.createRow(1);
		row2.createCell(0).setCellValue(1);
		row2.createCell(1).setCellValue("xxx");

		Cell timeCell = row2.createCell(2);
		CellStyle timeCellStyle = wb.createCellStyle();
		timeCellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy h:mm"));
		timeCell.setCellStyle(timeCellStyle);
		timeCell.setCellValue(new Date());

		Cell numbericCell = row2.createCell(3);
		CellStyle numbericCellStyle = wb.createCellStyle();
		numbericCellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.00"));
		numbericCell.setCellStyle(numbericCellStyle);
		numbericCell.setCellValue(1.3525487);

		// 创建标注
		HSSFPatriarch patr = (HSSFPatriarch) sheet.createDrawingPatriarch();
		HSSFClientAnchor createAnchor = patr.createAnchor(0, 0, 0, 0, 5, 1, 8, 3);
		HSSFComment comment = patr.createCellComment(createAnchor);
		comment.setAuthor("cwq");
		comment.setVisible(true);
		comment.setString(new HSSFRichTextString("��ע���䣡"));
		timeCell.setCellComment(comment);

		Row row3 = sheet.createRow(2);
		Cell mergeCell = row3.createCell(0);
		CellStyle mergeStyle = wb.createCellStyle();
		mergeCell.setCellValue("�ϲ���");
		CellRangeAddress region = new CellRangeAddress(2, 3, 0, 3);
		sheet.addMergedRegion(region);
		mergeStyle.setAlignment(CellStyle.ALIGN_CENTER);
		mergeStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		mergeCell.setCellStyle(mergeStyle);

		Row row4 = sheet.createRow(4);
		Cell specialCell = row4.createCell(0);
		CellStyle specialStyle = wb.createCellStyle();
		specialStyle.setBorderTop(HSSFCellStyle.BORDER_DASHED);
		specialStyle.setTopBorderColor(HSSFColor.GREEN.index);
		specialStyle.setBorderBottom(HSSFCellStyle.BORDER_DOTTED);
		specialStyle.setBottomBorderColor(HSSFColor.PINK.index);
		specialCell.setCellStyle(specialStyle);
		specialCell.setCellValue("xxx");

		try {
			wb.write(out);
			out.flush();
			out.close();
		} catch (IOException e) {

		}

	}

	@Test
	public void testReadAndPrintExcelData() {
		TestDriver testDriver = new TestDriver();
		InputStream in = null;
		String excelFileName = "C:\\Users\\Administrator\\Desktop\\chen\\�й�ר�������ר����ơ�ժҪ����Ȩ��Ҫ��.xlsx";

		try {
			in = new FileInputStream(excelFileName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		testDriver.readAndPrintDataExcelData(in);
	}

	@Test
	public void testExportExcelWithNewCol() {
		TestDriver testDriver = new TestDriver();
		InputStream in = null;
		OutputStream out = null;

		String excelFileName = "C:\\Users\\Administrator\\Desktop\\chen\\�й�ר�������ר����ơ�ժҪ����Ȩ��Ҫ��.xlsx";
		String excelFileName1 = "C:\\Users\\Administrator\\Desktop\\chen\\aa.xlsx";

		try {
			in = new FileInputStream(excelFileName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		try {
			out = new FileOutputStream(excelFileName1);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		testDriver.exportExcelWithNewCol(in, out);
	}

	@Test
	public void testCreateASimpleExcel() {
		TestDriver testDriver = new TestDriver();
		try {
			FileOutputStream out = new FileOutputStream("D:\\a.xls");
			testDriver.createASimpleExcel(out);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
