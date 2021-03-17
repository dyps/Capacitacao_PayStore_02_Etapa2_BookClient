package br.com.bookclient.bookcategory;

import static br.com.bookclient.bookcategory.builders.BookCategoryBuilder.createBookCategory;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.bookclient.bookcategory.service.UpdateBookCategoryServiceImpl;

@ExtendWith(MockitoExtension.class)
@Tag("service")
@DisplayName("Valida funcionalidade do serviço responsável por atualizar a categoria de livro")
public class UpdateBookCategoryServiceTest {

    @Mock
    private BookCategoryRepository bookcategoryRepository;
    private UpdateBookCategoryServiceImpl updateBookCategory;

    @BeforeEach
    public void setUp() {
        this.updateBookCategory = new UpdateBookCategoryServiceImpl(bookcategoryRepository);
    }

    @Test
    @DisplayName("Deve atualizar uma categoria de livro")
    void shouldUpdateBookCategory() { 

        when(bookcategoryRepository.findById(1L)).thenReturn(Optional.of(createBookCategory().id(1L).build()));

        updateBookCategory.update(createBookCategory().name("teste update").build(), 1L);

        ArgumentCaptor<BookCategory> captorBookCategory = ArgumentCaptor.forClass(BookCategory.class);
        verify(bookcategoryRepository).save(captorBookCategory.capture());
        
        BookCategory result = captorBookCategory.getValue();

        assertAll("bookcategory",
                () -> assertThat(result.getName(), is("teste update"))
        );
    }
}

