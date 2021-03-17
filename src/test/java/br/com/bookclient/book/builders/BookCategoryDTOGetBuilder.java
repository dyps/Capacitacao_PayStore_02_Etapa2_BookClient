package br.com.bookclient.book.builders;

import br.com.bookclient.book.BookCategoryDTOGet;

public class BookCategoryDTOGetBuilder {

	public static BookCategoryDTOGet.Builder createBookCategory() {
		return BookCategoryDTOGet.builder()
				.name("Ação")
				;
	}
}
