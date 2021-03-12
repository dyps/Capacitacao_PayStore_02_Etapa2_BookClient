package br.com.bookclient.book.v1;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import br.com.bookclient.book.Book;
import br.com.bookclient.book.BookDTO;
import br.com.bookclient.book.BookDTOFromCategory;
import br.com.bookclient.book.service.DeleteBookService;
import br.com.bookclient.book.service.GetBookService;
import br.com.bookclient.book.service.ListBookService;
import br.com.bookclient.book.service.ListPageBookService;
import br.com.bookclient.book.service.SaveBookService;
import br.com.bookclient.book.service.UpdateBookService;
import br.com.bookclient.bookcategory.service.GetBookCategoryService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1/api/book")
public class BookControllerV1 {

	private final GetBookService getBookService;
	private final GetBookCategoryService getCategoriaBookService;
	private final ListBookService listBookService;
	private final SaveBookService saveBookService;
	private final UpdateBookService updateBookService;
	private final DeleteBookService deleteBookService;
	private final ListPageBookService listPageBookService;

	@GetMapping(value = "/{id}")
	public BookDTO find(@PathVariable("id") Long id) {
		return getBookService.find(id);
	}

	@GetMapping("/search")
	public Page<BookDTO> search(@RequestParam(value = "titulo", required = false, defaultValue = "") String searchTerm,
			 Pageable pageable ){
		return listPageBookService.findPage(searchTerm, pageable);
	}

	@GetMapping
	public List<BookDTO> findAll() {
		return listBookService.findAll();
	}

	@GetMapping(value = "/categoria/{id}")
	public List<BookDTOFromCategory > findAllPorCat(@PathVariable("id") Long id) {
		return BookDTOFromCategory.fromAll(getCategoriaBookService.find(id).getBooks());
	}

	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping // adiciona um novo Book
	public void insert(@Valid @RequestBody BookDTO bookDTO) {
		saveBookService.insert(Book.to(bookDTO));
	}

	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@PutMapping(value = "/{id}") // atualizar um Book
	public void update(@Valid @RequestBody BookDTO bookDTO, @PathVariable Long id) {
		updateBookService.update(Book.to(bookDTO), id);
	}

	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@DeleteMapping(value = "/{id}") // Deleta Book
	public void delete(@PathVariable Long id) {
		deleteBookService.delete(id);
	}

}
