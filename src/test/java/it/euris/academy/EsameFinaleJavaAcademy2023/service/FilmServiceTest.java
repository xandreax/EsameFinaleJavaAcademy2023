package it.euris.academy.EsameFinaleJavaAcademy2023.service;

import it.euris.academy.EsameFinaleJavaAcademy2023.model.Film;
import it.euris.academy.EsameFinaleJavaAcademy2023.repository.FilmRepository;
import it.euris.academy.EsameFinaleJavaAcademy2023.service.impl.FilmServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@TestPropertySource(locations = "classpath:application-test.properties")
class FilmServiceTest {

    private FilmService filmService;

    @Mock
    private FilmRepository filmRepository;

    @BeforeEach
    public void init(){
        filmService = new FilmServiceImpl(filmRepository);
    }

    @Test
    void test_getById(){
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

        Mockito.when(filmRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(film));
        Optional<Film> response = filmService.getById(film.getId());
        Assertions.assertNotNull(response.get());
        Assertions.assertEquals(title, response.get().getTitle());
        Assertions.assertEquals(author, response.get().getAuthor());
        Assertions.assertEquals(producer, response.get().getProducer());
        Assertions.assertEquals(genre, response.get().getGenre());
        Assertions.assertEquals(minimum_age, response.get().getMinimumAge());
        Assertions.assertEquals(duration, response.get().getDuration());
    }

}
