package br.com.rsfot.bookstore.book;

import br.com.rsfot.bookstore.author.AuthorRepository;
import br.com.rsfot.bookstore.category.CategoryRepository;
import br.com.rsfot.bookstore.error.handler.NotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BookController {
    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;
    private final AuthorRepository authorRepository;

    public BookController(BookRepository bookRepository, CategoryRepository categoryRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.categoryRepository = categoryRepository;
        this.authorRepository = authorRepository;
    }

    @PostMapping("/books")
    public ResponseEntity<NewBookResponse> createBook(@Valid @RequestBody NewBookRequest bookRequest) {
        var category = categoryRepository.findById(bookRequest.categoryId())
                .orElseThrow(() -> new NotFoundException("Category not found with id " + bookRequest.categoryId()));
        var author = authorRepository.findById(bookRequest.authorId())
                .orElseThrow(() -> new NotFoundException("Author not found with id " + bookRequest.authorId()));

        var book = bookRequest.toModel(category, author);
        bookRepository.save(book);

        return ResponseEntity.ok(new NewBookResponse(book));

    }

}
