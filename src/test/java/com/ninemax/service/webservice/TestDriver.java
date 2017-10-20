package com.ninemax.service.webservice;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ninemax.webservicemodule.service.MyWebService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:/applicationContext.xml" })
public class TestDriver {

	@Resource
	private MyWebService myWebService;
	
	@Test
	public void testGetRowCount() {
		System.out.println("==============================");
		System.out.println(myWebService.getRowCount("city"));
		System.out.println("==============================");
	}

}
