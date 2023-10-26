package leo.lucas.ClienteCrud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import leo.lucas.ClienteCrud.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>{
	
	
}
