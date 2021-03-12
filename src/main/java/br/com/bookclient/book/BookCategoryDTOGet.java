package br.com.bookclient.book;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.bookclient.bookcategory.BookCategory;
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
public class BookCategoryDTOGet {

	@NotNull
	@Positive
	private Long id;

	private String name;

	public static BookCategoryDTOGet from(BookCategory find) {
			return BookCategoryDTOGet
					.builder()
					.id(find.getId())
					.name(find.getName())
					.build();
	}

}
