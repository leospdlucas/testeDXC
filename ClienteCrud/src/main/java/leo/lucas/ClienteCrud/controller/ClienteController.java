package leo.lucas.ClienteCrud.controller;

import java.util.List;

import org.hibernate.JDBCException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import leo.lucas.ClienteCrud.api.ClienteApi;
import leo.lucas.ClienteCrud.entity.Cliente;
import leo.lucas.ClienteCrud.service.ClienteService;

@RestController
public class ClienteController implements ClienteApi{
	
	@Autowired
	ClienteService clienteService;

	public ResponseEntity<Cliente> inserirCliente(@RequestBody Cliente cliente) {

		try {			
			clienteService.inserirCliente(cliente);
			return new ResponseEntity<>(HttpStatus.CREATED);
			
		} catch (JDBCException e) {
			return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	public ResponseEntity<List<Cliente>> buscarClientes(){

		try {
			List<Cliente> clientes = clienteService.buscarClientes();
			if (clientes.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<List<Cliente>>(clientes,HttpStatus.OK);
		} catch (JDBCException e) {
			return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public ResponseEntity<Cliente> atualizarCliente(@RequestBody Cliente cliente) {

		try {	
			if (clienteService.buscarCliente(cliente.getId()) == null) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			clienteService.atualizarCliente(cliente);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (JDBCException e) {
			return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<HttpStatus> deletarCliente(@PathVariable("id") Integer id) {

		try {
			
			clienteService.deletarCliente(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (JDBCException e) {
			return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<Cliente> buscarCliente(Integer id){

		try {
			Cliente cliente = clienteService.buscarCliente(id);

			if (cliente == null) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(cliente,HttpStatus.OK);
		} catch (JDBCException e) {
			return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
