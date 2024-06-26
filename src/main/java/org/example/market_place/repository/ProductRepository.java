package org.example.market_place.repository;

import org.example.market_place.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    //Filtering the product by price
    List<Product> findByPriceGreaterThanEqual(Long price);

    //Delete the product by expiration date
    void deleteByExpired(LocalDate localDate);

   /* //find products by user email and password
    @Query("select p from Product p join p.user u where u.email = :email and u.password = :password")
    List<Product> findByUserEmailAndPassword(@Param("email")String email,@Param("password") String password);*/

}
