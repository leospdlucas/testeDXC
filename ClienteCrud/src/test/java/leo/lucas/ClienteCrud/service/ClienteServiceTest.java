package leo.lucas.ClienteCrud.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import leo.lucas.ClienteCrud.entity.Cliente;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de teste para o serviço de Cliente.
 */
@SpringBootTest
public class ClienteServiceTest {

    @Autowired
    private ClienteService clienteService;

    /**
     * Teste para criar e consultar um cliente.
     */
    @Test
    public void testCriarConsultarCliente() {
        Cliente cliente = new Cliente();
        cliente.setNome("João");
        clienteService.inserirCliente(cliente);

        Cliente clienteSalvo = clienteService.buscarCliente(cliente.getId());
        assertNotNull(clienteSalvo);
        assertEquals("João", clienteSalvo.getNome());
    }

    /**
     * Teste para atualizar um cliente.
     */
    @Test
    public void testAtualizarCliente() {
        Cliente cliente = new Cliente();
        cliente.setNome("Maria");
        cliente = clienteService.inserirCliente(cliente);

        cliente.setNome("Mariana");
        Cliente clienteAtualizado = clienteService.atualizarCliente(cliente.getId(), cliente);
        assertNotNull(clienteAtualizado);
        assertEquals("Mariana", clienteAtualizado.getNome());
    }

    /**
     * Teste para deletar um cliente.
     */
    @Test
    public void testDeletarCliente() {
        Cliente cliente = new Cliente();
        cliente.setNome("Pedro");
        cliente = clienteService.inserirCliente(cliente);

        assertTrue(clienteService.deletarCliente(cliente.getId()));
        assertNull(clienteService.buscarCliente(cliente.getId()));
    }
    
}
