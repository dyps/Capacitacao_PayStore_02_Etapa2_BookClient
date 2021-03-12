package br.com.bookclient.book.service;

import java.util.List;

import br.com.bookclient.book.BookDTO;

@FunctionalInterface
public interface ListBookService {

	List<BookDTO> findAll();

}
