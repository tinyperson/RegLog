package com.reglog.utils;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JDBCUtils {

	private static DataSource datasource = new ComboPooledDataSource();

	public static DataSource getDataSource() {
		return datasource;
	}

	public static Connection getConnection() throws SQLException {
		return datasource.getConnection();
	}

}
