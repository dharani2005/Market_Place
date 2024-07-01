package org.example.market_place.repository;

import org.example.market_place.domain.entity.Product;
import org.example.market_place.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    //check if the email exists in the database
   Boolean existsByEmail(String email);

  User findByEmail(String email);

  //User UpdateByProducts(List<Product> products);

  //find products by user email and password
  Optional<User> findByEmailAndPassword(String email, String password);
}
