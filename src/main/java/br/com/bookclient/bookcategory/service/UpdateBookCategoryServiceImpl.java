package br.com.bookclient.bookcategory.service;

import org.springframework.stereotype.Service;

import br.com.bookclient.bookcategory.BookCategory;
import br.com.bookclient.bookcategory.BookCategoryRepository;
import br.com.bookclient.exceptions.BookCategoryNotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UpdateBookCategoryServiceImpl implements UpdateBookCategoryService {

	private final BookCategoryRepository categoriaLivroRepository;

	@Override
	public void update(BookCategory newBookCategory, Long id) {
		BookCategory categoriaLivro = categoriaLivroRepository.findById(id).orElseThrow(BookCategoryNotFoundException::new);
		categoriaLivro.setName(newBookCategory.getName());
		categoriaLivroRepository.save(categoriaLivro);
	}
}
