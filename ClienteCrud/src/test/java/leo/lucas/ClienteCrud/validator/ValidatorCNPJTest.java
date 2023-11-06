package leo.lucas.ClienteCrud.validator;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidatorCNPJTest {

	@SpringBootTest
	public class ValidadorCNPJTest {

	    @Autowired
	    private ValidadorCNPJ validadorCNPJ;

	    @Test
	    public void testCNPJValido() {
	        assertTrue(validadorCNPJ.validarCNPJ("83.277.116/0001-22"));
	    }

	    @Test
	    public void testCNPJInvalido() {
	        assertFalse(validadorCNPJ.validarCNPJ("00.000.000/0000-00"));
	    }

	    @Test
	    public void testCNPJComCaracteresEspeciais() {
	        assertTrue(validadorCNPJ.validarCNPJ("83.277.116/0001-22"));
	    }

	    @Test
	    public void testCNPJMaisDeCatorzeDigitos() {
	        assertFalse(validadorCNPJ.validarCNPJ("83.277.116/0001-222"));
	    }

	    @Test
	    public void testCNPJComDigitosIguais() {
	        assertFalse(validadorCNPJ.validarCNPJ("00.000.000/0000-00"));
	        }
	}
	
}
