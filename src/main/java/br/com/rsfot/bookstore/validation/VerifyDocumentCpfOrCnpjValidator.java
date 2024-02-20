package br.com.rsfot.bookstore.validation;

import br.com.rsfot.bookstore.purchase.NewPurchaseRequest;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class VerifyDocumentCpfOrCnpjValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return NewPurchaseRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        NewPurchaseRequest request = (NewPurchaseRequest) target;
        if (!request.isValidDocument()) {
            errors.rejectValue("document", null, "Invalid document format. It should be a valid CPF or CNPJ.");
        }
    }
}
