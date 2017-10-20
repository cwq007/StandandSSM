package com.ninemax.webservicemodule.service;

import javax.jws.WebService;

@WebService
public interface MyWebService {
	
	Integer getRowCount(String tableName);
	
}
