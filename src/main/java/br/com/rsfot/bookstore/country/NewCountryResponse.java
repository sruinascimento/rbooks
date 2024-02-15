package br.com.rsfot.bookstore.country;

public record NewCountryResponse(Long id, String name) {
    public static NewCountryResponse from(Country country) {
        return new NewCountryResponse(country.getId(), country.getName());
    }
}
