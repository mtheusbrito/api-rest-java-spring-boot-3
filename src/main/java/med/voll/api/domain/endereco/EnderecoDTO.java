package med.voll.api.domain.endereco;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoDTO{
	

		@NotBlank
		private String logradouro;
		@NotBlank
		private String bairro;
		@NotBlank
		@Pattern(regexp = "\\d{8}")
		private String cep;
		@NotBlank
		private String cidade;
		@NotBlank
		private String uf;
		@NotBlank
		private String complemento;
		@NotBlank
		private String numero;

}
