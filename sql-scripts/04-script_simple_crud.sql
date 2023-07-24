create table tipo_respuesto(
pk_id  int(11) not null auto_increment,
nombre varchar(50) default '',
descripcion varchar(100) default '',
fecha_registro date,
primary key (pk_id)
)engine=innodb;

create table respuesto(
pk_id  int(11) not null auto_increment,
nombre varchar(50) default '',
descripcion varchar(100) default '',
fecha_registro datetime,
fk_tipo_respuesto int(11),
primary key (pk_id),
foreign key (fk_tipo_respuesto) references tipo_respuesto (pk_id)
)engine=innodb;

