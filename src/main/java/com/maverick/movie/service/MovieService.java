package com.maverick.movie.service;

import com.maverick.movie.controller.request.MovieRequest;
import com.maverick.movie.controller.response.MovieResponse;
import com.maverick.movie.mapper.MovieMapper;
import com.maverick.movie.model.Movie;
import com.maverick.movie.repository.MovieRepository;
import org.springframework.stereotype.Service;

@Service
public class MovieService {
    private MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public MovieResponse addMovie(MovieRequest request) {
        Movie movie = MovieMapper.requestToModel(request);
        movieRepository.save(movie);
        return MovieMapper.modelToResponse(movie);
    }
}
