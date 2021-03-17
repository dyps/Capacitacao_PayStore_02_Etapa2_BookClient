create table tb_book_book_categories (
       book_id int8 not null,
       book_categories_id int8,
       constraint book_categories_idFK foreign key (book_categories_id) references tb_book_category,
       constraint books_idFK foreign key (book_id ) references tb_book
    );