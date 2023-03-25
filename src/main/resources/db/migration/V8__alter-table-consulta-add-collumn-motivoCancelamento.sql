alter table CONSULTA add motivoCancelamento varchar(100);
update CONSULTA set motivoCancelamento = null;
