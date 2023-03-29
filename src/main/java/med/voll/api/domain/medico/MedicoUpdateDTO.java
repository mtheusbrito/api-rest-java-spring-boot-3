package med.voll.api.domain.medico;

import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.endereco.EnderecoDTO;

public record MedicoUpdateDTO(@NotNull Long id, String nome, String email,String crm, String telefone, Especialidade especialidade , EnderecoDTO endereco, Boolean ativo ) {
	
}