package med.voll.api.domain.medico;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;

import med.voll.api.domain.consulta.Consulta;
import med.voll.api.domain.endereco.EnderecoDTO;
import med.voll.api.domain.paciente.Paciente;
import med.voll.api.domain.paciente.PacienteDTO;
import med.voll.api.utils.BeanUtil;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class MedicoRepositoryTest {

	@Autowired
	private MedicoRepository repository;
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired 
	private ApplicationContext contexto;

	
	
	


	@Test
	@DisplayName("Deveria devolver null quando único médico cadastrado não está disponível na data")
	void testEscolherMedicoAleatorioLivreNaDataCenario1 () {
		
		//given
		contextoBean();		
		LocalDateTime proximaSegundaAs10h = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10, 0);
		Medico medico = cadastrarMedico("Maria","maria@vol.med", "123456", Especialidade.CARDIOLOGIA);
		Paciente paciente = cadastrarPaciente("Paciente", "paciente@email.com", "00000000000");
		cadastrarConsulta(medico, paciente, proximaSegundaAs10h);
			

		
//		when ou act
		Medico medicoLivre = repository.escolherMedicoAleatorioLivreNaData(proximaSegundaAs10h, Especialidade.CARDIOLOGIA);
		
//		then ou assert
		assertThat(medicoLivre).isNull();
	}
	
	
	
	@Test
	@DisplayName("Deveria devolver medico quando estiver disponível na data")
	void testEscolherMedicoAleatorioLivreNaDataCenario2 () {
		
		//given
		contextoBean();
		LocalDateTime proximaSegundaAs10h = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10, 0);
		Medico medico = cadastrarMedico("Maria","maria@vol.med", "123456", Especialidade.CARDIOLOGIA);
			
		
		//when ou act
		Medico medicoLivre = repository.escolherMedicoAleatorioLivreNaData(proximaSegundaAs10h, Especialidade.CARDIOLOGIA);
		
		
		//then ou assert
		assertThat(medicoLivre).isEqualTo(medico);
	}
	
	
	
	
	
	
	private void contextoBean() {
		BeanUtil beanUtil = new BeanUtil();
		beanUtil.setApplicationContext(contexto);
	}
	
	
	private void cadastrarConsulta(Medico medico, Paciente paciente, LocalDateTime data) {
	    entityManager.persist(new Consulta(medico, paciente, data, null, Boolean.TRUE));
	}

	private Medico cadastrarMedico(String nome, String email, String crm, Especialidade especialidade) {
	    var medico = new Medico(dadosMedico(nome, email, crm, especialidade));
	    entityManager.persist(medico);
	    return medico;
	}

	private Paciente cadastrarPaciente(String nome, String email, String cpf) {
	    var paciente = new Paciente(dadosPaciente(nome, email, cpf));
	    entityManager.persist(paciente);
	    return paciente;
	}

	private MedicoDTO dadosMedico(String nome, String email, String crm, Especialidade especialidade) {
	    return new MedicoDTO(
	            nome,
	            email,
	            "61999999999",
	            crm,
	            especialidade,
	            dadosEndereco()
	    );
	}

	private PacienteDTO dadosPaciente(String nome, String email, String cpf) {
	    return new PacienteDTO(
	            nome,
	            email,
	            cpf,
	            "61999999999",
	            dadosEndereco()
	    );
	}

	private EnderecoDTO dadosEndereco() {
	    return new EnderecoDTO(
	            "rua xpto",
	            "bairro",
	            "00000000",
	            "Brasilia",
	            "DF",
	            null,
	            null
	    );
	}

}
