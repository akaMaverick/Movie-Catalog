package com.maverick.movie.service;

import com.maverick.movie.controller.response.MovieResponse;
import com.maverick.movie.enums.Genre;
import com.maverick.movie.model.Movie;
import com.maverick.movie.repository.MovieRepository;
import com.maverick.movie.stub.MovieRequestStub;
import com.maverick.movie.stub.MovieStub;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.assertj.core.api.Assertions.tuple;
import static org.mockito.Mockito.when;

public class MovieServiceTest {

    @InjectMocks
    private MovieService movieService;

    @Mock
    private MovieRepository movieRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);

    }


    @Test
    void whenMovieService_invokeSearchMoviesByTitle_returnListMovieResponse() {
        when(movieRepository.findMoviesByTitle(Mockito.anyString())).thenReturn(MovieStub.listToTestServiceStub());

        List<MovieResponse> response = movieService.searchMoviesByTitle(Mockito.anyString());

        Assertions.assertThat(response)
                .isNotEmpty()
                .hasSize(2)
                .extracting(MovieResponse::getTitle, MovieResponse::getRate, MovieResponse::getGenre,
                        MovieResponse::getDirector)
                .containsExactlyInAnyOrder(
                        tuple("Long dream", 8.5, Genre.DRAMA, "Marlon Maverick"),
                        tuple("Long Life", 7.3, Genre.COMEDY, "Kirk Johnson")

                );
    }

    @Test
    @DisplayName("Test a successfully added movie.")
    void whenMovieService_invokeAddMovie_saveSuccessfulMovieResponse() {
        Movie movie = MovieStub.createStub();

        when(movieRepository.save(movie)).thenReturn(movie);

        MovieResponse response = movieService.addMovie(MovieRequestStub.createStubServiceTest());

        Assertions.assertThat(response)
                .isNotNull()
                .hasFieldOrPropertyWithValue("title", "Long dream")
                .hasFieldOrPropertyWithValue("genre", Genre.DRAMA)
                .hasFieldOrPropertyWithValue("description", "It's about a long dream.")
                .hasFieldOrPropertyWithValue("rate", 8.5)
                .hasFieldOrPropertyWithValue("director", "Marlon Maverick");
    }
}
