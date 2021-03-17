package br.com.bookclient.book;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

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
public class BookDTOFromCategory {

	private Long id;

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

	public static BookDTOFromCategory from(Book book) {
		return BookDTOFromCategory.builder()
//				.id(book.getId())
				.title(book.getTitle())
				.isbn(book.getIsbn())
				.author(book.getAuthor())
				.yearPublication(book.getYearPublication())
				.priceSale(book.getPriceSale())
				.availableQuantity(book.getAvailableQuantity())
				.synopsis(book.getSynopsis())
				.build();
	}

	public static List<BookDTOFromCategory> fromAll(List<Book> books) {
		return books.stream().map(BookDTOFromCategory::from).collect(Collectors.toList());
	}
	public static Page<BookDTOFromCategory> fromPage(Page<Book> page) {
		return page.map(BookDTOFromCategory::from);
	}

}
