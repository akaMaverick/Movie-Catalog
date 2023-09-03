package com.maverick.movie.controller.request;

import com.maverick.movie.enums.Genre;

public class MovieRequest {
    private String title;
    private String description;
    private String director;
    private Double rate;
    private Genre genre;

    public MovieRequest(String title, String description, String director, Double rate, Genre genre) {
        this.title = title;
        this.description = description;
        this.director = director;
        this.rate = rate;
        this.genre = genre;
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
}
