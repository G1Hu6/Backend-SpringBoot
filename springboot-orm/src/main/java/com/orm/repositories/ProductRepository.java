package com.orm.repositories;

import com.orm.entities.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByName(String name);

    List<Product> findByCreatedAtBefore(LocalDateTime endDate);

    List<Product> findByCreatedAtAfter(LocalDateTime startDate);

    List<Product> findByQuantityAndPrice(Integer quantity, BigInteger price);

    List<Product> findByNameLike(String name);

    List<Product> findByNameContains(String name);

    List<Product> findByNameContainsIgnoreCase(String name);

    // Find with sorting and pagination
    List<Product> findByNameContainsIgnoreCase(String name, Pageable pageable);

    //Optional<Product> findByNameAndPrice(String name, BigInteger price);

    @Query("select p from Product p where p.name=?1 and p.price=?2")
    Optional<Product> findByNameAndPrice(String name, BigInteger price);

    List<Optional<Product>> findByNameOrPrice(String name, BigInteger price);

    List<Product> findDistinctByName(String name);

    // Sorting using method queries
    List<Product> findByOrderByPrice();

    List<Product> findByNameOrderByPrice(String name);

    // Sorting using Sort class(Most used)
    List<Product> findBy(Sort sort);
}
