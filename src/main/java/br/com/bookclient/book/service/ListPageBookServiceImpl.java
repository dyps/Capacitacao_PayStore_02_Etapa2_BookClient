package br.com.bookclient.book.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.bookclient.book.Book;
import br.com.bookclient.book.BookDTO;
import br.com.bookclient.book.BookRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ListPageBookServiceImpl implements ListPageBookService {

	private final BookRepository bookRepository;
	private final GetCategoryFromBook getCategoryFromBook;

	public Page<BookDTO> findPage(String searchTerm, Pageable pageable) {

		pageable.getSortOr( Sort.by(Sort.Direction.ASC, "title"));
//		 PageRequest pageRequest = PageRequest.of(
//	                page,
//	                size,
//	                Sort.Direction.ASC,
//	                "title");
		
		Page<Book> page = bookRepository.findAll(searchTerm.toLowerCase(), pageable);
		Page<BookDTO> paginacao = BookDTO.fromPage(page);

		for (BookDTO bookDTO : paginacao)
			getCategoryFromBook.buscarCategorias(bookDTO);
		return paginacao;
	}

}
