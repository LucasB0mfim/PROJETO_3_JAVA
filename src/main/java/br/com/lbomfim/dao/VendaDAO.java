package br.com.lbomfim.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import br.com.lbomfim.dao.jdbc.ConexaoJDBC;
import br.com.lbomfim.domain.Produto;
import br.com.lbomfim.domain.Venda;

/**
 * @author Lucas Bomfim 
 */

public class VendaDAO implements IVendaDAO {

	@Override
	public Integer cadastrar(Venda venda) throws Exception {
	    Connection conexao = null;
	    PreparedStatement declararSQL = null;
	    ResultSet resultado = null;
	    Long idVenda = null;
	    
	    try {
	        conexao = ConexaoJDBC.getConnection();
	        
	        // Inserir a venda na tabela TB_VENDA
	        String sql = "INSERT INTO TB_VENDA (ID_CLIENTE, DATA_HORA) VALUES (?, CURRENT_TIMESTAMP) RETURNING ID";
	        declararSQL = conexao.prepareStatement(sql);
	        declararSQL.setLong(1, venda.getCliente().getId());
	        resultado = declararSQL.executeQuery();
	        
	        if (resultado.next()) {
	            idVenda = resultado.getLong("ID");
	            venda.setId(idVenda);
	        }
	        
	        // Inserir produtos na tabela TB_VENDA_PRODUTO
	        String sqlProduto = "INSERT INTO TB_VENDA_PRODUTO (ID_VENDA, ID_PRODUTO) VALUES (?, ?)";
	        declararSQL = conexao.prepareStatement(sqlProduto);

	        for (Produto produto : venda.getProdutos()) {
	            declararSQL.setLong(1, idVenda);
	            declararSQL.setLong(2, produto.getId());
	            declararSQL.addBatch();
	        }

	        declararSQL.executeBatch();

	        return 1;
	        
	    } catch (Exception erro) {
	        throw new Exception("Erro ao cadastrar a venda: " + venda, erro);
	        
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
	public Venda consultar(Long id) throws Exception {
		Connection conexao = null;
		PreparedStatement declararSQL = null;
		ResultSet resultado = null;
		Venda venda = null;
		
		try {
			conexao = ConexaoJDBC.getConnection();
			String sql = "SELECT * FROM TB_VENDA WHERE ID = ?";
			declararSQL = conexao.prepareStatement(sql);
			declararSQL.setLong(1, id);
			resultado = declararSQL.executeQuery();
			
			if (resultado.next()) {
				venda = new Venda();
				venda.setId(resultado.getLong("id"));
				return venda;
			} 
			
		} catch (Exception erro) {
			throw new Exception("Erro ao consultar a venda com id: " + id, erro);
			
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
		
		return null;
	}

}
