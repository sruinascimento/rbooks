package br.com.rsfot.bookstore.country;

import br.com.rsfot.bookstore.validation.UniqueValue;
import jakarta.validation.constraints.NotBlank;

public record NewCountryRequest(
        @NotBlank
        @UniqueValue(domainClass = Country.class, fieldName = "name")
        String name) {
    public Country toModel() {
        return new Country(name);
    }
}
