package med.voll.api.domain.paciente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long>{
	
	@Query(value="SELECT p.* FROM PACIENTE p WHERE p.ativo = 'T' ", nativeQuery = true)
	Page<Paciente> findAllByAtivoTrue(Pageable paginacao);

	
	@Query(value="SELECT p.ativo FROM PACIENTE p WHERE p.id = :idPaciente ")
	Boolean findAtivoById(@Param("idPaciente")Long idPaciente);

}
