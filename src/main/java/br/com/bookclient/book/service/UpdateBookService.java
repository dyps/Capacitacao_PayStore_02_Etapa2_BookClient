package br.com.bookclient.book.service;

import br.com.bookclient.book.Book;

@FunctionalInterface
public interface UpdateBookService {

	void update(Book newBook, Long id);

}
