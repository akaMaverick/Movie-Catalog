package com.maverick.movie.controller;

import com.maverick.movie.controller.request.MovieRequest;
import com.maverick.movie.controller.response.MovieResponse;
import com.maverick.movie.service.MovieService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/movies")
public class MovieController {

    private MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping
    public MovieResponse addMovie(@RequestBody MovieRequest movieRequest) {
        return movieService.addMovie(movieRequest);
    }
}
