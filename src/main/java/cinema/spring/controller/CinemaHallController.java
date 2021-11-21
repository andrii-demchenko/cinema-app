package cinema.spring.controller;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import cinema.spring.dto.request.CinemaHallRequestDto;
import cinema.spring.dto.response.CinemaHallResponseDto;
import cinema.spring.model.CinemaHall;
import cinema.spring.service.CinemaHallService;
import cinema.spring.service.mapper.CinemaHallMapper;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cinema-halls")
public class CinemaHallController {
    private final CinemaHallService cinemaHallService;
    private final CinemaHallMapper cinemaHallMapper;

    public CinemaHallController(CinemaHallService cinemaHallService,
                                CinemaHallMapper cinemaHallMapper) {
        this.cinemaHallService = cinemaHallService;
        this.cinemaHallMapper = cinemaHallMapper;
    }

    @PostMapping
    public CinemaHallResponseDto add(@RequestBody @Valid CinemaHallRequestDto requestDto) {
        CinemaHall cinemaHall = cinemaHallService.add(cinemaHallMapper.mapToModel(requestDto));
        return cinemaHallMapper.mapToDto(cinemaHall);
    }

    @GetMapping
    public List<CinemaHallResponseDto> getAll() {
        return cinemaHallService.getAll()
                .stream()
                .map(cinemaHallMapper::mapToDto)
                .collect(Collectors.toList());
    }
}
