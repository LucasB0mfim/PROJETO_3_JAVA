package br.com.lbomfim.dao;

import br.com.lbomfim.domain.Venda;

/**
 * @author Lucas Bomfim 
 */

public interface IVendaDAO {

	public Integer cadastrar(Venda venda) throws Exception;
	
	public Venda consultar(Long id) throws Exception;
	
}
