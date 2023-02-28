package ru.job4j.accident.controller;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.accident.Main;
import ru.job4j.accident.model.User;
import ru.job4j.accident.service.UserService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
class RegistrationControllerTest {

    @MockBean
    private UserService userService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser
    void whenGetRegistrationPage() throws Exception {
        this.mockMvc.perform(get("/users/registration"))
                .andExpect(status().isOk())
                .andExpect(view().name("registration"));
    }

    @Test
    @WithMockUser
    void whenGetRegistrationFailResult() throws Exception {
        this.mockMvc.perform(get("/users/fail"))
                .andExpect(status().isOk())
                .andExpect(view().name("registrationFail"));
    }

    @Test
    @WithMockUser
    void whenRegisterThenSuccess() throws Exception {
        this.mockMvc.perform(post("/users/register")
                .param("username", "Denis")
                .param("password", "password"))
                .andExpect(status().is3xxRedirection());
        ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);
        verify(userService).save(userArgumentCaptor.capture());
        assertThat(userArgumentCaptor.getValue().getUsername()).isEqualTo("Denis");
    }
}