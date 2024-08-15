package org.example.market_place.service;

import jakarta.transaction.Transactional;
import org.example.market_place.converter.ProductConverter;
import org.example.market_place.domain.dto.ProductDTOForm;
import org.example.market_place.domain.dto.ProductDTOView;
import org.example.market_place.domain.dto.UserDTOForm;
import org.example.market_place.domain.entity.Product;
import org.example.market_place.domain.entity.User;
import org.example.market_place.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service

public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    @Transactional
    public List<ProductDTOView> createWithUser(List<ProductDTOForm> productDTOForms, User user) {
        List<ProductDTOView> productDTOViews = new ArrayList<>();
        for (ProductDTOForm productDTOForm : productDTOForms) {
           productDTOViews.add(createUser(productDTOForm, user));
        }
        return productDTOViews;
    }

    @Override
    @Transactional
    public ProductDTOView create(ProductDTOForm dtoForm) {
        if(dtoForm == null) throw new IllegalArgumentException("dtoForm can  not be null");
        Product product = new Product(dtoForm.getId(), dtoForm.getType(), dtoForm.getPrice(), dtoForm.getExpired());
        Product savedProduct = productRepository.save(product);
        return ProductDTOView.builder()
                .id(savedProduct.getId())
                .price(savedProduct.getPrice())
                .type(savedProduct.getType())
                .expired(savedProduct.getExpired())
                .build();

    }

    @Override
    @Transactional
    public ProductDTOView createUser(ProductDTOForm dtoForm, User user) {
        if(dtoForm == null) throw new IllegalArgumentException("dtoForm can  not be null");
        Product product = new Product(dtoForm.getId(), dtoForm.getType(), dtoForm.getPrice(), dtoForm.getExpired());
        product.setUser(user);
        Product savedProduct = productRepository.save(product);
        return ProductDTOView.builder()
                .id(savedProduct.getId())
                .price(savedProduct.getPrice())
                .type(savedProduct.getType())
                .expired(savedProduct.getExpired())
                .build();

    }

    @Override
    @Transactional
    public List<ProductDTOView> findProductsByPriceGreaterThanEqual(Long price) {
        if(price < 0) throw new IllegalArgumentException("Price must be greater than zero");
        List<Product> products = productRepository.findByPriceGreaterThanEqual(price);
       return products.stream()
               .map(this::toProductView)
               .collect(Collectors.toList());
    }
    @Override
    @Transactional
    public ProductDTOView toProductView(Product entity) {
        return ProductDTOView.builder()
                .id(entity.getId())
                .price(entity.getPrice())
                .type(entity.getType())
                .expired(entity.getExpired())
                .build();
        //return new ProductDTOView(entity.getId(), entity.getType(), entity.getPrice(), entity.getExpired());
    }

    @Override
    @Transactional
    public void deleteProductByExpired(LocalDate expired) {
        if(expired == null) throw new IllegalArgumentException("Expired cannot be null");

        productRepository.deleteByExpired(expired);

    }


}
