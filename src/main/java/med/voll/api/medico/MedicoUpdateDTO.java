package med.voll.api.medico;

import jakarta.validation.constraints.NotNull;
import med.voll.api.endereco.EnderecoDTO;

public record MedicoUpdateDTO(@NotNull Long id, String nome, String email,String crm, String telefone, Especialidade especialidade , EnderecoDTO endereco ) {
	
}