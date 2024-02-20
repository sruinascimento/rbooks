package br.com.rsfot.bookstore.validation;

import br.com.rsfot.bookstore.book.Book;
import br.com.rsfot.bookstore.book.BookRepository;
import br.com.rsfot.bookstore.purchase.NewPurchaseRequest;
import br.com.rsfot.bookstore.state.StateRepository;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.math.BigDecimal;
import java.util.List;

@Component
public class PurchaseValidator implements Validator {
    private final StateRepository stateRepository;
    private final BookRepository bookRepository;

    public PurchaseValidator(StateRepository stateRepository, BookRepository bookRepository) {
        this.stateRepository = stateRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return NewPurchaseRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (errors.hasErrors()) {
            return;
        }
        NewPurchaseRequest paymentDetails = (NewPurchaseRequest) target;


        if (!isStateBelongToTheCountry(paymentDetails)) {
            errors.rejectValue("stateId", null, "This state does not belong to the selected country");
        }


        if (getPurchaseAmount(paymentDetails).compareTo(paymentDetails.getAmount()) != 0) {
            errors.rejectValue("amount", null, "The total amount does not match the sum of the book prices");
        }

        if (hasProductDuplicatedInPurchase(paymentDetails)) {
            errors.rejectValue("products", null, "There are duplicated products in the purchase");
        }
    }

    private BigDecimal getPurchaseAmount(NewPurchaseRequest paymentDetails) {
        List<Book> booksByIds = bookRepository.getBooksByIds(paymentDetails.products().getBookIds());
        return booksByIds.stream()
                .map(it -> it.getPrice().multiply(BigDecimal.valueOf(paymentDetails.products().getQuantities().get(booksByIds.indexOf(it)))))
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }

    private boolean isStateBelongToTheCountry(NewPurchaseRequest paymentDetails) {
        return stateRepository.existsByIdAndCountry_Id(paymentDetails.stateId(), paymentDetails.countryId());
    }

    private boolean hasProductDuplicatedInPurchase(NewPurchaseRequest paymentDetails) {
        return paymentDetails.products().getBookIds().stream().distinct().count() != paymentDetails.products().getBookIds().size();
    }
}