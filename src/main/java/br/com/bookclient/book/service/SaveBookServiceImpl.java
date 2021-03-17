package br.com.bookclient.book.service;


import java.util.UUID;

import org.springframework.stereotype.Service;

import br.com.bookclient.book.Book;
import br.com.bookclient.book.BookDTO;
import br.com.bookclient.book.BookRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SaveBookServiceImpl implements SaveBookService {

    private final BookRepository bookRepository;
    private final GetCategoryFromBook getCategoryFromBook;

    @Override
    public void insert(Book book) {
    	getCategoryFromBook.buscarCategorias(BookDTO.from(book));
    	
		book.setLivro_db(UUID.randomUUID());
        bookRepository.save(book);
    }
}
