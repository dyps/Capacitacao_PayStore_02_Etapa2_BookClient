package br.com.bookclient.bookcategory;

import static br.com.bookclient.bookcategory.builders.BookCategoryBuilder.createBookCategory;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.when;

import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import br.com.bookclient.bookcategory.service.ListPageBookCategoryServiceImpl;

@ExtendWith(MockitoExtension.class)
@Tag("service")
@DisplayName("Valida funcionalidade do serviço responsável por pesquisar categorias de livro por paginação")
public class ListPageBookCategoryServiceTest {

	@Mock
	private BookCategoryRepository bookcategoryRepository;
	private ListPageBookCategoryServiceImpl listPageBookCategory;

	@BeforeEach
	public void setUp() {
		this.listPageBookCategory = new ListPageBookCategoryServiceImpl(bookcategoryRepository);
	}

	@Test
	@DisplayName("Deve retornar todos as categoria de livros com paginação")
	void shouldFindAllBookCategory() {
		
		PageRequest pageable = PageRequest.of(0,2);
		when(listPageBookCategory.findPage("", pageable))
				.thenReturn(new PageImpl<>(Collections.nCopies(2, createBookCategory().build())));
		Page<BookCategory> userAppPage = listPageBookCategory.findPage("", pageable);
		assertAll("bookcategory", () -> assertThat(userAppPage.getNumber(), is(0)),
				() -> assertThat(userAppPage.getSize(), is(2)),
				
				() -> assertThat(userAppPage.getContent().get(0).getName(), is("Ação")),

				() -> assertThat(userAppPage.getContent().get(1).getName(), is("Ação"))
				);
	}

}
