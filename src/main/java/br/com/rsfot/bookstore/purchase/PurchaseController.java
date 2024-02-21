package br.com.rsfot.bookstore.purchase;

import br.com.rsfot.bookstore.validation.PurchaseValidator;
import br.com.rsfot.bookstore.validation.VerifyDocumentCpfOrCnpjValidator;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

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

    @PostMapping("/purchases")
    @Transactional
    public ResponseEntity<Void> create(@Valid @RequestBody NewPurchaseRequest newPurchaseRequest,
                                                   UriComponentsBuilder uriComponentsBuilder) {
        Long purchaseIdCreated = purchaseService.create(newPurchaseRequest);
        URI uri = uriComponentsBuilder.path("/purchases/{id}").buildAndExpand(purchaseIdCreated).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/purchases/{id}")
    public ResponseEntity<PurchaseResponse> getPurchase(@PathVariable Long id) {
        PurchaseResponse purchaseResponse = purchaseService.getPurchase(id);
        return ResponseEntity.ok(purchaseResponse);
    }
}
