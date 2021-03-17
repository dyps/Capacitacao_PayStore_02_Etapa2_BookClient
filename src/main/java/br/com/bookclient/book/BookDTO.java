package br.com.bookclient.book;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;

import org.springframework.data.domain.Page;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(builderClassName = "Builder")
public class BookDTO {

	private Long id;
	
	private UUID livro_db;

	@NotEmpty
	private String title;
	@NotEmpty
	private String synopsis;
	@NotEmpty
	private String isbn;
	@NotEmpty
	private String author;
	@NotNull
	@PastOrPresent
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate yearPublication;
	@Positive
	@NotNull
	private Float priceSale;
	@Positive
	@NotNull
	private Integer availableQuantity;
	@NotEmpty
	@Valid
	private List<BookCategoryDTOGet> bookCategories;

	public static BookDTO from(Book book) {
		List<BookCategoryDTOGet> listaCat = new ArrayList<BookCategoryDTOGet>();
		for (Long bookDTO : book.getBookCategories()) 
			listaCat.add(BookCategoryDTOGet.builder().id(bookDTO).build());
		return BookDTO.builder()
				.id(book.getId())
				.livro_db(book.getLivro_db())
				.title(book.getTitle())
				.isbn(book.getIsbn())
				.author(book.getAuthor())
				.yearPublication(book.getYearPublication())
				.priceSale(book.getPriceSale())
				.availableQuantity(book.getAvailableQuantity())
				.bookCategories(listaCat)
				.synopsis(book.getSynopsis())
				.build();
	}

	public static List<BookDTO> fromAll(List<Book> books) {
		return books.stream().map(BookDTO::from).collect(Collectors.toList());
	}
	public static Page<BookDTO> fromPage(Page<Book> page) {
		return page.map(BookDTO::from);
	}

}
