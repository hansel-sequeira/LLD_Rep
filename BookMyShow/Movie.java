package BookMyShow.Entitites;

import BookMyShow.Enums.Genre;

public class Movie {
    String movieName;
    Genre genre;
    int movieLength;
    public Movie(String movieName, int movieLength, Genre genre) {
        this.movieName = movieName;
        this.movieLength = movieLength;
        this.genre = genre;
    }

}
