package com.autosale.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.commons.dbcp2.BasicDataSource;
import com.autosale.model.model;

public class DB {

	private String url = "jdbc:mysql://localhost:3306/kickstarter";
	private String user = "root";
	private String password = "12345";

	public void getResult(String name, int modelId) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try (Connection con = getConnection()) {
			ps = con.prepareStatement("select * FROM models WHERE  name = 1 or ? is null and modelId = ? or ? is null " );
			name= null;
			ps.setString(1,name);
			ps.setString(2,name);
			ps.setInt(3,modelId);
			ps.setInt(4,modelId);
			System.out.println(ps.toString());
			rs = ps.executeQuery();
			while (rs.next()) {
				model project = new model();
				project.setId(rs.getInt("modelId"));
				project.setName(rs.getString("name"));
				project.setHight(rs.getInt("hight"));
				project.setAge(rs.getInt("age"));
				System.out.print(project.toString());
			}

		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}

	}

	public Connection getConnection() {
		BasicDataSource datasource = null;
		try {
			// Class.forName("org.apache.commons.dbcp2.BasicDataSource");
			Class.forName("com.mysql.jdbc.Driver");
			datasource = new BasicDataSource();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}

		Connection conection = null;

		datasource.setDriverClassName("com.mysql.jdbc.Driver");
		datasource.setUrl(url);
		datasource.setUsername(user);
		datasource.setPassword(password);
		try {
			conection = datasource.getConnection();
		} catch (SQLException e) {
			System.out.println("Connection problem " + e.getMessage());
		}
		return conection;

	}

}
