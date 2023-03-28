alter table CONSULTA 
add criadoEm datetime not null,
add modificadoEm datetime not null,
add criadoPor_id bigint not null,
add modificadoPor_id bigint not null;
update CONSULTA
set criadoEm = now(), 
modificadoEm = now(),
criadoPor_id = 1,
modificadoPor_id = 1;
