package br.com.rsfot.bookstore.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long>{
    @Query("select sum(b.price * :quantity) from Book b where b.id in :bookIds")
    BigDecimal getBookAmountByListId(@Param("bookIds") List<Long> bookIds, @Param("quantity") List<Integer> quantity);
}
