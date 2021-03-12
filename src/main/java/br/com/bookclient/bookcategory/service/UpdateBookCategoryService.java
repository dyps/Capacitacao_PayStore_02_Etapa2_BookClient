package br.com.bookclient.bookcategory.service;

import br.com.bookclient.bookcategory.BookCategory;

@FunctionalInterface
public interface UpdateBookCategoryService {

	void update(BookCategory cliente, Long id);

}