package med.voll.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;



// Para permitir o deploy em tomcat
// a classe ApiApplication deve extender a classe SpringBootServletInitializer e o metodo configure deve ser sobreescrito

@SpringBootApplication
public class ApiApplication {
//public class ApiApplication  extends SpringBootServletInitializer{
	
//	@Override
//	  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//	    return application.sources(ApiApplication.class);
//	  }

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

}
