package med.voll.api.domain.medico;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {
	
	@Query(value="SELECT m.* FROM MEDICO m WHERE m.ativo = 'T' ", nativeQuery = true)
	Page<Medico> findAllByAtivoTrue(Pageable paginacao);

	
	@Query(value="SELECT m FROM MEDICO m WHERE m.ativo = true AND m.especialidade = :especialidade AND m.id not in( SELECT c.medico.id FROM CONSULTA c WHERE c.data = :data ) order by rand() limit 1")
	Medico escolherMedicoAleatorioLivreNaData( LocalDateTime data, Especialidade especialidade);

	
	@Query(value="SELECT m.ativo FROM MEDICO m WHERE m.id = :id ")
	Boolean findAtivoById(@Param("id") Long id);

	
	
}
