package br.com.bookclient.book.builders;

import static br.com.bookclient.book.builders.BookCategoryDTOGetBuilder.createBookCategory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.bookclient.book.BookCategoryDTOGet;
import br.com.bookclient.book.BookDTO;

public class BookBuilder {
	
	 public static BookDTO.Builder createBook() {
	        List<BookCategoryDTOGet> listbookCategoryDTOGets= new ArrayList<BookCategoryDTOGet>();
			listbookCategoryDTOGets.add(createBookCategory().id(1L).build());
			return BookDTO
	                .builder()
	                .title("teste titulo")
	                .availableQuantity(2)
	                .priceSale(5F)
	                .synopsis("teste sinopse")
	                .yearPublication(LocalDate.of(2020, 02, 02))
	                .author("yaggo")
	                .isbn("1234")
	                .bookCategories(listbookCategoryDTOGets)
	                ;
	    }

}