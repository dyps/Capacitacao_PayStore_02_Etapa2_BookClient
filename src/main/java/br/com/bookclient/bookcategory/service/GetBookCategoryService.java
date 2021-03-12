package br.com.bookclient.bookcategory.service;

import br.com.bookclient.bookcategory.BookCategory;

@FunctionalInterface
public interface GetBookCategoryService {

	BookCategory find(Long id);

}

