package br.com.bookclient.bookcategory.service;

import org.springframework.stereotype.Service;

import br.com.bookclient.bookcategory.BookCategory;
import br.com.bookclient.bookcategory.BookCategoryRepository;
import br.com.bookclient.exceptions.BookCategoryNotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class GetBookCategoryServiceImpl implements GetBookCategoryService {

	private final BookCategoryRepository categoriaLivroRepository;

	public BookCategory find(Long id) {
		return categoriaLivroRepository.findById(id).orElseThrow(BookCategoryNotFoundException::new);
	}

}
