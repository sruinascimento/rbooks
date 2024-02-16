package br.com.rsfot.bookstore.payment;

import br.com.rsfot.bookstore.validation.StateFromCountryValidator;
import jakarta.validation.Valid;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@RestController
public class PaymentDetailsController {
    private final StateFromCountryValidator stateFromCountryValidator;

    public PaymentDetailsController(StateFromCountryValidator stateFromCountryValidator) {
        this.stateFromCountryValidator = stateFromCountryValidator;
    }

    @InitBinder
    public void init(WebDataBinder binder) {
        binder.addValidators(new VerifyDocumentCpfOrCnpjValidator(), stateFromCountryValidator);
    }

    @PostMapping("/payment-details")
    public String create(@Valid @RequestBody NewPaymentDetailsRequest newPaymentDetailsRequest) {
        return newPaymentDetailsRequest.toString();
    }
}
