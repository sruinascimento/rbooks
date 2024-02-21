package br.com.rsfot.bookstore.purchase;

import br.com.rsfot.bookstore.validation.PurchaseValidator;
import br.com.rsfot.bookstore.validation.VerifyDocumentCpfOrCnpjValidator;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@RestController
public class PurchaseController {
    private final PurchaseValidator purchaseValidator;
    private final PurchaseService purchaseService;

    public PurchaseController(PurchaseValidator purchaseValidator, PurchaseService purchaseService) {
        this.purchaseValidator = purchaseValidator;
        this.purchaseService = purchaseService;
    }

    @InitBinder
    public void init(WebDataBinder binder) {
        binder.addValidators(new VerifyDocumentCpfOrCnpjValidator(),
                purchaseValidator);
    }

    @PostMapping("/purchase")
    @Transactional
    public ResponseEntity<NewPurchaseResponse> create(@Valid @RequestBody NewPurchaseRequest newPurchaseRequest) {
        NewPurchaseResponse purchaseResponse = purchaseService.create(newPurchaseRequest);
        return ResponseEntity.ok(purchaseResponse);
    }
}
