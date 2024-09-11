package br.com.lbomfim.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Lucas Bomfim 
 */

public class ConexaoJDBC {

	private static Connection conexao;

	private ConexaoJDBC(Connection connection) {
		
	}

	public static Connection getConnection() throws SQLException {
		if (conexao == null) {
			conexao = abrirConexao();
			return conexao;
		} else if (conexao.isClosed()) {
			conexao = abrirConexao();
			return conexao;
		}
		return conexao;
	}

	private static Connection abrirConexao() {
		try {
			return DriverManager.getConnection("jdbc:postgresql://localhost:5432/vendas_online_2", "postgres", "saun@qualifiedreb1rth");
		} catch (Exception erro) {
			throw new RuntimeException(erro);
		}
	}

}
