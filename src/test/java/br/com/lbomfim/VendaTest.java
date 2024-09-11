package br.com.lbomfim;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import br.com.lbomfim.dao.IVendaDAO;
import br.com.lbomfim.dao.VendaDAO;
import br.com.lbomfim.domain.Cliente;
import br.com.lbomfim.domain.Produto;
import br.com.lbomfim.domain.Venda;

/**
 * @author Lucas Bomfim 
 */

public class VendaTest {
	
	@Test
	public void cadastrarVenda() throws Exception {
	    IVendaDAO dao = new VendaDAO();
	    Venda venda = new Venda();
	    
	    // CLIENTE QUE VAI COMPRAR
	    Cliente cliente = new Cliente();
	    cliente.setId(32L);
	    
	    // PRODUTO(S) COMPRADO(S)
	    Produto produto1 = new Produto();
	    produto1.setId(10L);
	    
	    Produto produto2 = new Produto();
	    produto2.setId(11L);
	    
	    
	    // LISTA PARA ARMAZENAR O(S) PRODUTO(S)
	    List<Produto> produtos = new ArrayList<>();
	    produtos.add(produto1);
	    produtos.add(produto2);
	    
	    // CRIAR VENDA
	    venda.setCliente(cliente);
	    venda.setProdutos(produtos);
	    
	    // CADASTRAR VENDA
	    Integer vendasRealizadas = dao.cadastrar(venda);
	    
	    if (vendasRealizadas != null) {
	    	System.out.println("----VENDA REALIZADA COM SUCESSO----\n");
	    }
	    
	    // CONSULTAR VENDA
	    Venda vendaConsultada = dao.consultar(venda.getId());
	    assertNotNull(vendaConsultada);
	    assertEquals(venda.getId(), vendaConsultada.getId());
	    
	    System.out.println("----VENDA CONSULTADA COM SUCESSO----");
	}

}
