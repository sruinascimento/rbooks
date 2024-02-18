package br.com.rsfot.bookstore.payment;

import br.com.rsfot.bookstore.validation.EqualsAmountPurchaseValidator;
import br.com.rsfot.bookstore.validation.StateFromCountryValidator;
import jakarta.validation.Valid;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@RestController
public class PaymentDetailsController {
    private final StateFromCountryValidator stateFromCountryValidator;
    private final EqualsAmountPurchaseValidator equalsAmountPurchaseValidator;


    public PaymentDetailsController(StateFromCountryValidator stateFromCountryValidator,
                                    EqualsAmountPurchaseValidator equalsAmountPurchaseValidator) {
        this.stateFromCountryValidator = stateFromCountryValidator;
        this.equalsAmountPurchaseValidator = equalsAmountPurchaseValidator;
    }

    @InitBinder
    public void init(WebDataBinder binder) {
        binder.addValidators(new VerifyDocumentCpfOrCnpjValidator(),
                stateFromCountryValidator,
                equalsAmountPurchaseValidator);
    }

    @PostMapping("/payment-details")
    public String create(@Valid @RequestBody NewPaymentDetailsRequest newPaymentDetailsRequest) {
        return newPaymentDetailsRequest.toString();
    }
}
