package leo.lucas.ClienteCrud.api;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import leo.lucas.ClienteCrud.entity.Cliente;

@RequestMapping("/api")
public interface ClienteApi {

	@PostMapping("/inserirCliente")
	public ResponseEntity<Cliente> inserirCliente(@RequestBody Cliente cliente);
	
	@GetMapping("/buscarClientes")
	public ResponseEntity<List<Cliente>> buscarClientes();
	
	@PutMapping("/atualizarCliente")
	public ResponseEntity<Cliente> atualizarCliente(@RequestBody Cliente cliente);
	
	@DeleteMapping("/deletarCliente/{id}")
	public ResponseEntity<HttpStatus> deletarCliente(@PathVariable("id") Integer id);
	
	@GetMapping("/buscarCliente/{id}")
	public ResponseEntity<Cliente> buscarCliente(@PathVariable("id")Integer id);

	
}
