package br.com.rsfot.bookstore.purchase;

import br.com.rsfot.bookstore.book.Book;
import br.com.rsfot.bookstore.country.Country;
import br.com.rsfot.bookstore.state.State;
import br.com.rsfot.bookstore.validation.ExistsId;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CNPJValidator;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CPFValidator;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.List;

public record NewPurchaseRequest(
        @NotBlank
        @Email
        String email,
        @NotBlank
        String name,
        @NotBlank
        String document,
        @NotBlank
        String address,
        @NotBlank
        String complement,
        @NotBlank
        String city,
        @ExistsId(fieldName = "id", domainClass = State.class)
        Long stateId,
        @NotNull
        @ExistsId(fieldName = "id", domainClass = Country.class)
        Long countryId,
        @NotBlank
        @Pattern(regexp = "\\d{5}-\\d{3}", message = "CEP must be in the format XXXXX-XXX")
        String cep,
        @NotBlank
        String phone,
        @NotNull
        @Valid
        NewProductsPurchaseRequest products) {

    public boolean isValidDocument() {
        Assert.hasLength(document, "Document cannot be empty");

        CPFValidator cpfValidator = new CPFValidator();
        CNPJValidator cnpjValidator = new CNPJValidator();
        //ConstraintAnnotation and Context are not used in these methods, so we can pass null
        cpfValidator.initialize(null);
        cnpjValidator.initialize(null);

        return cpfValidator.isValid(document, null) || cnpjValidator.isValid(document, null);
    }

    public BigDecimal getAmount() {
        return products.amount();
    }

    public Purchase toModel(State state, Country country, List<Book> books) {
        return new Purchase(
                name,
                email,
                document,
                phone,
                address,
                complement,
                city,
                state,
                country,
                cep,
                products.toModel(books),
                products.amount()
        );
    }
}
