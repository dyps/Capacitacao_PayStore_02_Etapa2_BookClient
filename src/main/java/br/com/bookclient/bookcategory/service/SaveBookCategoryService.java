package br.com.bookclient.bookcategory.service;

import br.com.bookclient.bookcategory.BookCategory;

@FunctionalInterface
public interface SaveBookCategoryService {

	void insert(BookCategory cliente);

}