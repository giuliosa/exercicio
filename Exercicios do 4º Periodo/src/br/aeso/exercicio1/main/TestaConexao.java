package br.aeso.exercicio1.main;

import java.sql.Connection;
import java.sql.SQLException;

import br.aesoexercicio1.jdbc.ConnectionFactory;

public class TestaConexao {	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection con = new ConnectionFactory().getConnection();
		System.out.println("Conexão aberta!");
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
