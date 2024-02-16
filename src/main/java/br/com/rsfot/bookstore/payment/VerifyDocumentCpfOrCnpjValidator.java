package br.com.rsfot.bookstore.payment;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class VerifyDocumentCpfOrCnpjValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return NewPaymentDetailsRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        NewPaymentDetailsRequest request = (NewPaymentDetailsRequest) target;
        if (!request.isValidDocument()) {
            errors.rejectValue("document", null, "Invalid document format. It should be a valid CPF or CNPJ.");
        }
    }
}
