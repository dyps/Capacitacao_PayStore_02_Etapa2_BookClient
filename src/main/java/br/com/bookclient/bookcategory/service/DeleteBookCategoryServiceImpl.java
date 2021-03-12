package br.com.bookclient.bookcategory.service;

import org.springframework.stereotype.Service;

import br.com.bookclient.bookcategory.BookCategoryRepository;
import br.com.bookclient.exceptions.BookCategoryNotDeletedException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class DeleteBookCategoryServiceImpl implements DeleteBookCategoryService {

	private final BookCategoryRepository categoriaLivroRepository;

	@Override
	public void delete(Long id) {
		if (!categoriaLivroRepository.existsById(id)) {
			throw new BookCategoryNotDeletedException();
		}
		categoriaLivroRepository.deleteById(id);
	}
}
