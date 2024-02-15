package br.com.rsfot.bookstore.country;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CountryController {
    private final CountryRepository countryRepository;

    public CountryController(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @PostMapping("/countries")
    @Transactional
    public ResponseEntity<NewCountryResponse> create(@Valid @RequestBody NewCountryRequest newCountryRequest) {
        var country = newCountryRequest.toModel();
        countryRepository.save(country);
        return ResponseEntity.ok(NewCountryResponse.from(country));
    }
}
