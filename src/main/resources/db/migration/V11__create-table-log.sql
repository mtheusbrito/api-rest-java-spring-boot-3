create table LOG(

id bigint not null auto_increment,
titulo varchar(255),
descricao longtext,
classeObjeto varchar(255),
idObjeto varchar(255),
usuario_id bigint,
criadoPor_id bigint,
modificadoPor_id bigint,
criadoEm datetime,
modificadoEm datetime,


primary key(id),
constraint  fk_log_usuario_id foreign key(usuario_id) references USUARIO(id),
constraint  fk_log_criadoPor_id foreign key(criadoPor_id) references USUARIO(id),
constraint  fk_log_modificadoPor_id foreign key(modificadoPor_id) references USUARIO(id)

)