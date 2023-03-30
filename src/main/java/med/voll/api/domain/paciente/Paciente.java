package med.voll.api.domain.paciente;

import java.io.Serializable;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.endereco.Endereco;

@Table(name = "PACIENTE")
@Entity(name = "PACIENTE")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Paciente implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String email;
	private Boolean ativo;
	private String cpf; 
	private String telefone;
	
	@Embedded
	private Endereco endereco;

	public Paciente(@Valid PacienteDTO pacienteDTO) {
		this.ativo = Boolean.TRUE;
		this.nome = pacienteDTO.getNome();
		this.cpf = pacienteDTO.getCpf();
		this.email = pacienteDTO.getEmail();
		this.telefone = pacienteDTO.getTelefone();
		this.endereco = new Endereco(pacienteDTO.getEndenreco());
		
	}
	
	
	
}
