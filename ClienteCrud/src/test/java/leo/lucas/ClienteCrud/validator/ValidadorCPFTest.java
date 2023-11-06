package leo.lucas.ClienteCrud.validator;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ValidadorCPFTest {

	    @Autowired
	    private ValidadorCPF validadorCPF;

	    @Test
	    public void testCPFValido() {
	        assertTrue(validadorCPF.validarCPF("52998224725"));
	    }

	    @Test
	    public void testCPFInvalido() {
	        assertFalse(validadorCPF.validarCPF("12345678901"));
	    }

	    @Test
	    public void testCPFComCaracteresEspeciais() {
	        assertTrue(validadorCPF.validarCPF("529.982.247-25"));
	    }

	    @Test
	    public void testCPFMenosDeOnzeDigitos() {
	        assertFalse(validadorCPF.validarCPF("123456789"));
	    }

	    @Test
	    public void testCPFComDigitosIguais() {
	        assertFalse(validadorCPF.validarCPF("11111111111"));
	    }

}
