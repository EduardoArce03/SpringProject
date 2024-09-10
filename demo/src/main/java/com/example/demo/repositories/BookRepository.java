package com.example.demo.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{
    @Query("SELECT b FROM Book b " +
       "JOIN b.bookCategories bc " +
       "JOIN bc.category c " +
       "WHERE c.name = :name " +
       "ORDER BY b.title ASC")
    Page<Book> findByCategoryName(@Param("name") String name, Pageable pageable);
}
