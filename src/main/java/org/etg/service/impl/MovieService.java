package org.etg.service.impl;

import org.etg.entity.Movie;
import org.etg.entity.MovieType;
import org.etg.exception.MovieNotFoundException;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Service class for managing movie data.
 */
public class MovieService {
    private static class SingletonHelper {
        private static final MovieService INSTANCE = new MovieService();
    }

    private final Map<String, Movie> movies = new ConcurrentHashMap<>();

    private MovieService() {
        // Initialize movie catalog
        movies.put("F001", new Movie("You've Got Mail", MovieType.REGULAR));
        movies.put("F002", new Movie("Matrix", MovieType.REGULAR));
        movies.put("F003", new Movie("Cars", MovieType.CHILDREN));
        movies.put("F004", new Movie("Fast & Furious X", MovieType.NEW_RELEASE));
    }

    public static MovieService getInstance() {
        return SingletonHelper.INSTANCE; // Thread-safe lazy initialization
    }

    /**
     * Get movie by id
     * @param movieId
     * @return
     */
    public Movie getMovie(String movieId) {
        Movie movie = movies.get(movieId);
        if (movie == null) {
            throwMovieNotFound(movieId);
        }
        return movie;
    }

    /** Add a new movie dynamically */
    public void addMovie(String movieId, String title, MovieType type) {
        movies.put(movieId, new Movie(title, type));
    }

    /** Remove a movie */
    public void removeMovie(String movieId) {
        movies.remove(movieId);
    }

    /**
     * Get All Movies
     * @return list of Movies
     */
    public Map<String, Movie> getAllMovies() {
        return Map.copyOf(movies); // Return an immutable copy
    }

    private void throwMovieNotFound(String movieId) {
        throw new MovieNotFoundException(movieId);
    }
}
