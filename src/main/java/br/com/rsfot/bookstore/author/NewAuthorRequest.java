package br.com.rsfot.bookstore.author;

import br.com.rsfot.bookstore.validation.UniqueValue;
import jakarta.validation.constraints.*;

public record NewAuthorRequest(
        @NotBlank
        @Size(min = 3, max = 100)
        String name,
        @NotNull
        @Email
        @UniqueValue(fieldName = "email", domainClass = Author.class)
        String email,
        @NotBlank
        @Size(max = 400)
        String description) {
    public Author toModel() {
        return new Author(name, email, description);
    }
}
