package br.com.bookclient.book.service;


import org.springframework.stereotype.Service;

import br.com.bookclient.book.Book;
import br.com.bookclient.book.BookRepository;
import br.com.bookclient.bookcategory.service.GetBookCategoryService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SaveBookServiceImpl implements SaveBookService {

    private final BookRepository bookRepository;
    private final GetBookCategoryService getCategoriaBookService;

    @Override
    public void insert(Book book) {
		for (Long bookCategory : book.getBookCategories())
			getCategoriaBookService.find(bookCategory);
        bookRepository.save(book);
    }
}
