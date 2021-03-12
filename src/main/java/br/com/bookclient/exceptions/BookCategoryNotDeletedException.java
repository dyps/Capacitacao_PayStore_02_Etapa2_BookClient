package br.com.bookclient.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BookCategoryNotDeletedException extends RuntimeException{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BookCategoryNotDeletedException() {
        super("Categoria de Livro não deletada porque não foi encontrada!");
    }
}
