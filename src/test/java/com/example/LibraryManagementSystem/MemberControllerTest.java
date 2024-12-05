package com.example.LibraryManagementSystem;

import com.example.LibraryManagementSystem.model.Member;
import com.example.LibraryManagementSystem.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MemberService MemberService;

    @Test
    void testAddMember() throws Exception {
        Member member = new Member();
        member.setMemberId(1);
        member.setName("Maharajan");
        member.setEmail("maharajan@gmail.com");

        when(MemberService.saveMember(any(Member.class))).thenReturn(member);

        mockMvc.perform(post("/members/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"name\": \"Maharajan\", \"email\": \"maharajan@gmail.com\" }"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Maharajan"))
                .andExpect(jsonPath("$.email").value("maharajan@gmail.com"));
    }

    @Test
    void testViewAllMembers() throws Exception {
        Member member1 = new Member();
        member1.setMemberId(1);
        member1.setName("Maharajan");
        member1.setEmail("maharajan@gmail.com");

        Member member2 = new Member();
        member2.setMemberId(2);
        member2.setName("Maharajan");
        member2.setEmail("maharajan@gmail.com");

        when(MemberService.viewMembers()).thenReturn(Arrays.asList(member1, member2));

        mockMvc.perform(get("/members"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Maharajan"))
                .andExpect(jsonPath("$[1].email").value("maharajan@gmail.com"));
    }

    @Test
    void testViewMemberById() throws Exception {
        Member member = new Member();
        member.setMemberId(1);
        member.setName("Maharajan");
        member.setEmail("maharajanr2003@gmail.com");

        when(MemberService.viewMemberById(1)).thenReturn(member);

        mockMvc.perform(get("/member/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Maharajan"))
                .andExpect(jsonPath("$.email").value("maharajanr2003@gmail.com"));
    }

    @Test
    void testUpdateMember() throws Exception {
        Member member = new Member();
        member.setMemberId(1);
        member.setName("Maharajan Updated");
        member.setEmail("maharajan@gmail.com");

        when(MemberService.updateMember(eq(1), any(Member.class))).thenReturn(member);

        mockMvc.perform(put("/member/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"name\": \"Maharajan Updated\", \"email\": \"maharajan@gmail.com\" }"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Maharajan Updated"))
                .andExpect(jsonPath("$.email").value("maharajan@gmail.com"));
    }
}

