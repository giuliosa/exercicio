package br.aesoexercicio1.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	public Connection getConnection() {
		try {
			return DriverManager.getConnection("jdbc:mysql://localhost/exercicio",
					"root", "giulio93");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
