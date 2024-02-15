package br.com.rsfot.bookstore.state;

public record NewStateResponse(String stateName, String countryName) {
    public static NewStateResponse from(State state) {
        return new NewStateResponse(state.getName(), state.getCountry().getName());
    }
}
