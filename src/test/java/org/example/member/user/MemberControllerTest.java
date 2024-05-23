package org.example.member.user;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.example.beforeLogin.MemberVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MemberService memberService;

    private String username;
    private String password;
    private String notUserName;

    @BeforeEach
    void setUp() {
        username = "bnm";
        password = "new_Password";
        notUserName = "nonono";
    }

    @Test
    void success_changePasswordPage() throws Exception {

        //Given
        BDDMockito.given(memberService.updatePassword(username, password)).willReturn(true);

        //When&Then
        mockMvc .perform(post("/changePasswordPage")
                .with(user(username))
                .param("password", password))
                .andExpect(status().isOk())
                .andExpect(view().name("redirect:/myPage"));
    }
}