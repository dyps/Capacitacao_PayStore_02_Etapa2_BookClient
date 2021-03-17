package br.com.bookclient.book.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import br.com.bookclient.book.BookDTO;
import br.com.bookclient.book.BookRepository;
import br.com.bookclient.exceptions.BookNotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class GetBookByUUIDServiceImpl implements GetBookByUUIDService {
	
	private final BookRepository bookRepository;
    private final GetCategoryFromBook getCategoryFromBook;

    @Override
    public BookDTO find(UUID uuid) {
    	try {
    		BookDTO bookdto = BookDTO.from(bookRepository.findByUUID(uuid).get(0));
    		getCategoryFromBook.buscarCategorias(bookdto);
    		return bookdto;
		} catch (Exception e) {
			throw new BookNotFoundException();
		}
    }

}
