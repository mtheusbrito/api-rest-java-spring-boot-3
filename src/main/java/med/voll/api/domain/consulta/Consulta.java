package med.voll.api.domain.consulta;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.Entidade;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.paciente.Paciente;

@Table(name ="CONSULTA")
@Entity(name ="CONSULTA")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Consulta extends Entidade {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "medico_id")
	private Medico medico;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "paciente_id")
	private Paciente paciente;

	private LocalDateTime data;
	
	
	@Enumerated(EnumType.STRING)
	private MotivoCancelamento motivoCancelamento;

	private Boolean ativo;


	public void cancelar(CancelarAgendamentoDTO dto) {
		this.motivoCancelamento =dto.getMotivo();
		this.ativo = Boolean.FALSE;
	}
	
	

}
