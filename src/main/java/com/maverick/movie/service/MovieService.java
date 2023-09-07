package com.maverick.movie.service;

import com.maverick.movie.controller.request.MovieRequest;
import com.maverick.movie.controller.response.MovieResponse;
import com.maverick.movie.enums.StreamAvailable;
import com.maverick.movie.mapper.MovieMapper;
import com.maverick.movie.model.Movie;
import com.maverick.movie.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieService {
    private MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<MovieResponse> searchMoviesByTitle(String title) {
        List<Movie> movie = movieRepository.findMoviesByTitle(title);
        return movie.stream().map(movie1 -> MovieMapper.modelToResponse(movie1))
                .collect(Collectors.toList());

    }

    public MovieResponse addMovie(MovieRequest request) {
        Movie movie = MovieMapper.requestToModel(request);
        movieRepository.save(movie);
        return MovieMapper.modelToResponse(movie);
    }

    public MovieResponse updateStreamAvailability(String title, String stream){
        Movie movie = movieRepository.findByTitle(title);

        if(!stream.isEmpty()) {
            List<StreamAvailable> streamsList = movie.getStreamAvailableList() != null
                    ? movie.getStreamAvailableList() : new ArrayList<>();
            streamsList.add(StreamAvailable.valueOf(stream));
            List<StreamAvailable> streamUpdated = streamsList.stream().distinct().collect(Collectors.toList());
            movie.setStreamAvailableList(streamUpdated);
        }
        movieRepository.save(movie);
        return MovieMapper.modelToResponse(movie);
    }
}
