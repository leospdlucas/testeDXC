package leo.lucas.ClienteCrud.api;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import leo.lucas.ClienteCrud.entity.Cliente;

/**
 * Uma interface que define endpoints da API para operações relacionadas a clientes.
 * 
 * @param <T> O tipo de resposta que a API deve retornar. Obtive dificuldades na resposta do tipo List de Cliente
 * após implemetar o endpoint inserirClientes, por isso usei T como genérico. 
 */
@RequestMapping("/api")
public interface ClienteApi<T> {

	@PostMapping("/inserirCliente")
	public ResponseEntity<T> inserirCliente(@RequestBody Cliente cliente);
	
	@GetMapping("/buscarClientes")
	public ResponseEntity<T> buscarClientes();
	
	@PutMapping("/atualizarCliente/{id}")
	public ResponseEntity<T> atualizarCliente(@PathVariable("id") Long id, @RequestBody Cliente cliente);
	
	@DeleteMapping("/deletarCliente/{id}")
	public ResponseEntity<T> deletarCliente(@PathVariable("id") Long id);
	
	@GetMapping("/buscarCliente/{id}")
	public ResponseEntity<T> buscarCliente(@PathVariable("id")Long id);
	
	@GetMapping("/buscarPorNome/{nome}")
	public ResponseEntity<T> buscarPorNome(@PathVariable("nome")String nome);

}
