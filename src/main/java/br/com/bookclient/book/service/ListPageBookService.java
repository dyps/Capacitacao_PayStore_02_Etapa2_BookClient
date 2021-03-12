package br.com.bookclient.book.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.bookclient.book.BookDTO;

@FunctionalInterface
public interface ListPageBookService {

	Page<BookDTO> findPage(String searchTerm, Pageable pageable);

}
