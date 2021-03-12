package br.com.bookclient.bookcategory;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.Valid;

import br.com.bookclient.book.Book;
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
@Table(name = "TB_BookCategory")
public class BookCategory  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BookCategorySeq")
	@SequenceGenerator(name = "BookCategorySeq", sequenceName = "BOOKCATEGORY_SEQ", allocationSize = 1)
	private Long id;

	private String name;

	@ManyToMany(mappedBy = "bookCategories", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	private List<Book> books;
	
	public static BookCategory  to(@Valid BookCategoryDTO bookCategoryDTO) {
		return BookCategory.builder()
				.id(bookCategoryDTO.getId())
				.name(bookCategoryDTO.getName())
				.build();
	}
	public static List<BookCategory> fromAll(List<BookCategoryDTO> list) {
        return list.stream().map(BookCategory::to).collect(Collectors.toList());
	}

}
