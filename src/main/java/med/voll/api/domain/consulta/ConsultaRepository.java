package med.voll.api.domain.consulta;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
	
	Boolean existsByMedicoIdAndDataAndAtivo(Long idMedico, LocalDateTime data, Boolean ativo);

	Boolean existsByPacienteIdAndAtivoAndDataBetween(Long idPaciente, Boolean ativo, LocalDateTime primeiroHorario,
			LocalDateTime ultimoHorario);


}
