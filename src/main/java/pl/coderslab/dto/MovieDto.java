package pl.coderslab.dto;

import lombok.Getter;
import lombok.Setter;


import java.util.List;

@Getter
@Setter
public class MovieDto {
    private Long id;
    private String title;
    private int releaseYear;
    private List<String> genres;
    private double rating;
}
