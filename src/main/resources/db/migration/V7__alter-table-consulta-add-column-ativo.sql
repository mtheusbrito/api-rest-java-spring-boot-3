alter table CONSULTA add ativo char(1) not null default 'F';
update CONSULTA set ativo = 'T';