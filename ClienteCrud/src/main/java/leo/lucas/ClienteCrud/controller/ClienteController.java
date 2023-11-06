package leo.lucas.ClienteCrud.controller;

import java.util.List;

import org.hibernate.JDBCException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import leo.lucas.ClienteCrud.api.ClienteApi;
import leo.lucas.ClienteCrud.entity.Cliente;
import leo.lucas.ClienteCrud.service.ClienteService;

/**
 * Controlador que define os endpoints da API para operações relacionadas a clientes.
 * 
 * @param <T> O tipo de resposta que o controlador deve retornar.
 */
@RestController
public class ClienteController<T> implements ClienteApi{
	
	@Autowired
	ClienteService clienteService;

	/**
     * Endpoint para inserir um novo cliente.
     *
     * @param cliente Os dados do cliente a ser inserido.
     * @return ResponseEntity contendo a resposta da operação.
     */
	public ResponseEntity<T> inserirCliente(Cliente cliente) {

		try {			
			clienteService.inserirCliente(cliente);
			return (ResponseEntity<T>) ResponseEntity.status(HttpStatus.CREATED).body(cliente);
			
		} catch (JDBCException e) {
			return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
		}catch (HttpClientErrorException e) {
			return (ResponseEntity<T>) new ResponseEntity<String>(e.getStatusText(), e.getStatusCode());
		}catch (Exception e) {
			return (ResponseEntity<T>) new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	/**
     * Endpoint para inserir vários clientes de uma vez. Implementei o endpoint aqui pois na interface ClienteApi 
     * acontecia um erro de não poder sobrescrever em ClienteApi. Então padronizei também os outros com T.
     *
     * @param clientes Uma lista de clientes a serem inseridos.
     * @return ResponseEntity contendo a resposta da operação.
     */
	@PostMapping("/inserirClientes")
	public ResponseEntity<T> inserirClientes(@RequestBody List<Cliente> clientes) {

		try {			
			List<Cliente> clientesCriados = clienteService.inserirClientes(clientes);
			return (ResponseEntity<T>) new ResponseEntity<List<Cliente>>(clientesCriados, HttpStatus.CREATED);
			
		} catch (JDBCException e) {
			return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
		}catch (HttpClientErrorException e) {
			return (ResponseEntity<T>) new ResponseEntity<String>(e.getStatusText(), e.getStatusCode());
		}catch (Exception e) {
			return (ResponseEntity<T>) new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	/**
     * Endpoint para buscar todos os clientes.
     *
     * @return ResponseEntity contendo a resposta da operação.
     */
	public ResponseEntity<T> buscarClientes(){

		try {
			List<Cliente> clientes = clienteService.buscarClientes();
			if (clientes.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			return (ResponseEntity<T>) new ResponseEntity<List<Cliente>>(clientes,HttpStatus.OK);
		} catch (JDBCException e) {
			return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
     * Endpoint para atualizar os dados de um cliente existente, validando há o registro para atualizar.
     *
     * @param id      O ID do cliente a ser atualizado.
     * @param cliente Os novos dados do cliente.
     * @return ResponseEntity contendo a resposta da operação.
     */
	public ResponseEntity<T> atualizarCliente(Long id, Cliente cliente) {

		try {	
			Cliente clienteAtualizado = clienteService.atualizarCliente(id, cliente);
			if (clienteAtualizado == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}else {
				return new ResponseEntity<>(HttpStatus.OK);
			}
		} catch (JDBCException e) {
			return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
		}catch (HttpClientErrorException e) {
			return (ResponseEntity<T>) new ResponseEntity<String>(e.getStatusText(), e.getStatusCode());
		}catch (Exception e) {
			return (ResponseEntity<T>) new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
     * Endpoint para deletar um cliente por ID.
     *
     * @param id O ID do cliente a ser excluído.
     * @return ResponseEntity contendo a resposta da operação.
     */
	public ResponseEntity<T> deletarCliente(Long id) {

		try {
			if(clienteService.deletarCliente(id)) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);	
			}
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (JDBCException e) {
			return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	 /**
     * Endpoint para buscar um cliente por ID.
     *
     * @param id O ID do cliente a ser buscado.
     * @return ResponseEntity contendo a resposta da operação.
     */
	public ResponseEntity<T> buscarCliente(Long id){

		try {
			Cliente cliente = clienteService.buscarCliente(id);

			if (cliente == null) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return (ResponseEntity<T>) new ResponseEntity<Cliente>(cliente,HttpStatus.OK);
		} catch (JDBCException e) {
			return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	 /**
     * Endpoint para buscar clientes por nome.
     *
     * @param nome O nome do cliente a ser buscado.
     * @return ResponseEntity contendo a resposta da operação.
     */
	public ResponseEntity<T> buscarPorNome(String nome){

		try {
			List<Cliente> cliente = clienteService.buscarPorNome(nome);

			if (cliente == null) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return (ResponseEntity<T>) new ResponseEntity<List<Cliente>>(cliente,HttpStatus.OK);
		} catch (JDBCException e) {
			return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
