package br.com.lbomfim.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.lbomfim.dao.generic.IGenericDAO;
import br.com.lbomfim.dao.jdbc.ConexaoJDBC;
import br.com.lbomfim.domain.Produto;

/**
 * @author Lucas Bomfim 
 */

public class ProdutoDAO implements IGenericDAO<Produto> {

	@Override
	public Integer cadastrar(Produto produto) throws Exception {
		Connection conexao = null;
		PreparedStatement declararSQL = null;
		ResultSet resultado = null;
		
		try {
			conexao = ConexaoJDBC.getConnection();
			String sql = "INSERT INTO TB_PRODUTO (NOME, PRECO) VALUES (?,?) RETURNING ID";
			declararSQL = conexao.prepareStatement(sql);
			declararSQL.setString(1, produto.getNome());
			declararSQL.setString(2, produto.getPreco());
			resultado = declararSQL.executeQuery();
			
			if (resultado.next()) {
				produto.setId(resultado.getLong("ID"));
			}
			
			return 1;
			
		} catch (Exception erro) {
			throw new Exception("Erro ao cadastrar o produto: " + produto, erro);
			
		} finally {
			if (declararSQL != null && !declararSQL.isClosed()) {
		        declararSQL.close();
		    }
		    if (conexao != null && !conexao.isClosed()) {
		        conexao.close();
		    }
		}
	}

	@Override
	public Produto consultar(Long id) throws Exception {
		Connection conexao = null;
		PreparedStatement declararSQL = null;
		ResultSet resultado = null;
		Produto produto = null;
		
		try {
			conexao = ConexaoJDBC.getConnection();
			String sql = "SELECT * FROM TB_PRODUTO WHERE ID = ?";
			declararSQL = conexao.prepareStatement(sql);
			declararSQL.setLong(1, id);
			resultado = declararSQL.executeQuery();
			
			if (resultado.next()) {
				produto = new Produto();
				produto.setId(resultado.getLong("id"));
				produto.setNome(resultado.getString("nome"));
				produto.setPreco(resultado.getString("preco"));
			} 
			
			return produto;
			
		} catch (Exception erro) {
			throw erro;
			
		} finally {
			if (resultado != null && !resultado.isClosed()) {
		        resultado.close();
		    }
		    if (declararSQL != null && !declararSQL.isClosed()) {
		        declararSQL.close();
		    }
		    if (conexao != null && !conexao.isClosed()) {
		        conexao.close();
		    }
		}
	}

	@Override
	public Integer atualizar(Produto produto) throws Exception {
		Connection conexao = null;
		PreparedStatement declararSQL = null;
		
		try {
			conexao = ConexaoJDBC.getConnection();
			String sql = "UPDATE TB_PRODUTO SET NOME = ? WHERE ID = ?";
			declararSQL = conexao.prepareStatement(sql);
			declararSQL.setString(1, produto.getNome());
			declararSQL.setLong(2, produto.getId());
			
			return declararSQL.executeUpdate();
			
		} catch (Exception erro) {
			throw erro;
			
		} finally {
			if (declararSQL != null && !declararSQL.isClosed()) {
		        declararSQL.close();
		    }
		    if (conexao != null && !conexao.isClosed()) {
		        conexao.close();
		    }
		}
	}

	@Override
	public List<Produto> buscarTudo() throws Exception {
		Connection conexao = null;
		PreparedStatement declararSQL = null;
		List<Produto> lista = new ArrayList<>();
		ResultSet resultado = null;
		
		try {
			conexao = ConexaoJDBC.getConnection();
			String sql = "SELECT * FROM TB_PRODUTO";
			declararSQL = conexao.prepareStatement(sql);
			resultado = declararSQL.executeQuery();
			
			while (resultado.next()) {
				Produto produto = new Produto();
				produto.setId(resultado.getLong("id"));
				produto.setNome(resultado.getString("nome"));
				produto.setPreco(resultado.getString("preco"));
				lista.add(produto);
			} 
			
			return lista;
			
		} catch (Exception erro) {
			throw erro;
			
		} finally {
			if (resultado != null && !resultado.isClosed()) {
		        resultado.close();
		    }
		    if (declararSQL != null && !declararSQL.isClosed()) {
		        declararSQL.close();
		    }
		    if (conexao != null && !conexao.isClosed()) {
		        conexao.close();
		    }
		}
	}

	@Override
	public Integer excluir(Long id) throws Exception {
		Connection conexao = null;
		PreparedStatement declararSQL = null;
		
		try {
			conexao = ConexaoJDBC.getConnection();
			String sql = "DELETE FROM TB_PRODUTO WHERE ID = ?";
			declararSQL = conexao.prepareStatement(sql);
			declararSQL.setLong(1, id);
			
			return declararSQL.executeUpdate();
			
		} catch (Exception erro) {
			throw erro;
			
		} finally {
			if (declararSQL != null && !declararSQL.isClosed()) {
		        declararSQL.close();
		    }
		    if (conexao != null && !conexao.isClosed()) {
		        conexao.close();
		    }
		}
	}

}
