package org.example.market_place.service;

import org.example.market_place.domain.dto.ProductDTOForm;
import org.example.market_place.domain.dto.ProductDTOView;
import org.example.market_place.domain.dto.UserDTOForm;

import java.time.LocalDate;
import java.util.List;

public interface ProductService {

    //create the product
    ProductDTOView create(ProductDTOForm dtoForm);

    //Filter the product by price
    List<ProductDTOView> findProductsByPriceGreaterThanEqual(Long price);

    //delete the product by expiration date
    void deleteProductByExpired(LocalDate expired);

   /* //find the product by user email and password
    List<ProductDTOView>findByUserEmailAndPassword(UserDTOForm dtoForm);*/



}
