package it.euris.academy.EsameFinaleJavaAcademy2023.controller;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@WebMvcTest(MovieTheatreController.class)
@TestPropertySource(locations = "classpath:application-test.properties")
public class MovieTheatreControllerTest {
}
