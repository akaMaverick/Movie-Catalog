package com.maverick.movie.stub;

import com.maverick.movie.enums.Genre;
import com.maverick.movie.enums.StreamAvailable;
import com.maverick.movie.model.Movie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MovieStub {
    public static List<Movie> listToTestServiceStub() {
        List<Movie> list = new ArrayList<>();
        list.add(MovieStub.createStub());
        list.add(MovieStub.createSecondStub());
        return list;
    }

    public static Movie createStub() {
        Movie model = new Movie();
        model.setId("12345");
        model.setTitle("Long dream");
        model.setDescription("It's about a long dream.");
        model.setDirector("Marlon Maverick");
        model.setGenre(Genre.DRAMA);
        model.setRate(8.5);
        model.setStreamAvailableList(Arrays.asList(StreamAvailable.NETFLIX));
        return model;
    }

    public static Movie createSecondStub() {
        Movie model = new Movie();
        model.setId("54321");
        model.setTitle("Long Life");
        model.setDescription("It's a long life");
        model.setDirector("Kirk Johnson");
        model.setGenre(Genre.COMEDY);
        model.setRate(7.3);
        model.setStreamAvailableList(Arrays.asList(StreamAvailable.HULU));
        return model;
    }
}
