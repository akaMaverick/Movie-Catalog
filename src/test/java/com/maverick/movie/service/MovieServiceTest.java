package com.maverick.movie.service;

import com.maverick.movie.controller.response.MovieResponse;
import com.maverick.movie.enums.Genre;
import com.maverick.movie.enums.StreamAvailable;
import com.maverick.movie.model.Movie;
import com.maverick.movie.repository.MovieRepository;
import com.maverick.movie.stub.MovieRequestStub;
import com.maverick.movie.stub.MovieStub;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.assertj.core.api.Assertions.tuple;
import static org.mockito.Mockito.*;

public class MovieServiceTest {

    private MovieService movieService;

    private MovieRepository movieRepository;

    @BeforeEach
    public void setUp() {
        this.movieRepository = mock(MovieRepository.class);
        this.movieService = new MovieService(movieRepository);
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

    @Test
    @DisplayName("Test an update of a stream.")
    void whenMovieService_invokeUpdateStreamAvailability_updateNullListSuccessfully() {
        Movie movie = MovieStub.createStub();
        movie.setStreamAvailableList(null);

        when(movieRepository.findByTitle(Mockito.anyString())).thenReturn(movie);

        MovieResponse response = movieService.updateStreamAvailability("Long dream", "HULU");

        Assertions.assertThat(response)
                .isNotNull()
                .hasFieldOrPropertyWithValue("title", "Long dream")
                .extracting("streamAvailableList")
                .asList()
                .contains(StreamAvailable.HULU);

        verify(movieRepository, atMostOnce()).findByTitle(Mockito.anyString());
    }
}
