package leo.lucas.ClienteCrud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import leo.lucas.ClienteCrud.entity.Cliente;

/**
 * Interface que define o repositório de dados para a entidade Cliente.
 */
public interface ClienteRepository extends JpaRepository<Cliente, Long>{
	
	/**
     * Consulta com query nativa para a busca dos clientes pelo nome.
     *
     * @param nome O nome a ser pesquisado.
     * @return Uma lista de clientes com o nome especificado.
     */
	@Query(value = "SELECT * FROM cliente c WHERE c.nome = :nome", nativeQuery = true)
    List<Cliente> encontrarPorNome(@Param("nome") String nome); 
	
	/**
     * Consulta com query nativa para a busca dos clientes pelo nome e CPF.
     *
     * @param nome O nome a ser pesquisado.
     * @param cpf O CPF a ser pesquisado.
     * @return Uma lista de clientes com o nome e CPF especificados.
     */
	@Query(value = "SELECT * FROM cliente c WHERE c.nome = :nome AND c.cpf = :cpf", nativeQuery = true)
    List<Cliente> encontrarPorNomeECpf(@Param("nome") String nome, @Param("cpf") String cpf); 
}
