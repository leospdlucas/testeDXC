package leo.lucas.ClienteCrud.service;

import java.util.List;

import leo.lucas.ClienteCrud.entity.Cliente;

public interface ClienteService {

	public void inserirCliente(Cliente cliente);
	
	public List<Cliente> buscarClientes();
	
	public void atualizarCliente(Cliente cliente);
	
	public void deletarCliente(Integer id);
	
	public Cliente buscarCliente(Integer id);
	
}
