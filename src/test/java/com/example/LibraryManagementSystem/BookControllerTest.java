package com.example.LibraryManagementSystem;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.LibraryManagementSystem.model.Book;
import com.example.LibraryManagementSystem.service.BookService;

import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService BookService;

    @Test
    void testAddBook() throws Exception {
        Book book = new Book();
        book.setBookId(1);
        book.setTitle("Book");
        book.setAuthor("Author");
        book.setIsbn(1234567890123L);
        book.setPublishedDate("2023-01-01");
        book.setAvailableCopies(10);

        when(BookService.addBook(any(Book.class))).thenReturn(book);

        mockMvc.perform(post("/books/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"title\": \"Book\", \"author\": \"Author\", \"isbn\": 1234567890123, \"publishedDate\": \"2023-01-01\", \"availableCopies\": 10 }"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("Book"))
                .andExpect(jsonPath("$.author").value("Author"))
                .andExpect(jsonPath("$.isbn").value(1234567890123L));
    }

    @Test
    void testViewBooks() throws Exception {
        Book book1 = new Book();
        book1.setBookId(1);
        book1.setTitle("Book 1");
        book1.setAuthor("Author 1");
        book1.setIsbn(1111111111111L);
        book1.setPublishedDate("2023-01-01");
        book1.setAvailableCopies(5);

        Book book2 = new Book();
        book2.setBookId(2);
        book2.setTitle("Book 2");
        book2.setAuthor("Author 2");
        book2.setIsbn(2222222222222L);
        book2.setPublishedDate("2023-02-01");
        book2.setAvailableCopies(3);

        when(BookService.findAllBooks()).thenReturn(Arrays.asList(book1, book2));

        mockMvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Book 1"))
                .andExpect(jsonPath("$[1].isbn").value(2222222222222L));
    }


    @Test
    void testUpdateBook() throws Exception {
        Book book = new Book();
        book.setBookId(1);
        book.setTitle("Updated Book");
        book.setAuthor("Updated Author");
        book.setIsbn(1234567890123L);

        when(BookService.updateBook(eq(1), any(Book.class))).thenReturn(book);

        mockMvc.perform(put("/book/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"title\": \"Updated Book\", \"author\": \"Updated Author\", \"isbn\": 1234567890123, \"publishedDate\": \"2023-01-01\", \"availableCopies\": 10 }"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Updated Book"))
                .andExpect(jsonPath("$.author").value("Updated Author"));
    }

    @Test
    void testDeleteBook() throws Exception {
        mockMvc.perform(delete("/book/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Book ID 1 is deleted successfully"));

        verify(BookService, times(1)).deleteBook(1);
    }
}
