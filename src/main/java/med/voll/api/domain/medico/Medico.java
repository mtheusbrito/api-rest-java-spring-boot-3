package med.voll.api.domain.medico;

import java.io.Serializable;

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
import lombok.ToString;
import med.voll.api.domain.Entidade;
import med.voll.api.domain.endereco.Endereco;

@Entity(name = "MEDICO")
@Table(name = "MEDICO")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
		this.ativo = Boolean.TRUE;
		this.nome = dados.getNome();
		this.email = dados.getEmail();
		this.telefone = dados.getTelefone();
		this.crm = dados.getCrm();
		this.endereco = new Endereco(dados.getEndereco());
		this.especialidade = dados.getEspecialidade();

	}

	public void atualizarInformacoes(@Valid MedicoUpdateDTO dados) {
		if (dados.getNome() != null) {
			this.nome = dados.getNome();
		}
		if (dados.getEmail() != null) {
			this.email = dados.getEmail();
		}
		if (dados.getTelefone() != null) {
			this.telefone = dados.getTelefone();
		}
		if (dados.getCrm() != null) {
			this.crm = dados.getCrm();
		}
		if (dados.getEspecialidade() != null) {
			this.especialidade = dados.getEspecialidade();
		}
		if (dados.getEndereco() != null) {
			this.endereco.atualizaInformacoes(dados.getEndereco());
		}

	}

	public void excluir() {
		this.ativo = Boolean.FALSE;
	}
}
