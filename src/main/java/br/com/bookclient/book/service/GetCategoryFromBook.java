package br.com.bookclient.book.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.bookclient.book.BookCategoryDTOGet;
import br.com.bookclient.book.BookDTO;
import br.com.bookclient.bookcategory.service.GetBookCategoryService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class GetCategoryFromBook {
	
	private final GetBookCategoryService getCategoriaBookService;
	
	public void buscarCategorias(BookDTO bookdto) {
		List<BookCategoryDTOGet> lista = new ArrayList<BookCategoryDTOGet>();
		System.out.println(bookdto.getBookCategories());
		for (BookCategoryDTOGet bookCategoryDTOGet : bookdto.getBookCategories()) {
			System.out.println(bookCategoryDTOGet);
			lista.add(BookCategoryDTOGet.from( getCategoriaBookService.find(bookCategoryDTOGet.getId())));
		}
		bookdto.setBookCategories(lista);
	}

}
