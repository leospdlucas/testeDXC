package leo.lucas.ClienteCrud.validator;

import org.springframework.stereotype.Component;

/**
 * Classe responsável por validar CPF.
 */
@Component
public class ValidadorCPF {

	/**
     * Valida um número de CPF.
     *
     * @param cpf O número de CPF a ser validado.
     * @return `true` se o CPF for válido, `false` caso contrário.
     */
    public static boolean validarCPF(String cpf) {
        // Remove caracteres não numéricos
        cpf = cpf.replaceAll("[^\\d]", "");

        // Verifica se o CPF tem 11 dígitos
        if (cpf.length() != 11) {
            return false;
        }

        // Verifica se todos os dígitos são iguais, o que torna o CPF inválido
        if (cpf.matches("(\\d)\\1*")) {
            return false;
        }

        // Calcula o primeiro dígito verificador
        int digito1 = calcularDigito(cpf.substring(0, 9), 10);

        // Calcula o segundo dígito verificador
        int digito2 = calcularDigito(cpf.substring(0, 9) + digito1, 11);

        // Verifica se os dígitos verificadores calculados são iguais aos dígitos reais
        return cpf.equals(cpf.substring(0, 9) + digito1 + digito2);
    }

    /**
     * Calcula um dígito verificador com base no número e no peso especificados.
     *
     * @param cpf  O número de CPF a ser calculado.
     * @param peso O peso a ser aplicado a cada dígito.
     * @return O dígito verificador calculado.
     */
    private static int calcularDigito(String cpf, int peso) {
        int soma = 0;
        for (int i = 0; i < cpf.length(); i++) {
            soma += Integer.parseInt(cpf.substring(i, i + 1)) * peso--;
        }
        int resto = soma % 11;
        return resto < 2 ? 0 : 11 - resto;
        }
}
