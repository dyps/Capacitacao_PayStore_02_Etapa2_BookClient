package br.com.bookclient.book.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.bookclient.book.BookDTO;
import br.com.bookclient.book.BookRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ListBookServiceImpl implements ListBookService {

	private final BookRepository bookRepository;
	private final GetCategoryFromBook getCategoryFromBook;

	@Override
	public List<BookDTO> findAll() {

		List<BookDTO> list = BookDTO.fromAll(bookRepository.findAll());
		for (BookDTO bookdto : list)
			getCategoryFromBook.buscarCategorias(bookdto);
		return list;
	}

}
