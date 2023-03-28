package med.voll.api.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import med.voll.api.domain.consulta.MotivoCancelamento;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.paciente.Paciente;
import med.voll.api.domain.usuario.Usuario;

@Table(name="LOG")
@Entity(name="LOG")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Log extends Entidade {
	

	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String titulo;
	
	private String descricao;
		
	private String idObjeto;
	
	private String classeObjeto;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;
	
		
	
	
	
}
