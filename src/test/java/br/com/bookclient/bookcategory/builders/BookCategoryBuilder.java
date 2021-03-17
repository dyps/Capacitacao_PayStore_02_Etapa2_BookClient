package br.com.bookclient.bookcategory.builders;

import br.com.bookclient.bookcategory.BookCategory;

public class BookCategoryBuilder {

	public static BookCategory.Builder createBookCategory() {
		return BookCategory.builder()
				.name("Ação")
				;
	}
}
