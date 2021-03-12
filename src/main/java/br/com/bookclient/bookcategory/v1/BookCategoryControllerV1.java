package br.com.bookclient.bookcategory.v1;

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

import br.com.bookclient.bookcategory.BookCategory;
import br.com.bookclient.bookcategory.BookCategoryDTO;
import br.com.bookclient.bookcategory.service.DeleteBookCategoryService;
import br.com.bookclient.bookcategory.service.GetBookCategoryService;
import br.com.bookclient.bookcategory.service.ListBookCategoryService;
import br.com.bookclient.bookcategory.service.ListPageBookCategoryService;
import br.com.bookclient.bookcategory.service.SaveBookCategoryService;
import br.com.bookclient.bookcategory.service.UpdateBookCategoryService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1/api/bookcategory")
public class BookCategoryControllerV1 {

	private final GetBookCategoryService getBookCategoryService;
	private final ListBookCategoryService listBookCategoryService;
	private final ListPageBookCategoryService listPageBookCategoryService;
	private final SaveBookCategoryService saveBookCategoryService;
	private final UpdateBookCategoryService updateBookCategoryService;
	private final DeleteBookCategoryService deleteBookCategoryService;

	@GetMapping(value = "/{id}")
	public BookCategoryDTO find(@PathVariable("id") Long id) {
		return BookCategoryDTO.from(getBookCategoryService.find(id));
	}

	@GetMapping
	public List<BookCategoryDTO> findAll() {
		return BookCategoryDTO.fromAll(listBookCategoryService.findAll());
	}

	@GetMapping("/search")
	public Page<BookCategoryDTO> search(@RequestParam(value = "nome", required = false, defaultValue = "") String nome,
			Pageable pageable) {
		return BookCategoryDTO.fromPage(listPageBookCategoryService.findPage(nome, pageable));

	}

	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping
	public void insert(@Valid @RequestBody BookCategoryDTO bookcategoryDTO) {
		saveBookCategoryService.insert(BookCategory.to(bookcategoryDTO));
	}

	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@PutMapping(value = "/{id}")
	public void update(@Valid @RequestBody BookCategoryDTO bookcategoryDTO, @PathVariable Long id) {
		updateBookCategoryService.update(BookCategory.to(bookcategoryDTO), id);
	}

	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@DeleteMapping(value = "/{id}")
	public void delete(@PathVariable Long id) {
		deleteBookCategoryService.delete(id);
	}

}
