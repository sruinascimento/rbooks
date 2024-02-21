package br.com.rsfot.bookstore.purchase;

import br.com.rsfot.bookstore.book.Book;
import br.com.rsfot.bookstore.book.BookRepository;
import br.com.rsfot.bookstore.country.Country;
import br.com.rsfot.bookstore.country.CountryRepository;
import br.com.rsfot.bookstore.error.handler.NotFoundException;
import br.com.rsfot.bookstore.state.State;
import br.com.rsfot.bookstore.state.StateRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseService {
    private final StateRepository stateRepository;
    private final CountryRepository countryRepository;
    private final BookRepository bookRepository;
    private final PurchaseRepository purchaseRepository;

    public PurchaseService(StateRepository stateRepository,
                           CountryRepository countryRepository,
                           BookRepository bookRepository,
                           PurchaseRepository purchaseRepository) {
        this.stateRepository = stateRepository;
        this.countryRepository = countryRepository;
        this.bookRepository = bookRepository;
        this.purchaseRepository = purchaseRepository;
    }

    public NewPurchaseResponse create(NewPurchaseRequest newPurchaseRequest) {
        State state = stateRepository.findById(newPurchaseRequest.stateId())
                .orElseThrow(() -> new NotFoundException("State not found with id " + newPurchaseRequest.stateId()));

        Country country = countryRepository.findById(newPurchaseRequest.countryId())
                .orElseThrow(() -> new NotFoundException("Country not found with id " + newPurchaseRequest.countryId()));

        List<Book> books = bookRepository.getBooksByIds(newPurchaseRequest.products().getBookIds());

        Purchase purchase = newPurchaseRequest.toModel(state, country, books);
        purchaseRepository.save(purchase);

        return new NewPurchaseResponse(purchase);

    }
}
