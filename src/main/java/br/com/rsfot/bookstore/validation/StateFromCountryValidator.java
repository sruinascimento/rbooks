package br.com.rsfot.bookstore.validation;

import br.com.rsfot.bookstore.purchase.NewPurchaseRequest;
import br.com.rsfot.bookstore.state.StateRepository;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class StateFromCountryValidator implements Validator {
      private final StateRepository stateRepository;

    public StateFromCountryValidator(StateRepository stateRepository) {
        this.stateRepository = stateRepository;
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
        boolean stateDoesntBelongToTheCountry = !stateRepository.existsByIdAndCountry_Id(paymentDetails.stateId(), paymentDetails.countryId());
        if (stateDoesntBelongToTheCountry) {
            errors.rejectValue("stateId", null, "This state does not belong to the selected country");
        }
    }
}