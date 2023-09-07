package com.maverick.movie.mapper;

import com.maverick.movie.controller.request.MovieRequest;
import com.maverick.movie.controller.response.MovieResponse;
import com.maverick.movie.model.Movie;

public class MovieMapper {
    public static Movie requestToModel(MovieRequest request) {
        Movie movie = new Movie();
        movie.setTitle(request.getTitle());
        movie.setDescription(request.getDescription());
        movie.setDirector(request.getDirector());
        movie.setRate(request.getRate());
        movie.setGenre(request.getGenre());
        return movie;
    }

    public static MovieResponse modelToResponse(Movie model) {
        MovieResponse response = new MovieResponse();
        response.setId(model.getId());
        response.setTitle(model.getTitle());
        response.setDescription(model.getDescription());
        response.setDirector(model.getDirector());
        response.setRate(model.getRate());
        response.setGenre(model.getGenre());
        response.setStreamAvailableList(model.getStreamAvailableList());
        return response;
    }
}
