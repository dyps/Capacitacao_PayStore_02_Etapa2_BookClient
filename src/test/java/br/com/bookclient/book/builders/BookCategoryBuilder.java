package br.com.bookclient.book.builders;

import static br.com.bookclient.book.builders.BookBuilder.createBook;

import org.assertj.core.util.Lists;

import br.com.bookclient.book.Book;
import br.com.bookclient.bookcategory.BookCategory;

public class BookCategoryBuilder {

	public static BookCategory.Builder createBookCategory() {
		return BookCategory.builder()
				.name("Ação")
				.books(Lists.newArrayList(Book.to(createBook().id(1L).build())))
				;
	}
}
