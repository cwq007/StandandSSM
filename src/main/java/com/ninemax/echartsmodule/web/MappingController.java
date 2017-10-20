package com.ninemax.echartsmodule.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/echarts")
public class MappingController {

	@RequestMapping("/testecharts.html")
	public String testecharts() {
		return "echartsmodule/test_echarts";
	}

}