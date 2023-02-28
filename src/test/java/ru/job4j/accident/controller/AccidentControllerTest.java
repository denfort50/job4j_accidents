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
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.service.AccidentService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
class AccidentControllerTest {

    @MockBean
    private AccidentService accidentService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser
    void whenGetCreateForm() throws Exception {
        this.mockMvc.perform(get("/accidents/createAccident"))
                .andExpect(status().isOk())
                .andExpect(view().name("createAccident"));
    }

    @Test
    @WithMockUser
    void whenCreateThenSuccess() throws Exception {
        this.mockMvc.perform(post("/accidents/saveAccident")
                .param("id", "1")
                .param("name", "name")
                .param("text", "text")
                .param("address", "address")
                .param("type.id", "1")
                .param("rIds", "1")
                .param("rIds", "2"))
                .andExpect(status().is3xxRedirection());
        ArgumentCaptor<Accident> accidentArgumentCaptor = ArgumentCaptor.forClass(Accident.class);
        ArgumentCaptor<String[]> stringsArgumentCaptor = ArgumentCaptor.forClass(String[].class);
        verify(accidentService).createOrUpdate(accidentArgumentCaptor.capture(), stringsArgumentCaptor.capture());
        assertThat(accidentArgumentCaptor.getValue().getName()).isEqualTo("name");
    }
}