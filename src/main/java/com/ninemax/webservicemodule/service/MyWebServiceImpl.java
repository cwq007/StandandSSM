package com.ninemax.webservicemodule.service;

import org.springframework.stereotype.Service;

@Service
public class MyWebServiceImpl implements MyWebService {

	public Integer getRowCount(String tableName) {
		Integer count = 0;

		if ("".equals(tableName)) {
			return count;
		}

		return count;
	}

}
