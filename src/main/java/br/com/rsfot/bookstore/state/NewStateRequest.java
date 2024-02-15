package br.com.rsfot.bookstore.state;

import br.com.rsfot.bookstore.country.Country;
import br.com.rsfot.bookstore.validation.UniqueValue;
import jakarta.validation.constraints.*;

public record NewStateRequest(
        @NotBlank
        @UniqueValue(domainClass = State.class, fieldName = "name")
        String name,
        @NotNull
        @Min(1)
        Long countryId) {

    public State toModel(Country country) {
        return new State(name, country);
    }
}
