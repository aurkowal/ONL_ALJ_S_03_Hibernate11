package pl.coderslab.mapper;

import pl.coderslab.dto.MovieDto;
import pl.coderslab.entity.Genre;
import pl.coderslab.entity.Movie;

import java.util.stream.Collectors;

public class MovieMapper {

    private MovieMapper() {

    }

    public static MovieDto toDto(Movie movie) {
        MovieDto movieDto = new MovieDto();
        movieDto.setId(movie.getId());
        movieDto.setTitle(movie.getTitle());
        movieDto.setReleaseYear(movie.getReleaseYear());
        movieDto.setRating(movie.getRating());
        movieDto.setGenres(
                movie.getGenres()
                        .stream()
                        .map(Genre::getName)
                        .collect(Collectors.toList())
        );
        return movieDto;
    }
}
