package org.example.market_place.controller;

import jakarta.validation.Valid;
import org.example.market_place.domain.dto.ProductDTOForm;
import org.example.market_place.domain.dto.ProductDTOView;
import org.example.market_place.domain.entity.User;
import org.example.market_place.service.ProductService;
import org.example.market_place.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@Validated
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/")
    public ResponseEntity<ProductDTOView> create(@RequestBody ProductDTOForm productDTOForms) {
        System.out.println("DTO Form: " + productDTOForms );
        ProductDTOView createdProducts = productService.create(productDTOForms);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProducts);
    }

    @GetMapping("/price")
    public ResponseEntity<List<ProductDTOView>> doFindProductsByPriceGreaterThanEqual(@RequestParam Long price) {
        List<ProductDTOView> products = productService.findProductsByPriceGreaterThanEqual(price);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping("/add-to-user")
    public ResponseEntity<ProductDTOView> addAdvertisementToUser(@RequestParam String email, @RequestBody @Valid ProductDTOForm dtoForm) {
        ProductDTOView responseBody = productService.addProductToUser(email, dtoForm);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseBody);
    }



@DeleteMapping("/expired")
    public ResponseEntity<Void> doDeleteProductBYExpired() {
boolean isDeleted = productService.deleteProductByExpired();
if(isDeleted) {
    return ResponseEntity.noContent().build();
} else {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

}
}
}
