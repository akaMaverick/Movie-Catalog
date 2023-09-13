package com.maverick.movie.stub;

import com.maverick.movie.controller.request.MovieRequest;
import com.maverick.movie.enums.Genre;

public class MovieRequestStub {
    public static MovieRequest createStub() {
        MovieRequest request = new MovieRequest();
        request.setTitle("Amazing Life");
        request.setDescription("It's about a life.");
        request.setDirector("Marlon Maverick");
        request.setGenre(Genre.DRAMA);
        request.setRate(8.5);
        return request;
    }

    public static MovieRequest createStubServiceTest() {
        MovieRequest request = new MovieRequest();
        request.setTitle("Long dream");
        request.setDescription("It's about a long dream.");
        request.setDirector("Marlon Maverick");
        request.setGenre(Genre.DRAMA);
        request.setRate(8.5);
        return request;
    }
}
