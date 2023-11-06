package leo.lucas.ClienteCrud.validator;

import org.springframework.stereotype.Component;

/**
 * Classe responsável por validar CNPJ.
 */
@Component
public class ValidadorCNPJ {

	/**
     * Valida um número de CNPJ.
     *
     * @param cnpj O número de CNPJ a ser validado.
     * @return `true` se o CNPJ for válido, `false` caso contrário.
     */
    public boolean validarCNPJ(String cnpj) {
        // Remove caracteres não numéricos
        cnpj = cnpj.replaceAll("[^\\d]", "");

        // Verifica se o CNPJ tem 14 dígitos
        if (cnpj.length() != 14) {
            return false;
        }

        // Verifica se todos os dígitos são iguais, o que torna o CNPJ inválido
        if (cnpj.matches("(\\d)\\1*")) {
            return false;
        }

        // Calcula o primeiro dígito verificador
        int digito1 = calcularDigito(cnpj.substring(0, 12), 5);

        // Calcula o segundo dígito verificador
        int digito2 = calcularDigito(cnpj.substring(0, 12) + digito1, 6);

        // Verifica se os dígitos verificadores calculados são iguais aos dígitos reais
        return cnpj.equals(cnpj.substring(0, 12) + digito1 + digito2);
    }

    /**
     * Calcula um dígito verificador com base no número e no peso especificados.
     *
     * @param cnpj  O número de CNPJ a ser calculado.
     * @param peso  O peso a ser aplicado a cada dígito.
     * @return O dígito verificador calculado.
     */
    private int calcularDigito(String cnpj, int peso) {
        int soma = 0;
        for (int i = 0; i < cnpj.length(); i++) {
            soma += Integer.parseInt(String.valueOf(cnpj.charAt(i))) * peso--;
            if (peso < 2) {
                peso = 9;
            }
        }
        int resto = soma % 11;
        return resto < 2 ? 0 : 11 - resto;
     }
}