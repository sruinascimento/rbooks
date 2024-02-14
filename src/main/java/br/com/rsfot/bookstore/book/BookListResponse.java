package br.com.rsfot.bookstore.book;

public record BookListResponse(Long id, String title) {
    public BookListResponse(Book book) {
        this(book.getId(), book.getTitle());
    }
}
