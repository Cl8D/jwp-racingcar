package racingcar.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import racingcar.dto.RaceRequest;
import racingcar.dto.RaceResponse;
import racingcar.service.RaceService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/plays")
public class RaceController {

    private final RaceService raceService;

    public RaceController(final RaceService raceService) {
        this.raceService = raceService;
    }

    @PostMapping
    public ResponseEntity<RaceResponse> play(@RequestBody @Valid final RaceRequest raceRequest) {
        return ResponseEntity.ok().body(raceService.play(raceRequest));
    }

    @GetMapping
    public ResponseEntity<List<RaceResponse>> raceResult() {
        return ResponseEntity.ok().body(raceService.getRaceResult());
    }
}
