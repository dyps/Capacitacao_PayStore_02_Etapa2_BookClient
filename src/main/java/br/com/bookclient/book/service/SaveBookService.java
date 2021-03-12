package br.com.bookclient.book.service;

import br.com.bookclient.book.Book;

@FunctionalInterface
public interface SaveBookService {

	void insert(Book book);

}
