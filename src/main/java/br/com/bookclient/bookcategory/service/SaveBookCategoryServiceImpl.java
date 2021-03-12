package br.com.bookclient.bookcategory.service;

import org.springframework.stereotype.Service;

import br.com.bookclient.bookcategory.BookCategory;
import br.com.bookclient.bookcategory.BookCategoryRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SaveBookCategoryServiceImpl implements SaveBookCategoryService {

    private final BookCategoryRepository categoriaLivroRepository;

    @Override
    public void insert(BookCategory categoriaLivro) {
		categoriaLivroRepository.save(categoriaLivro);
    }
}
