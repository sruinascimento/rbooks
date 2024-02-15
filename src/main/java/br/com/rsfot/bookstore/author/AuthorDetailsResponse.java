package br.com.rsfot.bookstore.author;

import org.springframework.util.Assert;

public record AuthorDetailsResponse(String name, String description) {
    public static AuthorDetailsResponse from(Author author) {
        Assert.notNull(author, "Author cannot be null");
        return new AuthorDetailsResponse(author.getName(), author.getDescription());
    }
}
