
create table entrega (
	id BIGSERIAL,
	cliente_id bigint not null,
	taxa decimal(10,2) not null,
	status VARCHAR(20) not null,
	data_pedido timestamp not null,
	data_finalizacao timestamp,

	destinatario_nome VARCHAR(60) not null,
	destinatario_logradouro VARCHAR(255) not null,
	destinatario_numero VARCHAR(30) not null,
	destinatario_complemento VARCHAR(60) not null,
	destinatario_bairro VARCHAR(30) not null,

	primary key(id)
);

alter table entrega add constraint fk_entrega_cliente
foreign key (cliente_id) references cliente (id);
