package br.com.rsfot.bookstore.purchase;

import br.com.rsfot.bookstore.book.Book;
import br.com.rsfot.bookstore.book.BookRepository;
import br.com.rsfot.bookstore.country.Country;
import br.com.rsfot.bookstore.country.CountryRepository;
import br.com.rsfot.bookstore.error.handler.NotFoundException;
import br.com.rsfot.bookstore.state.State;
import br.com.rsfot.bookstore.state.StateRepository;
import br.com.rsfot.bookstore.validation.PurchaseValidator;
import br.com.rsfot.bookstore.validation.VerifyDocumentCpfOrCnpjValidator;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PurchaseController {
    private final PurchaseValidator purchaseValidator;
    private final BookRepository bookRepository;
    private final StateRepository stateRepository;
    private final CountryRepository countryRepository;
    private final PurchaseRepository purchaseRepository;

    public PurchaseController(PurchaseValidator purchaseValidator,
                              BookRepository bookRepository,
                              StateRepository stateRepository,
                              CountryRepository countryRepository, PurchaseRepository purchaseRepository) {
        this.purchaseValidator = purchaseValidator;
        this.bookRepository = bookRepository;
        this.stateRepository = stateRepository;
        this.countryRepository = countryRepository;
        this.purchaseRepository = purchaseRepository;
    }

    @InitBinder
    public void init(WebDataBinder binder) {
        binder.addValidators(new VerifyDocumentCpfOrCnpjValidator(),
                purchaseValidator);
    }

    @PostMapping("/purchase")
    @Transactional
    public String create(@Valid @RequestBody NewPurchaseRequest newPurchaseRequest) {
        State state = stateRepository.findById(newPurchaseRequest.stateId())
                .orElseThrow(() -> new NotFoundException("State not found with id " + newPurchaseRequest.stateId()));

        Country country = countryRepository.findById(newPurchaseRequest.countryId())
                .orElseThrow(() -> new NotFoundException("Country not found with id " + newPurchaseRequest.countryId()));

        List<Book> books = bookRepository.getBooksByIds(newPurchaseRequest.products().getBookIds());

        Purchase purchase = newPurchaseRequest.toModel(state, country, books);
        purchaseRepository.save(purchase);
        return newPurchaseRequest.toString();
    }
}
