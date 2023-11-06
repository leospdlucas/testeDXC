package leo.lucas.ClienteCrud.service;

import java.util.List;

import org.springframework.web.client.HttpClientErrorException;

import leo.lucas.ClienteCrud.entity.Cliente;

/**
 * Interface que define os serviços relacionados a clientes.
 */
public interface ClienteService {

	public Cliente inserirCliente(Cliente cliente) throws HttpClientErrorException;
	
	public List<Cliente> inserirClientes(List<Cliente> clientes) throws HttpClientErrorException;
	
	public List<Cliente> buscarClientes();
	
	public Cliente atualizarCliente(Long id, Cliente cliente) throws HttpClientErrorException;
	
	public boolean deletarCliente(Long id);
	
	public Cliente buscarCliente(Long id);
	
	public List<Cliente> buscarPorNome(String nome);
	
	
	
}
