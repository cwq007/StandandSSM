package com.ninemax.accessoracle;

import org.junit.Test;

import com.ninemax.acceessoracle.DBConnector;

public class TestDriver {

	@Test
	public void testConnectOracle() {
		DBConnector.loadProperties("oracle");
		System.out.println(DBConnector.getConnection());
	}
	
	@Test
	public void testConnectAccess() {
		DBConnector.loadProperties("access");
		System.out.println(DBConnector.getConnection());
	}
		
}
