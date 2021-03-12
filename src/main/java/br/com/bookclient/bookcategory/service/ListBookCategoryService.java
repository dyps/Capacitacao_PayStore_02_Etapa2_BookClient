package br.com.bookclient.bookcategory.service;

import java.util.List;

import br.com.bookclient.bookcategory.BookCategory;

@FunctionalInterface
public interface ListBookCategoryService {

	List<BookCategory> findAll();

}
