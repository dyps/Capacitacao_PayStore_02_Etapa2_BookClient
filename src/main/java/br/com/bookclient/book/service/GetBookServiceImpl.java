package br.com.bookclient.book.service;

import org.springframework.stereotype.Service;

import br.com.bookclient.book.BookDTO;
import br.com.bookclient.book.BookRepository;
import br.com.bookclient.exceptions.BookNotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class GetBookServiceImpl implements GetBookService {

    private final BookRepository bookRepository;
    private final GetCategoryFromBook getCategoryFromBook;

    @Override
    public BookDTO find(Long id) {
		BookDTO bookdto = BookDTO.from(bookRepository.findById(id).orElseThrow(BookNotFoundException::new));
		getCategoryFromBook.buscarCategorias(bookdto);
		return bookdto;
    }

	

}
