package br.com.bookclient.bookcategory;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.data.domain.Page;

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
public class BookCategoryDTO {

	private Long id;
	
	@NotEmpty
	@NotNull
	private String name;

	
	public static BookCategoryDTO from(BookCategory bookCategory) {
		return BookCategoryDTO
				.builder()
				.id(bookCategory.getId())
				.name(bookCategory.getName())
				.build();
	}

	public static List<BookCategoryDTO> fromAll(List<BookCategory> list) {
	        return list.stream().map(BookCategoryDTO::from).collect(Collectors.toList());
	}
	
	public static Page<BookCategoryDTO> fromPage(Page<BookCategory> page) {
		return page.map(BookCategoryDTO::from);
	}

}
