package br.com.rsfot.bookstore.payment;

import br.com.rsfot.bookstore.country.Country;
import br.com.rsfot.bookstore.state.State;
import br.com.rsfot.bookstore.validation.ExistsId;
import jakarta.validation.constraints.*;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CNPJValidator;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CPFValidator;
import org.springframework.util.Assert;

public record NewPaymentDetailsRequest(
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
        String phone) {

    public boolean isValidDocument() {
        Assert.hasLength(document, "Document cannot be empty");

        CPFValidator cpfValidator = new CPFValidator();
        CNPJValidator cnpjValidator = new CNPJValidator();
        //ConstraintAnnotation and Context are not used in these methods, so we can pass null
        cpfValidator.initialize(null);
        cnpjValidator.initialize(null);

        return cpfValidator.isValid(document, null) || cnpjValidator.isValid(document, null);
    }


}
