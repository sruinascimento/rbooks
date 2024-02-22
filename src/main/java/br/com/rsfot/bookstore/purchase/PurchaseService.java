package br.com.rsfot.bookstore.purchase;

import br.com.rsfot.bookstore.book.Book;
import br.com.rsfot.bookstore.book.BookRepository;
import br.com.rsfot.bookstore.country.Country;
import br.com.rsfot.bookstore.country.CountryRepository;
import br.com.rsfot.bookstore.coupon.Coupon;
import br.com.rsfot.bookstore.coupon.CouponRepository;
import br.com.rsfot.bookstore.error.handler.NotFoundException;
import br.com.rsfot.bookstore.state.State;
import br.com.rsfot.bookstore.state.StateRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PurchaseService {
    private final StateRepository stateRepository;
    private final CountryRepository countryRepository;
    private final BookRepository bookRepository;
    private final PurchaseRepository purchaseRepository;
    private final CouponRepository couponRepository;

    public PurchaseService(StateRepository stateRepository,
                           CountryRepository countryRepository,
                           BookRepository bookRepository,
                           PurchaseRepository purchaseRepository,
                           CouponRepository couponRepository) {
        this.stateRepository = stateRepository;
        this.countryRepository = countryRepository;
        this.bookRepository = bookRepository;
        this.purchaseRepository = purchaseRepository;
        this.couponRepository = couponRepository;
    }

    public Long create(NewPurchaseRequest newPurchaseRequest) {
        State state = stateRepository.findById(newPurchaseRequest.stateId())
                .orElseThrow(() -> new NotFoundException("State not found with id " + newPurchaseRequest.stateId()));

        Country country = countryRepository.findById(newPurchaseRequest.countryId())
                .orElseThrow(() -> new NotFoundException("Country not found with id " + newPurchaseRequest.countryId()));

        List<Book> books = bookRepository.getBooksByIds(newPurchaseRequest.products().getBookIds());


        Coupon coupon = couponRepository.findByCode(newPurchaseRequest.couponCode());


        Purchase purchase = newPurchaseRequest.toModel(state, country, books, coupon);
        purchaseRepository.save(purchase);

        return purchase.getId();

    }

    public PurchaseResponse getPurchase(Long id) {
        Purchase purchase = purchaseRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Purchase not found with id " + id));
        return new PurchaseResponse(purchase);
    }
}
