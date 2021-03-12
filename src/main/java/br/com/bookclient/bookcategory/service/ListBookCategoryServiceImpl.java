package br.com.bookclient.bookcategory.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.bookclient.bookcategory.BookCategory;
import br.com.bookclient.bookcategory.BookCategoryRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ListBookCategoryServiceImpl implements ListBookCategoryService {

	private final BookCategoryRepository categoriaLivroRepository;

	public List<BookCategory> findAll() {
		return categoriaLivroRepository.findAll();
	}

}
