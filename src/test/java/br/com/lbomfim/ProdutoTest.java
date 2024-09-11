package br.com.lbomfim;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import br.com.lbomfim.dao.ProdutoDAO;
import br.com.lbomfim.dao.generic.IGenericDAO;
import br.com.lbomfim.domain.Produto;

/**
 * @author Lucas Bomfim 
 */

public class ProdutoTest {
	
	@Test
	public void CadastrarProduto() throws Exception {
		IGenericDAO<Produto> dao = new ProdutoDAO();
		// CRIAR 
		Produto produto = new Produto();
		produto.setNome("Controle Sony DualSense PS5, Sem Fio, Branco");
		produto.setPreco("R$ 375,06");
				
		// CADASTRAR		
		Integer produtosCadastrados = dao.cadastrar(produto);
		assertTrue(produtosCadastrados == 1);
		
		if (produtosCadastrados != null) {
			System.out.println("----PRODUTO CADASTRADO COM SUCESSO----");
		}
		
		// CONSULTAR
		Produto produtoBD = dao.consultar(produto.getId());
		assertEquals(produto.getId(), produtoBD.getId());
		
		if (produto.getId() == produtoBD.getId()) {
			System.out.println("\n----PRODUTO CONSULTADO COM SUCESSO----");
		}
	}
	
	
	@Test
	public void TesteGeral() throws Exception {
		IGenericDAO<Produto> dao = new ProdutoDAO();
		
		// CRIAR 
		Produto produto = new Produto();
		produto.setNome("Mouse Gamer sem fio Logitech G309");
		produto.setPreco("R$ 449,99");
				
		// CADASTRAR		
		Integer produtosCadastrados = dao.cadastrar(produto);
		assertTrue(produtosCadastrados == 1);
		
		if (produtosCadastrados != null) {
			System.out.println("----PRODUTO CADASTRADO COM SUCESSO----");
		}
		
		// CONSULTAR
		Produto produtoBD = dao.consultar(produto.getId());
		assertEquals(produto.getId(), produtoBD.getId());
		
		if (produto.getId() == produtoBD.getId()) {
			System.out.println("\n----PRODUTO CONSULTADO COM SUCESSO----");
		}
		
		// ATUALIZAR
		produtoBD.setPreco("R$ 499,99");
		Integer produtoAtualizado = dao.atualizar(produtoBD);
		
		if (produtoAtualizado != null) {
			System.out.println("\n----PRODUTO ATUALIZADO COM SUCESSO----\n");
			
			System.out.println("ID " + produto.getId() + ": " +
					produtoBD.getNome() + "\nPreço antigo: " + produto.getPreco() + 
					" | " + 
					"Preço atual: " + produtoBD.getPreco());
		}
		
		// BUSCAR TUDO
		List<Produto> listaDeProdutos = dao.buscarTudo();
		
		System.out.println("\n----PRODUTOS CADASTRADOS NO BANCO----\n");
		
		for (Produto lista : listaDeProdutos) {
			System.out.println("ID: " + lista.getId() + 
					"\nNome: " + lista.getNome() + 
					"\nPreço: " + lista.getPreco() + "\n");
		}
		
		// EXCLUIR
		Integer produtoExcluido = dao.excluir(produtoBD.getId());
		
		if (produtoExcluido != null) {
			System.out.println("\n----PRODUTO DELETADO COM SUCESSO----\n");
			System.out.println("ID " + produtoBD.getId() + ": " + produtoBD.getNome());
		}
	}
}