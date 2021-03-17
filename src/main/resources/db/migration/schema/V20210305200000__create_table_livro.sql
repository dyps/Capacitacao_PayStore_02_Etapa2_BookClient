create table tb_book (
		id int8 not null,
        livro_db uuid not null,
        author varchar(255),
        available_quantity int4,
        isbn varchar(255),
        price_sale float4,
        synopsis varchar(255),
        title varchar(255),
        year_publication date,
      	PRIMARY KEY (id),
      	constraint livro_db_unique unique (livro_db)
    );
create sequence book_seq start 1 increment 1;