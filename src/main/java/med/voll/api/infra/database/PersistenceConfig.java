package med.voll.api.infra.database;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import med.voll.api.domain.usuario.Usuario;

@Configuration
@EnableTransactionManagement
//@EnableJpaRepositories(entityManagerFactoryRef = "domainsEntityManager",
//transactionManagerRef = "domainsTransactionManager",basePackages="med.voll.api.domain")
@EnableJpaAuditing(auditorAwareRef="auditorProvider")
public class PersistenceConfig {

	
	@Bean
    AuditorAware<Usuario> auditorProvider() {
        return new AuditorAwareImpl();
    }

}
