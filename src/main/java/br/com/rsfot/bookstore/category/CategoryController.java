package br.com.rsfot.bookstore.category;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CategoryController {
    private final CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @PostMapping(path = "/categories", consumes = "application/json")
    public ResponseEntity<NewCategoryResponse> create(@Valid @RequestBody NewCategoryRequest newCategoryRequest) {
        Category category = newCategoryRequest.toModel();
        categoryRepository.save(category);
        return ResponseEntity.ok().body(new NewCategoryResponse(category));
    }
}
