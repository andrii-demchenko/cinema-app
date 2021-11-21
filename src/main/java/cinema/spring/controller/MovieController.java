package cinema.spring.controller;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import cinema.spring.dto.request.MovieRequestDto;
import cinema.spring.dto.response.MovieResponseDto;
import cinema.spring.model.Movie;
import cinema.spring.service.MovieService;
import cinema.spring.service.mapper.MovieMapper;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movies")
public class MovieController {
    private final MovieService movieService;
    private final MovieMapper movieMapper;

    public MovieController(MovieService movieService, MovieMapper movieMapper) {
        this.movieService = movieService;
        this.movieMapper = movieMapper;
    }

    @PostMapping
    public MovieResponseDto add(@RequestBody @Valid MovieRequestDto requestDto) {
        Movie movie = movieService.add(movieMapper.mapToModel(requestDto));
        return movieMapper.mapToDto(movie);
    }

    @GetMapping
    public List<MovieResponseDto> getAll() {
        return movieService.getAll()
                .stream()
                .map(movieMapper::mapToDto)
                .collect(Collectors.toList());
    }
}
