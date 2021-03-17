package br.com.bookclient.book;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.Valid;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder(builderClassName = "Builder")
@Table(name = "TB_Book")
public class Book implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BookSeq")
	@SequenceGenerator(name = "BookSeq", sequenceName = "BOOK_SEQ", allocationSize = 1)
	private Long id;

	@Column(nullable = false, unique = true  )
	private UUID livro_db;

	private String title;
	private String synopsis;
	private String isbn;
	private String author;
	private LocalDate yearPublication;
	private Float priceSale;
	private Integer availableQuantity;

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "tb_book_book_categories", joinColumns = @JoinColumn(name = "book_id"))
	@Column(name = "bookCategories_id")
	private List<Long> bookCategories;

	public static Book to(@Valid BookDTO bookDTO) {
		List<Long> listaCatLivro = new ArrayList<Long>();
		for (BookCategoryDTOGet bookCategoryDTOGet : bookDTO.getBookCategories())
			listaCatLivro.add(bookCategoryDTOGet.getId());
		System.out.println(listaCatLivro);
		return Book.builder()
				.id(bookDTO.getId())
				.livro_db(bookDTO.getLivro_db())
				.title(bookDTO.getTitle())
				.isbn(bookDTO.getIsbn())
				.author(bookDTO.getAuthor())
				.yearPublication(bookDTO.getYearPublication())
				.priceSale(bookDTO.getPriceSale())
				.availableQuantity(bookDTO.getAvailableQuantity())
				.bookCategories(listaCatLivro)
				.synopsis(bookDTO.getSynopsis())
				.build();
	}

}
