package br.com.bookclient.book;

import static br.com.bookclient.book.builders.BookBuilder.createBook;
import static org.hamcrest.CoreMatchers.any;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.verify;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.bookclient.book.service.GetCategoryFromBook;
import br.com.bookclient.book.service.SaveBookServiceImpl;

@ExtendWith(MockitoExtension.class)
@Tag("service")
@DisplayName("Valida funcionalidade do serviço responsável por salvar um livro")
public class SaveBookServiceTest {

    @Mock
    private BookRepository bookRepository;
    private SaveBookServiceImpl saveBook;
    @Mock
	private GetCategoryFromBook getCategoryFromBook;

    @BeforeEach
    public void setUp() {
        this.saveBook = new SaveBookServiceImpl(bookRepository, getCategoryFromBook);
    }

    @Test
    @DisplayName("Deve salvar um livro")
    void shouldSaveBook() { 

        saveBook.insert(Book.to(createBook().build()));
        ArgumentCaptor<Book> captorBook = ArgumentCaptor.forClass(Book.class);
        verify(bookRepository).save(captorBook.capture());
        Book result = captorBook.getValue();
        assertAll("book",
        		() -> assertThat(result.getLivro_db(), is(any(UUID.class))),
        		() -> assertThat(result.getTitle(), is("teste titulo")),
        		() -> assertThat(result.getAuthor(), is("yaggo")),
        		() -> assertThat(result.getAvailableQuantity(), is(2)),
        		() -> assertThat(result.getIsbn(), is("1234")),
        		() -> assertThat(result.getPriceSale(), is(5F)),
        		() -> assertThat(result.getSynopsis(), is("teste sinopse")),
        		() -> assertThat(result.getBookCategories().get(0), is(1L)),
        		() -> assertThat(result.getYearPublication().toString(), is("2020-02-02"))
        );
    }
}
