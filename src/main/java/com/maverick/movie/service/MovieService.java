package com.maverick.movie.service;

import com.maverick.movie.controller.request.MovieRequest;
import com.maverick.movie.controller.response.MovieResponse;
import com.maverick.movie.mapper.MovieMapper;
import com.maverick.movie.model.Movie;
import com.maverick.movie.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieService {
    private MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<MovieResponse> searchMoviesByTitle(String title) {
        List<Movie> movie = movieRepository.findByTitle(title);
        return movie.stream().map(movie1 -> MovieMapper.modelToResponse(movie1))
                .collect(Collectors.toList());

    }

    public MovieResponse addMovie(MovieRequest request) {
        Movie movie = MovieMapper.requestToModel(request);
        movieRepository.save(movie);
        return MovieMapper.modelToResponse(movie);
    }
}
