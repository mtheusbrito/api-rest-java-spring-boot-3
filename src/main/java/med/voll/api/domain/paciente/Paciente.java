package med.voll.api.domain.paciente;

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

@Table(name = "PACIENTE")
@Entity(name = "PACIENTE")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Paciente {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String email;
	private String cpf; 
	private String logradouro;
	private String bairro;
	private String cep;
	private String complemento;
	private String numero;
	private String uf;
	private String cidade;
	private String telefone;
	private Boolean ativo;

	public Paciente(@Valid PacienteDTO pacienteDTO) {
		this.ativo = Boolean.TRUE;
		this.nome = pacienteDTO.nome();
		this.cpf = pacienteDTO.cpf();
		this.logradouro= pacienteDTO.logradouro();
		this.bairro= pacienteDTO.bairro();
		this.complemento= pacienteDTO.complemento();
		this.numero= pacienteDTO.numero();
		this.uf= pacienteDTO.uf();
		this.cidade= pacienteDTO.cidade();
		this.telefone= pacienteDTO.telefone();
		this.email = pacienteDTO.email();
		this.cep = pacienteDTO.cep();
		
	}
	
	
	
}
