
package br.com.bookclient.book.service;

import br.com.bookclient.book.BookDTO;

@FunctionalInterface
public interface GetBookService {

	BookDTO find(Long id);

}
