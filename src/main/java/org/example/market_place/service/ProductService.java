package org.example.market_place.service;

import org.example.market_place.domain.dto.ProductDTOForm;
import org.example.market_place.domain.dto.ProductDTOView;
import org.example.market_place.domain.dto.UserDTOForm;
import org.example.market_place.domain.entity.Product;
import org.example.market_place.domain.entity.User;

import java.time.LocalDate;
import java.util.List;

public interface ProductService {

    //create the product
   // ProductDTOView create(ProductDTOForm dtoForm);

    void createWithUser(List<ProductDTOForm> productDTOForms, User user);

    ProductDTOView createUser(ProductDTOForm dtoForm, User user);

    //Filter the product by price
    List<ProductDTOView> findProductsByPriceGreaterThanEqual(Long price);

    ProductDTOView toProductView(Product entity);

    //delete the product by expiration date
    void deleteProductByExpired(LocalDate expired);

   /* //find the product by user email and password
    List<ProductDTOView>findByUserEmailAndPassword(UserDTOForm dtoForm);*/



}
