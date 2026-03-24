package pl.coderslab.controller;

import jakarta.transaction.Transactional;
import lombok.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.coderslab.dto.MovieDto;
import pl.coderslab.entity.Genre;
import pl.coderslab.entity.Movie;
import pl.coderslab.mapper.MovieMapper;
import pl.coderslab.repository.MovieRepository;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    private final MovieRepository movieRepository;

    public MovieController(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @GetMapping("/{id}")
    @Transactional
    public MovieDto getMovieById(@PathVariable("id") Long id) {
        return movieRepository.findById(id)
                .map(MovieMapper::toDto).orElseThrow(() -> new RuntimeException("no movie found"));
    }

    private static @NonNull MovieDto getMovieDto(Movie movie) {
        MovieDto movieDto = new MovieDto();
        movieDto.setId(movie.getId());
        movieDto.setTitle(movie.getTitle());
        movieDto.setRating(movie.getRating());
        movieDto.setReleaseYear(movie.getReleaseYear());
        movieDto.setGenres(
                movie.getGenres()
                        .stream()
                        .map(Genre::getName)
                        .toList()
        );

        return movieDto;
    }


}
