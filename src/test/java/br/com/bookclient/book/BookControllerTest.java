package br.com.bookclient.book;

import static br.com.bookclient.book.builders.BookBuilder.createBook;
import static br.com.bookclient.book.builders.BookCategoryBuilder.createBookCategory;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.UUID;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import br.com.bookclient.book.service.DeleteBookService;
import br.com.bookclient.book.service.GetBookByUUIDService;
import br.com.bookclient.book.service.GetBookService;
import br.com.bookclient.book.service.ListBookService;
import br.com.bookclient.book.service.ListPageBookService;
import br.com.bookclient.book.service.SaveBookService;
import br.com.bookclient.book.service.UpdateBookService;
import br.com.bookclient.book.v1.BookControllerV1;
import br.com.bookclient.bookcategory.service.GetBookCategoryService;

@Tag("Controller")
@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = BookControllerV1.class)
@DisplayName("Valida funcionalidade do Controller Livro")
public class BookControllerTest{

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetBookService getBookService;
    @MockBean
    private ListBookService listBookService;
    @MockBean
    private ListPageBookService listPageBookService;
    @MockBean
    private SaveBookService saveBookService;
    @MockBean
    private UpdateBookService updateBookService;
    @MockBean
    private DeleteBookService deleteBookService;
    @MockBean
    private GetBookCategoryService getBookCategoryService;
    @MockBean
    private GetBookByUUIDService getBookByUUIDService;

    @Test
    @DisplayName("Pesquisa livro por id")
    void whenValidGetIdBook_thenReturnsBook() throws Exception { //pesquisa por livro
        when(getBookService.find(1L)).thenReturn(createBook().id(1L).build());

        mockMvc.perform(get("/v1/api/book/{id}", 1L)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("teste titulo")))
                .andExpect(jsonPath("$.isbn", is("1234")))
                .andExpect(jsonPath("$.synopsis", is("teste sinopse")))
                .andExpect(jsonPath("$.author", is("yaggo")))
                .andExpect(jsonPath("$.yearPublication", is("2020-02-02")))
                .andExpect(jsonPath("$.priceSale", is(5.0)))
                .andExpect(jsonPath("$.availableQuantity", is(2)))
                .andExpect(jsonPath("$.bookCategories[0].id", is(1)))
                .andExpect(jsonPath("$.bookCategories[0].name", is("Ação")))
                ;
    }
    @Test
    @DisplayName("Pesquisa livro por UUID")
    void whenValidGetUUIdBook_thenReturnsBook() throws Exception { 
    	UUID uuid = UUID.randomUUID();
        when(getBookByUUIDService.find(uuid)).thenReturn(createBook().livro_db(uuid).build());

        mockMvc.perform(get("/v1/api/book/UUID/{uuid}", uuid)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                
                .andExpect(jsonPath("$.livro_db", is(uuid.toString())))
                .andExpect(jsonPath("$.title", is("teste titulo")))
                .andExpect(jsonPath("$.isbn", is("1234")))
                .andExpect(jsonPath("$.synopsis", is("teste sinopse")))
                .andExpect(jsonPath("$.author", is("yaggo")))
                .andExpect(jsonPath("$.yearPublication", is("2020-02-02")))
                .andExpect(jsonPath("$.priceSale", is(5.0)))
                .andExpect(jsonPath("$.availableQuantity", is(2)))
                .andExpect(jsonPath("$.bookCategories[0].id", is(1)))
                .andExpect(jsonPath("$.bookCategories[0].name", is("Ação")))
                ;
    }
    @Test
    @DisplayName("Pesquisa livro por categoria")
    void whenValidListBookByCategory_thenReturnsBook() throws Exception { //pesquisa por livro de categoria

        when(getBookCategoryService.find(1L)).thenReturn(createBookCategory().id(1L).build());

        mockMvc.perform(get("/v1/api/book/categoria/{id}", 1L)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title", is("teste titulo")))
                .andExpect(jsonPath("$[0].isbn", is("1234")))
                .andExpect(jsonPath("$[0].synopsis", is("teste sinopse")))
                .andExpect(jsonPath("$[0].author", is("yaggo")))
                .andExpect(jsonPath("$[0].yearPublication", is("2020-02-02")))
                .andExpect(jsonPath("$[0].priceSale", is(5.0)))
                .andExpect(jsonPath("$[0].availableQuantity", is(2)))
                ;
    }

	@Test
    @DisplayName("Pesquisa lista de livros")
    void whenValidListBook_thenReturnsBook() throws Exception { //pesquisa todos os livro

        when(listBookService.findAll()).thenReturn(Lists.newArrayList(
                createBook().id(1L).build(), createBook().id(2L).build()
        ));

        mockMvc.perform(get("/v1/api/book")
        		
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$[*]", hasSize(2)))
                
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].title", is("teste titulo")))
                .andExpect(jsonPath("$[0].isbn", is("1234")))
                .andExpect(jsonPath("$[0].synopsis", is("teste sinopse")))
                .andExpect(jsonPath("$[0].author", is("yaggo")))
                .andExpect(jsonPath("$[0].yearPublication", is("2020-02-02")))
                .andExpect(jsonPath("$[0].priceSale", is(5.0)))
                .andExpect(jsonPath("$[0].availableQuantity", is(2)))
                .andExpect(jsonPath("$[0].bookCategories[0].id", is(1)))
                .andExpect(jsonPath("$[0].bookCategories[0].name", is("Ação")))
                
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].title", is("teste titulo")))
                .andExpect(jsonPath("$[1].isbn", is("1234")))
                .andExpect(jsonPath("$[1].synopsis", is("teste sinopse")))
                .andExpect(jsonPath("$[1].author", is("yaggo")))
                .andExpect(jsonPath("$[1].yearPublication", is("2020-02-02")))
                .andExpect(jsonPath("$[1].priceSale", is(5.0)))
                .andExpect(jsonPath("$[1].availableQuantity", is(2)))
                .andExpect(jsonPath("$[1].bookCategories[0].id", is(1)))
                .andExpect(jsonPath("$[1].bookCategories[0].name", is("Ação")))
                ;
    }

    @Test
    @DisplayName("Pesquisa livro com paginação")
    void whenValidListPageBook_thenReturnsBookPage() throws Exception { //pesquisa todos os livro
        Page<BookDTO> bookPage = new PageImpl<>(Collections.singletonList(createBook().id(1L).build()));

        Pageable pageable = PageRequest.of(0,2);
		when(listPageBookService.findPage("", pageable )).thenReturn(bookPage);

        mockMvc.perform(get("/v1/api/book/search?page=0&size=2")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                
                .andExpect(jsonPath("$.content[0].id", is(1)))
                .andExpect(jsonPath("$.content[0].title", is("teste titulo")))
                .andExpect(jsonPath("$.content[0].isbn", is("1234")))
                .andExpect(jsonPath("$.content[0].synopsis", is("teste sinopse")))
                .andExpect(jsonPath("$.content[0].author", is("yaggo")))
                .andExpect(jsonPath("$.content[0].yearPublication", is("2020-02-02")))
                .andExpect(jsonPath("$.content[0].priceSale", is(5.0)))
                .andExpect(jsonPath("$.content[0].availableQuantity", is(2)))
                .andExpect(jsonPath("$.content[0].bookCategories[0].id", is(1)))
                .andExpect(jsonPath("$.content[0].bookCategories[0].name", is("Ação")))
                ;
    }

    @Test
    @DisplayName("Salva um livro")
    void whenValidSaveBook_thenReturns201() throws Exception { //insere livro
        mockMvc.perform(post("/v1/api/book")
                .contentType(MediaType.APPLICATION_JSON)
                .content(readJson("bookDTO.json")))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("Edita um livro")
    void whenValidUpdateBook_thenReturns204() throws Exception { //atualiza um livro
        mockMvc.perform(put("/v1/api/book/{id}", 1L)
                .content(readJson("bookUpdate.json"))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("Deleta livro")
    void whenValidDelete_thenReturns204() throws Exception { // deleta livro
        mockMvc.perform(delete("/v1/api/book/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }	

    public static String readJson(String file) throws Exception {
        byte[] bytes = Files.readAllBytes(Paths.get("src/test/java/resources/json/" + file).toAbsolutePath());
        return new String(bytes);
    }
}
