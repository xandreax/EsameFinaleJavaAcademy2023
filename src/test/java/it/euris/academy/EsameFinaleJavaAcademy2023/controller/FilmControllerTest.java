package it.euris.academy.EsameFinaleJavaAcademy2023.controller;

import it.euris.academy.EsameFinaleJavaAcademy2023.model.Film;
import it.euris.academy.EsameFinaleJavaAcademy2023.service.FilmService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

@ExtendWith(SpringExtension.class)
@WebMvcTest(FilmController.class)
@TestPropertySource(locations = "classpath:application-test.properties")
class FilmControllerTest {
    @Autowired
    private MockMvc client;

    @MockBean
    private FilmService filmService;

    @Test
    void test_getById() throws Exception{
        int id_film = 1;
        String title = "titolo";
        String author = "autore";
        String producer = "produttore";
        String genre = "horror";
        int minimum_age = 16;
        int duration = 120;
        Film film = Film.builder()
                .id(id_film)
                .title(title)
                .author(author)
                .producer(producer)
                .genre(genre)
                .minimumAge(minimum_age)
                .duration(duration)
                .build();

        Mockito.when(filmService.getById(Mockito.anyInt())).thenReturn(Optional.of(film));

        client.perform(MockMvcRequestBuilders.get("/film/" + id_film))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(id_film))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value(title))
                .andExpect(MockMvcResultMatchers.jsonPath("$.author").value(author))
                .andExpect(MockMvcResultMatchers.jsonPath("$.producer").value(producer))
                .andExpect(MockMvcResultMatchers.jsonPath("$.genre").value(genre))
                .andExpect(MockMvcResultMatchers.jsonPath("$.minimumAge").value(minimum_age))
                .andExpect(MockMvcResultMatchers.jsonPath("$.duration").value(duration));
    }

}
