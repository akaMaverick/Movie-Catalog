package com.maverick.movie.stub;

import com.maverick.movie.controller.response.MovieResponse;
import com.maverick.movie.enums.Genre;
import com.maverick.movie.enums.StreamAvailable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MovieResponseStub {

    public static List<MovieResponse> listToTestControllerStub() {
        List<MovieResponse> list = new ArrayList<>();
        list.add(createStub());
        list.add(createSecondStub());
        return list;
    }

    public static MovieResponse createStub() {
        MovieResponse response = new MovieResponse();
        response.setId("12345");
        response.setTitle("Amazing Life");
        response.setDescription("It's about a life.");
        response.setDirector("Marlon Maverick");
        response.setGenre(Genre.DRAMA);
        response.setRate(8.5);
        response.setStreamAvailableList(Arrays.asList(StreamAvailable.NETFLIX));
        return response;
    }

    public static MovieResponse createSecondStub() {
        MovieResponse response = new MovieResponse();
        response.setId("54321");
        response.setTitle("Amazing Train");
        response.setDescription("It's about a train");
        response.setDirector("Kirk Johnson");
        response.setGenre(Genre.COMEDY);
        response.setRate(7.3);
        response.setStreamAvailableList(null);
        return response;
    }
}
