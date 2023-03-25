package med.voll.api.domain.medico;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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

@Entity(name="MEDICO")
@Table(name="MEDICO")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {


	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String telefone;
	private String email;
	private String crm;
	
	private Boolean ativo; 
	
	@Enumerated(EnumType.STRING)
	private Especialidade especialidade;
	
	@Embedded
	private Endereco endereco;
	
	public Medico(MedicoDTO dados) {
		this.ativo = Boolean.FALSE;
		this.nome = dados.nome();
		this.email= dados.email();
		this.telefone = dados.telefone();
		this.crm = dados.crm();
		this.endereco = new Endereco(dados.endereco());
		this.especialidade = dados.especialidade();
			
		
	}

	public void atualizarInformacoes(@Valid MedicoUpdateDTO dados) {
		if(dados.nome()!=null) {
			this.nome = dados.nome();
		}
		if(dados.email()!=null) {
			this.email = dados.email();
		}
		if(dados.telefone()!=null) {
			this.telefone = dados.telefone();
		}
		if(dados.crm()!=null) {
			this.crm = dados.crm();
		}
		if(dados.especialidade()!=null) {
			this.especialidade = dados.especialidade();
		}
		if(dados.endereco()!=null) {
			this.endereco.atualizaInformacoes(dados.endereco());
		}
		
	}

	public void excluir() {
		this.ativo = Boolean.FALSE;
	}
}
