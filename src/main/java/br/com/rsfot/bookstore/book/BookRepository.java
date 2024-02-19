package br.com.rsfot.bookstore.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long>{

    @Query("select book from Book book where book.id in :bookIds")
    List<Book> getBooksByIds(@Param("bookIds") List<Long> bookIds);
}
