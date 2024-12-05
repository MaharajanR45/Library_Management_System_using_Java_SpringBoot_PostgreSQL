package com.example.LibraryManagementSystem;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.LibraryManagementSystem.model.Borrow;
import com.example.LibraryManagementSystem.service.BorrowService;

import java.time.LocalDate;
import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class BorrowControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BorrowService BorrowService;

    @Test
    void testBorrowBook() throws Exception {
        Borrow borrow = new Borrow();
        borrow.setId(1);
        borrow.setBorrowDate(LocalDate.now());
        borrow.setDueDate(LocalDate.now().plusDays(7));

        when(BorrowService.borrowBook(1, 1)).thenReturn(borrow);

        mockMvc.perform(post("/borrow")
                .param("bookId", "1")
                .param("memberId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void testViewBorrows() throws Exception {
        Borrow borrow1 = new Borrow();
        borrow1.setId(1);
        borrow1.setBorrowDate(LocalDate.now());
        borrow1.setDueDate(LocalDate.now().plusDays(7));

        Borrow borrow2 = new Borrow();
        borrow2.setId(2);
        borrow2.setBorrowDate(LocalDate.now());
        borrow2.setDueDate(LocalDate.now().plusDays(14));

        when(BorrowService.viewBorrows()).thenReturn(Arrays.asList(borrow1, borrow2));

        mockMvc.perform(get("/borrows"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[1].dueDate").value(LocalDate.now().plusDays(14).toString()));
    }

    @Test
    void testViewBorrowById() throws Exception {
        Borrow borrow = new Borrow();
        borrow.setId(1);
        borrow.setBorrowDate(LocalDate.now());
        borrow.setDueDate(LocalDate.now().plusDays(7));

        when(BorrowService.viewBorrowsById(1)).thenReturn(borrow);

        mockMvc.perform(get("/borrow/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));     
    }

    @Test
    void testReturnBook() throws Exception {
        mockMvc.perform(delete("/return/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Book returned successfully."));

        verify(BorrowService, times(1)).returnBook(1);
    }
}
