package com.maverick.movie.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.maverick.movie.controller.request.MovieRequest;
import com.maverick.movie.controller.response.MovieResponse;
import com.maverick.movie.enums.Genre;
import com.maverick.movie.enums.StreamAvailable;
import com.maverick.movie.service.MovieService;
import com.maverick.movie.stub.MovieRequestStub;
import com.maverick.movie.stub.MovieResponseStub;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(MovieController.class)
public class MovieControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MovieService movieService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Successful search of movies based in the title provided.")
    void searchMoviesByTitleTest() throws Exception {

        Mockito.when(movieService.searchMoviesByTitle("Amazing")).thenReturn(MovieResponseStub.listToTestControllerStub());

        mockMvc.perform(MockMvcRequestBuilders.get("/movies/Amazing"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title").value("Amazing Life"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].description").value("It's about a life."))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].director").value("Marlon Maverick"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].genre").value(Genre.DRAMA.toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].rate").value(8.5))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].title").value("Amazing Train"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].director").value("Kirk Johnson"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].genre").value(Genre.COMEDY.toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].rate").value(7.3));

        Mockito.verify(movieService, Mockito.atMostOnce()).searchMoviesByTitle("Amazing");


    }

    @Test
    @DisplayName("Successful save of a new movie.")
    void addMovieTest() throws Exception {
        MovieRequest request = MovieRequestStub.createStub();
        MovieResponse response = MovieResponseStub.createStub();
        response.setStreamAvailableList(null);

        Mockito.when(movieService.addMovie(request)).thenReturn(response);

        MockHttpServletRequestBuilder requestBody = MockMvcRequestBuilders.post("/movies")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request));


        mockMvc.perform(requestBody)
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());

    }

    @Test
    @DisplayName("Successful stream update.")
    void updateStreamAvailabilityTest() throws Exception {
        MovieResponse response = MovieResponseStub.createStub();

        Mockito.when(movieService.updateStreamAvailability(Mockito.any(), Mockito.any())).thenReturn(response);

        mockMvc.perform(MockMvcRequestBuilders.put("/movies/update/{title}?stream=NETFLIX", "Amazing Life"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Amazing Life"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("It's about a life."))
                .andExpect(MockMvcResultMatchers.jsonPath("$.director").value("Marlon Maverick"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.genre").value(Genre.DRAMA.toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.rate").value(8.5))
                .andExpect(MockMvcResultMatchers.jsonPath("$.streamAvailableList[0]").value(StreamAvailable.NETFLIX.toString()));

    }
}
