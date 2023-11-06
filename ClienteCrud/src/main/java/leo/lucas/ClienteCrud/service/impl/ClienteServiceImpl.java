package leo.lucas.ClienteCrud.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import leo.lucas.ClienteCrud.entity.Cliente;
import leo.lucas.ClienteCrud.repository.ClienteRepository;
import leo.lucas.ClienteCrud.service.ClienteService;
import leo.lucas.ClienteCrud.validator.ValidadorCPF;

/**
 * Implementação dos serviços relacionados a clientes.
 */
@Service
public class ClienteServiceImpl implements ClienteService{
	
	@Autowired
	ClienteRepository clienteRepository;
	
	/**
     * Insere um novo cliente.
     *
     * @param cliente Os dados do cliente a ser inserido, validando o CPF informado.
     * @return O cliente inserido.
     * @throws HttpClientErrorException Em caso de erro na requisição HTTP.
     */
	public Cliente inserirCliente(Cliente cliente) throws HttpClientErrorException{
		if(!ValidadorCPF.validarCPF(cliente.getCpf())) {
			throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao tentar inserir o cliente. CPF inválido! ");
		}
		return clienteRepository.save(cliente);
	}
	
	/**
     * Insere vários clientes de uma vez, validando o CPF de cada um.
     *
     * @param clientes Uma lista de clientes a serem inseridos.
     * @return Uma lista de clientes inseridos.
     * @throws HttpClientErrorException Em caso de erro na requisição HTTP.
     */
	public List<Cliente> inserirClientes(List<Cliente> clientes) throws HttpClientErrorException{
		
		List<Cliente> clientesCriados = new ArrayList();
		
		for (Cliente cliente : clientes) {

			if(!ValidadorCPF.validarCPF(cliente.getCpf())) {
				throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao tentar inserir o cliente: " + cliente.getNome() + " CPF(" + cliente.getCpf() + ") inválido!");
			}else {
				if(clienteRepository.encontrarPorNomeECpf(cliente.getNome(), cliente.getCpf()).isEmpty()) {
					clientesCriados.add(cliente);
				}
			}
		}
		
		return clienteRepository.saveAll(clientesCriados);
	}
	
	/**
     * Busca todos os clientes.
     *
     * @return Uma lista de todos os clientes.
     */
	public List<Cliente> buscarClientes(){
		
		return clienteRepository.findAll();
	}

	/**
     * Atualiza os dados de um cliente existente.
     *
     * @param id      O ID do cliente a ser atualizado.
     * @param cliente Os novos dados do cliente.
     * @return O cliente atualizado.
     * @throws HttpClientErrorException Em caso de erro na requisição HTTP.
     */
	public Cliente atualizarCliente(Long id, Cliente cliente) throws HttpClientErrorException{
		
		if(clienteRepository.existsById(id)) {
			cliente.setId(id);
			if(!ValidadorCPF.validarCPF(cliente.getCpf())) {
				throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao tentar atualizar o cliente. CPF inválido! ");
			}
			return clienteRepository.save(cliente);
		}else {
			return null;
		}
	}
	
	/**
     * Deleta um cliente por ID.
     *
     * @param id O ID do cliente a ser excluído.
     * @return `true` se o cliente foi excluído com sucesso, `false` caso contrário.
     */
	public boolean deletarCliente(Long id) {
		
		if(clienteRepository.existsById(id)) {
			clienteRepository.deleteById(id);
			return true;
		}else {
			return false;
		}
	}
	
	/**
     * Busca um cliente por ID.
     *
     * @param id O ID do cliente a ser buscado.
     * @return O cliente encontrado ou `null` se não for encontrado.
     */
	public Cliente buscarCliente(Long id){
		
		return clienteRepository.findById(id).orElse(null);
	}

	/**
     * Busca clientes por nome.
     *
     * @param nome O nome do cliente a ser buscado.
     * @return Uma lista de clientes com o nome especificado.
     */
	public List<Cliente> buscarPorNome(String nome){
		
		return clienteRepository.encontrarPorNome(nome);
	}
}
