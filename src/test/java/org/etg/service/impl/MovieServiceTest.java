package org.etg.service.impl;

import org.etg.entity.Movie;
import org.etg.entity.MovieType;
import org.etg.exception.MovieNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class) // Initializes mocks automatically in Mockito 5
public class MovieServiceTest {

    @Mock
    private MovieService movieServiceMock; // Mocked MovieService instance

    @InjectMocks
    private MovieService movieService; // Inject mocked dependencies into MovieService

    @BeforeEach
    void setup() {
        movieService = MovieService.getInstance(); // Use singleton instance
    }

    @Test
    void testAddMovie() {
        movieService.addMovie("F005", "Inception", MovieType.NEW_RELEASE);

        // Mock getMovie behavior
        when(movieServiceMock.getMovie("F005")).thenReturn(new Movie("Inception", MovieType.NEW_RELEASE));

        Movie movie = movieServiceMock.getMovie("F005");
        assertNotNull(movie);
        assertEquals("Inception", movie.title());
        assertEquals(MovieType.NEW_RELEASE, movie.movieType());

        verify(movieServiceMock, times(1)).getMovie("F005"); // Verify method call
    }

    @Test
    void testRemoveMovie() {
        movieService.addMovie("F006", "Titanic", MovieType.REGULAR);
        movieService.removeMovie("F006");

        // Mock exception throwing when trying to get a removed movie
        doThrow(new MovieNotFoundException("F006")).when(movieServiceMock).getMovie("F006");

        Exception exception = assertThrows(MovieNotFoundException.class, () -> movieServiceMock.getMovie("F006"));
        assertEquals("Movie with ID: F006 not found.", exception.getMessage());
    }

    @Test
    void testRetrieveExistingMovies() {
        Movie movie = movieService.getMovie("F001");

        assertNotNull(movie);
        assertEquals("You've Got Mail", movie.title());
        assertEquals(MovieType.REGULAR, movie.movieType());
    }

    @Test
    void testRetrieveNonExistentMovieThrowsException() {
        Exception exception = assertThrows(MovieNotFoundException.class, () -> movieService.getMovie("INVALID_ID"));
        assertEquals("Movie with ID: INVALID_ID not found.", exception.getMessage());
    }
}