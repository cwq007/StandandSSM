package com.ninemax.ssmmodule.web.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ninemax.ssmmodule.utils.ExcelUtils;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/handlerdata")
public class HandlerDataController {
	private final String fileName = "C:\\Users\\Administrator\\Desktop\\chen\\待标引文献\\待标引文献\\美国专利（请标引专利名称、摘要、主权利要求）.xlsx";
//	private final String fileName = "C:\\Users\\Administrator\\Desktop\\chen\\待标引文献\\待标引文献\\英文期刊论文（请标引标题、关键词、摘要）.xlsx";
//	private final String fileName = "C:\\Users\\Administrator\\Desktop\\chen\\待标引文献\\待标引文献\\中国专利（请标引专利名称、摘要、主权利要求）.xlsx";
//	private final String fileName = "C:\\Users\\Administrator\\Desktop\\chen\\待标引文献\\待标引文献\\中文期刊论文（请标引标题、关键词、摘要）.xlsx";
	
	@RequestMapping("/taggingtest.html")
	public String taggingtest() {
		return "ssmmodule/test";
	}
	
	@RequestMapping("/taggingtest2.html")
	public String taggingtest2() {
		return "ssmmodule/test2";
	}
	
	@RequestMapping("/getDataParams")
	public void getDataParams(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		Map<String, List<String>> storeData = ExcelUtils.readDataToMap(new FileInputStream(fileName));
		responseData(resp, storeData, "application/json");
	}
	
	@RequestMapping("/exportDataToExcel")
	public void exportDataToExcel(HttpServletRequest req, HttpServletResponse resp, 
				 Integer index, String data) throws Exception {
		ExcelUtils.exportDataToExcel(index, data, fileName);
		responseData(resp, "{\"result\": \"success\"}", "text/json");
	}
	
	private void responseData(HttpServletResponse resp, Object data, String contentType) throws IOException {
		resp.setContentType(contentType + "; charset=utf-8");
		PrintWriter writer = resp.getWriter();
		writer.println(JSONObject.fromObject(data).toString());
	}
	
}
