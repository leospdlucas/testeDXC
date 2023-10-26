package leo.lucas.ClienteCrud.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import leo.lucas.ClienteCrud.entity.Cliente;
import leo.lucas.ClienteCrud.repository.ClienteRepository;
import leo.lucas.ClienteCrud.service.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService{
	
	@Autowired
	ClienteRepository clienteRepository;
	
	public void inserirCliente(Cliente cliente) {
		
		clienteRepository.save(cliente);
	}
	
	public List<Cliente> buscarClientes(){
		
		return clienteRepository.findAll();
	}

	public void atualizarCliente(Cliente cliente) {
		
		clienteRepository.save(cliente);
	}
	
	public void deletarCliente(Integer id) {
		
		clienteRepository.deleteById(id);
	}
	
	public Cliente buscarCliente(Integer id){
		
		return clienteRepository.findById(id).get();
	}

}
