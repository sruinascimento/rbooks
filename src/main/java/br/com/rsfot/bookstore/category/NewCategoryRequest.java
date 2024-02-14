package br.com.rsfot.bookstore.category;

import br.com.rsfot.bookstore.validation.UniqueValue;
import jakarta.validation.constraints.NotNull;

public record NewCategoryRequest(
        @NotNull
        @UniqueValue(fieldName = "name", domainClass = Category.class)
        String name) {

    public Category toModel() {
        return new Category(name);
    }
}
