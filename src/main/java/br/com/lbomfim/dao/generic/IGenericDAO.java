package br.com.lbomfim.dao.generic;

import java.util.List;

/**
 * @author Lucas Bomfim 
 * @param <T>
 */

public interface IGenericDAO<T> {
	
	public Integer cadastrar(T entidade) throws Exception;
	
	public T consultar(Long id) throws Exception;
	
	public Integer atualizar(T entidade) throws Exception;
	
	public List<T> buscarTudo() throws Exception;
	
	public Integer excluir(Long id) throws Exception;

}
