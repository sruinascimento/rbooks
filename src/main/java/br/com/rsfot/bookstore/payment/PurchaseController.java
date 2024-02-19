package br.com.rsfot.bookstore.payment;

import br.com.rsfot.bookstore.validation.PurchaseValidator;
import br.com.rsfot.bookstore.validation.VerifyDocumentCpfOrCnpjValidator;
import jakarta.validation.Valid;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@RestController
public class PurchaseController {
    private final PurchaseValidator purchaseValidator;

    public PurchaseController(PurchaseValidator purchaseValidator) {
        this.purchaseValidator = purchaseValidator;
    }

    @InitBinder
    public void init(WebDataBinder binder) {
        binder.addValidators(new VerifyDocumentCpfOrCnpjValidator(),
                purchaseValidator);
    }

    @PostMapping("/purchase")
    public String create(@Valid @RequestBody NewPurchaseRequest newPaymentDetailsRequest) {
        return newPaymentDetailsRequest.toString();
    }
}
