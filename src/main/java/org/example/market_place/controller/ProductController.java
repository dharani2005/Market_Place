package org.example.market_place.controller;

import org.example.market_place.domain.dto.ProductDTOForm;
import org.example.market_place.domain.dto.ProductDTOView;
import org.example.market_place.domain.entity.User;
import org.example.market_place.service.ProductService;
import org.example.market_place.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productService;
    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @PostMapping
    public ResponseEntity<List<ProductDTOView>> create(@RequestParam List<ProductDTOForm> productDTOForms, @RequestParam User user) {
        System.out.println("DTO Form: " +productDTOForms + user);
        List<ProductDTOView> createdProducts =  productService.createWithUser(productDTOForms,user);
        return new ResponseEntity<>(createdProducts, HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<ProductDTOView>> doFindProductsByPriceGreaterThanEqual(@RequestParam Long price){
        List<ProductDTOView> products = productService.findProductsByPriceGreaterThanEqual(price);
    return new ResponseEntity<>(products, HttpStatus.OK);
    }
    @DeleteMapping
    public ResponseEntity<Void> doDeleteProductBYExpired(@RequestParam LocalDate expired){
        productService.deleteProductByExpired(expired);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
