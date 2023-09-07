package com.maverick.movie.controller;

import com.maverick.movie.controller.request.MovieRequest;
import com.maverick.movie.controller.response.MovieResponse;
import com.maverick.movie.service.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping(value = "/movies")
public class MovieController {
    private static final Logger log = Logger.getLogger(MovieController.class.getName());
    private MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @Operation(summary= "Search for movies", description = "Search for movies with similar titles to the word that will be provided.")
    @GetMapping("{title}")
    public List<MovieResponse> searchMoviesByTitle(@PathVariable String title) {
        log.info("Searching for movies with similarities with " + title);
        return movieService.searchMoviesByTitle(title);
    }

    @Operation(summary= "Add movie", description = "Add movie with the information attributes given.")
    @PostMapping
    public MovieResponse addMovie(@RequestBody MovieRequest movieRequest) {
        return movieService.addMovie(movieRequest);
    }

    @Operation(summary= "Update stream availability", description = "Update the streaming availability for the movie " +
            "with the provided title and include the streaming platform.")
    @PutMapping("/update/{title}")
    public MovieResponse updateStreamAvailability(@PathVariable String title, @RequestParam String stream) {
        log.info("Updating stream availability of " + title);
        return movieService.updateStreamAvailability(title, stream);
    }
}
