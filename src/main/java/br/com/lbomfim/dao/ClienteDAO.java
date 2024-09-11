package br.com.lbomfim.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.lbomfim.dao.generic.IGenericDAO;
import br.com.lbomfim.dao.jdbc.ConexaoJDBC;
import br.com.lbomfim.domain.Cliente;

/**
 * @author Lucas Bomfim 
 */

public class ClienteDAO implements IGenericDAO<Cliente> {

	@Override
	public Integer cadastrar(Cliente cliente) throws Exception {
		Connection conexao = null;
		PreparedStatement declararSQL = null;
		ResultSet resultado = null;
		
		try {
			conexao = ConexaoJDBC.getConnection();
			String sql = "INSERT INTO TB_CLIENTE (NOME, CPF) VALUES (?, ?) RETURNING ID";
			declararSQL = conexao.prepareStatement(sql);
			declararSQL.setString(1, cliente.getNome());
			declararSQL.setLong(2, cliente.getCpf());
			resultado = declararSQL.executeQuery();
			
			if (resultado.next()) {
				cliente.setId(resultado.getLong("ID"));
			}
			
			return 1;
			
		} catch (Exception erro) {
			throw new Exception("Erro ao cadastrar o cliente: " + cliente.getNome(), erro);
			
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
	public Cliente consultar(Long id) throws Exception {
		Connection conexao = null;
		PreparedStatement declararSQL = null;
		Cliente cliente = null;
		ResultSet resultado = null;
		
		try {
			conexao = ConexaoJDBC.getConnection();
			String sql = "SELECT * FROM TB_CLIENTE WHERE ID = ?";
			declararSQL = conexao.prepareStatement(sql);
			declararSQL.setLong(1, id);
			resultado = declararSQL.executeQuery();
			
			if (resultado.next()) {
				cliente = new Cliente();
				cliente.setId(resultado.getLong("id"));
				cliente.setNome(resultado.getString("nome"));
				cliente.setCpf(resultado.getLong("cpf"));
			} 
			
			return cliente;
			
		} catch (Exception erro) {
			throw new Exception("Erro ao consultar o cliente com ID: " + id, erro);
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
	public Integer atualizar(Cliente cliente) throws Exception {
	    Connection conexao = null;
	    PreparedStatement declararSQL = null;
	    
	    try {
	        conexao = ConexaoJDBC.getConnection();
	        String sql = "UPDATE TB_CLIENTE SET NOME = ? WHERE ID = ?";
	        declararSQL = conexao.prepareStatement(sql);
	        declararSQL.setString(1, cliente.getNome()); 
	        declararSQL.setLong(2, cliente.getId());
	        
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
	public List<Cliente> buscarTudo() throws Exception {
		Connection conexao = null;
		PreparedStatement declararSQL = null;
		List<Cliente> lista = new ArrayList<>();
		ResultSet resultado = null;
		
		try {
			conexao = ConexaoJDBC.getConnection();
			String sql = "SELECT * FROM TB_CLIENTE";
			declararSQL = conexao.prepareStatement(sql);
			resultado = declararSQL.executeQuery();
			
			while (resultado.next()) {
				Cliente cliente = new Cliente();
				cliente.setId(resultado.getLong("id"));
				cliente.setNome(resultado.getString("nome"));
				cliente.setCpf(resultado.getLong("cpf"));
				lista.add(cliente);
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
			String sql = "DELETE FROM TB_CLIENTE WHERE ID = ?";
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
