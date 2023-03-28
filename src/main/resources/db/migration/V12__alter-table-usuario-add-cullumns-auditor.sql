alter table USUARIO 
add criadoEm datetime not null,
add modificadoEm datetime not null,
add criadoPor_id bigint,
add modificadoPor_id bigint;
update USUARIO
set criadoEm = now(), 
modificadoEm = now(),
criadoPor_id = null,
modificadoPor_id = null;
