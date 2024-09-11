package br.com.lbomfim;

import org.junit.Test;

import br.com.lbomfim.dao.ClienteDAO;
import br.com.lbomfim.dao.generic.IGenericDAO;
import br.com.lbomfim.domain.Cliente;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

/**
 * @author Lucas Bomfim 
 */

public class ClienteTest {
	
	IGenericDAO<Cliente> dao = new ClienteDAO();
	
	@Test
	public void cadastrarCliente() throws Exception {
		// CRIAR
		Cliente cliente = new Cliente();
		cliente.setNome("Lucas Emanuel");
		cliente.setCpf(69593969055L);
		
		// CADASTRAR
		Integer clienteCadastrado = dao.cadastrar(cliente);
		
		// FEEDBACK
		if (clienteCadastrado != null) {
			System.out.println("Cliente: " + cliente.getNome() +" cadastrado com sucesso.");
		} else {
			throw new Exception("Erro ao cadastrar o cliente: " + cliente.getNome());
		}
	}

	@Test
		public void consultarCliente() throws Exception {
		// CRIAR
		Cliente cliente = new Cliente();
		cliente.setId(4L);
		cliente.setNome("Lucas Bomfim");
		
		// CONSULTAR
		Cliente clienteBD = dao.consultar(cliente.getId());
		
		// FEEDBACK
		if (clienteBD != null) {
			System.out.println("O Cliente: " + cliente.getNome() + " está cadastrado em nosso banco de dados.");
		} else {
			throw new Exception("O cliente: " + cliente.getNome() + " não foi encontrado em nosso banco de dados.");
		}
	}
	
	@Test
	public void atualizarCliente() throws Exception {
	    // CRIAR
	    Cliente cliente = new Cliente();
	    cliente.setNome("Nome alterado");
	    cliente.setCpf(74985347035L);
	    
	    // CADASTRAR
	    Integer clienteCadastrado = dao.cadastrar(cliente);
	    assertTrue(clienteCadastrado == 1);
	    assertNotNull(cliente.getId());
	    
	    // CONSULTAR
	    Cliente clienteBD = dao.consultar(cliente.getId());
	    assertEquals(cliente.getId(), clienteBD.getId());
	    assertEquals(cliente.getNome(), clienteBD.getNome());
	    
	    // ALTERAR
	    clienteBD.setNome("João Vitor");
	    Integer clienteAtualizado = dao.atualizar(clienteBD);
	    
	    // CONSULTAR
	    Cliente clienteAtualizadoBD = dao.consultar(clienteBD.getId());
	    assertEquals(clienteBD.getNome(), clienteAtualizadoBD.getNome());
	    
	    // FEEDBACK
	    if (clienteAtualizado != null) {
	    	System.out.println("Nome alterado para: " + clienteBD.getNome());
	    }
	}

	
	@Test
	public void buscarTodos() throws Exception {		
		List<Cliente> listaDeClientes = dao.buscarTudo();
		
		// VALIDAR
		assertNotNull(listaDeClientes);
		assertTrue(listaDeClientes.size() > 0);
		
		// FEEDBACK
		for (Cliente lista : listaDeClientes) {
			System.out.println(lista.getNome());
		}
	}
	
	@Test
	public void excluirCliente() throws Exception {
		// CRIAR
	    Cliente cliente = new Cliente();
	    cliente.setNome("Teste");
	    cliente.setCpf(99999999999L);
	    
	    // CADASTRAR
	    Integer clienteCadastrado = dao.cadastrar(cliente);
	    assertTrue(clienteCadastrado == 1);
	    assertNotNull(cliente.getId());
	    assertEquals("Teste", cliente.getNome());
	    
	    // CONSULTAR
	    Cliente clienteBD = dao.consultar(cliente.getId());
	    assertNotNull(clienteBD);
	    assertEquals(cliente.getNome(), clienteBD.getNome());
	    
	    // EXCLUIR
	    Integer clienteExcluido = dao.excluir(clienteBD.getId());
	    assertTrue(clienteExcluido == 1);
	    
	    // CONSULTAR NOVAMENTE
	    Cliente clienteExcluidoBD = dao.consultar(clienteBD.getId());
	    assertNull(clienteExcluidoBD);
		
	    //FEEDBACK
		if (clienteExcluido == 1) {
			System.out.println("Cliente: " + clienteBD.getNome() + " excluido com sucesso.");
		} else {
			throw new Exception("O Cliente: " + clienteBD.getNome() + " não foi encontrado.");
		}
	}

}
