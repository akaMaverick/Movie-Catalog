package com.maverick.movie.model;

import com.maverick.movie.enums.Genre;
import com.maverick.movie.enums.StreamAvailable;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection="movie")
public class Movie {
    @Id
    private String id;
    private String title;
    private String description;
    private String director;
    private Double rate;
    private Genre genre;
    private List<StreamAvailable> streamAvailableList;

    public Movie() {
    }

    public Movie(String id, String title, String description, String director, Double rate, Genre genre, List<StreamAvailable> streamAvailableList) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.director = director;
        this.rate = rate;
        this.genre = genre;
        this.streamAvailableList = streamAvailableList;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public List<StreamAvailable> getStreamAvailableList() {
        return streamAvailableList;
    }

    public void setStreamAvailableList(List<StreamAvailable> streamAvailableList) {
        this.streamAvailableList = streamAvailableList;
    }
}
