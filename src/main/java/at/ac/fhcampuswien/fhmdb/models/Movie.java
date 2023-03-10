package at.ac.fhcampuswien.fhmdb.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Movie {
    private String title;
    private String description;

    private List<Genre> genres;
    // TODO add more properties here

    public Movie(String title, String description, List<Genre> genres) {
        this.title = title;
        this.description = description;
        this.genres = genres;
    }

    public List<Genre> getGenres() { return genres; }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public static List<Movie> initializeMovies(){
        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie("CREED III", "Adonis Creed tries very hard to be like Rocky", Arrays.asList(Genre.ACTION, Genre.DRAMA, Genre.SPORT)));
        movies.add(new Movie("ONE PIECE RED", "Ruffy and his CREW have a good sleep, while Shanks and his CREW solos", Arrays.asList(Genre.ACTION, Genre.MUSICAL,Genre.ANIMATION,Genre.ADVENTURE)));
        movies.add(new Movie("Inception", "Man goes to sleep, goes to sleep again and again", Arrays.asList(Genre.ACTION, Genre.DRAMA,Genre.SCIENCE_FICTION,Genre.MYSTERY)));

        // TODO add some dummy data here

        return movies;
    }
}
