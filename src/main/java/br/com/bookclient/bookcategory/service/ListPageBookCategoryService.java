package br.com.bookclient.bookcategory.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.bookclient.bookcategory.BookCategory;

@FunctionalInterface
public interface ListPageBookCategoryService {

	Page<BookCategory> findPage(String nome, Pageable pageable);


}
