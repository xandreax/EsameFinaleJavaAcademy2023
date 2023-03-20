package it.euris.academy.EsameFinaleJavaAcademy2023.service;

import it.euris.academy.EsameFinaleJavaAcademy2023.model.Film;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class FilmServiceIntegrationTest {

    @Autowired
    private FilmService filmService;

    @Test
    void test_getById(){
        String title = "titolo";
        String author = "autore";
        String producer = "produttore";
        String genre = "horror";
        int minimum_age = 16;
        int duration = 120;

        filmService.deleteAll();
        Optional<Film> response = filmService.getById(1);
        Assertions.assertTrue(response.isEmpty());

        Film filmInserted = filmService.insert(title, author, producer, genre, minimum_age, duration);
        response = filmService.getById(filmInserted.getId());
        Assertions.assertTrue(response.isPresent());
        Assertions.assertEquals(title, response.get().getTitle());
        Assertions.assertEquals(author, response.get().getAuthor());
        Assertions.assertEquals(producer, response.get().getProducer());
        Assertions.assertEquals(genre, response.get().getGenre());
        Assertions.assertEquals(minimum_age, response.get().getMinimumAge());
        Assertions.assertEquals(duration, response.get().getDuration());
    }
}
