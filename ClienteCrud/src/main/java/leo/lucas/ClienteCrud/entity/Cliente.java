package leo.lucas.ClienteCrud.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Classe que representa um cliente.
 */
@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Cliente implements Serializable{

	private static final long serialVersionUID = -3969352858203924755L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column
	private String nome;
	@Column
	private int idade;
	@Column
	private String cpf;
	@Column
	private String rg;
	@Column
	@JsonFormat(pattern = "dd/MM/yyyy" )
	private Date data_nasc;
	@Column
	private String sexo;
	@Column
	private String email;
	@Column
	private String cep;
	@Column
	private String endereco;
	@Column
	private int numero;
	@Column
	private String bairro;
	@Column
	private String cidade;
	@Column
	private String estado;
	@Column
	private String telefone_fixo;
	@Column
	private String celular;
	@Column
	private String altura;
	@Column
	private String peso;
}
