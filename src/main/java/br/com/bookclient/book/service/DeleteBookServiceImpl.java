package br.com.bookclient.book.service;

import org.springframework.stereotype.Service;

import br.com.bookclient.book.BookRepository;
import br.com.bookclient.exceptions.BookNotDeletedException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class DeleteBookServiceImpl implements DeleteBookService {

	private final BookRepository bookRepository;

	@Override
	public void delete(Long id) {
		if (!bookRepository.existsById(id)) {
			throw new BookNotDeletedException();
		}
		bookRepository.deleteById(id);
	}
}
