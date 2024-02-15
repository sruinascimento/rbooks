package br.com.rsfot.bookstore.state;

import br.com.rsfot.bookstore.country.Country;
import br.com.rsfot.bookstore.country.CountryRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class StateController {
    private final CountryRepository countryRepository;
    private final StateRepository stateRepository;

    public StateController(CountryRepository countryRepository, StateRepository stateRepository) {
        this.countryRepository = countryRepository;
        this.stateRepository = stateRepository;
    }

    @PostMapping("/states")
    @Transactional
    public ResponseEntity<NewStateResponse> create(@Valid @RequestBody NewStateRequest newStateRequest) {
        Country country = countryRepository.findById(newStateRequest.countryId())
                .orElseThrow(() -> new IllegalArgumentException("Country not found"));

        State state = newStateRequest.toModel(country);
        stateRepository.save(state);

        return ResponseEntity.ok(NewStateResponse.from(state));
    }
}
