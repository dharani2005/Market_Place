package org.example.market_place.service;

import jakarta.transaction.Transactional;
import org.example.market_place.converter.ProductConverter;
import org.example.market_place.domain.dto.ProductDTOForm;
import org.example.market_place.domain.dto.ProductDTOView;
import org.example.market_place.domain.dto.UserDTOForm;
import org.example.market_place.domain.dto.UserDTOView;
import org.example.market_place.domain.entity.Product;
import org.example.market_place.domain.entity.User;
import org.example.market_place.exception.DataNotFoundException;
import org.example.market_place.repository.ProductRepository;
import org.example.market_place.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service

public class ProductServiceImpl implements ProductService {


    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    /*@Override
    @Transactional
    public List<ProductDTOView> createWithUser(List<ProductDTOForm> productDTOForms, User user) {
        List<ProductDTOView> productDTOViews = new ArrayList<>();
        for (ProductDTOForm productDTOForm : productDTOForms) {
           productDTOViews.add(createUser(productDTOForm, user));
        }
        return productDTOViews;
    }*/

    @Override
    @Transactional
    public ProductDTOView create(ProductDTOForm dtoForm) {
        //check if user exists,otherwise create a new one
        Optional<User> optionalUser = userRepository.findByEmail(dtoForm.getUser().getEmail());
       User user;
       if(optionalUser.isPresent()) {
           user = optionalUser.get();
       }else {
           user = User.builder().email(dtoForm.getUser().getEmail())
                   .name(dtoForm.getUser().getUsername())
                   .password(dtoForm.getUser().getPassword())
                   .build();
           user = userRepository.save(user);
       }
       //create a new product entity using the dto and builder
        Product product = Product.builder()
                .type(dtoForm.getType())
                .price(dtoForm.getPrice())
                .creationDate(dtoForm.getCreationDate())
                .expired(dtoForm.getExpired())
                .user(user)
                .build();

       //save the created entity product to the database
       Product productSaved = productRepository.save(product);

       //convert the saved entity to DTOView user using builder
        UserDTOView userDTOView = UserDTOView.builder()
                .email(productSaved.getUser().getEmail())
                .username(productSaved.getUser().getName())
                .build();

        //return productDTOView using builder
        return ProductDTOView.builder()
                .id(productSaved.getId())
                .price(productSaved.getPrice())
                .type(productSaved.getType())
                .creationDate(productSaved.getCreationDate())
                .user(userDTOView)
                .expired(productSaved.getExpired())
                .build();


    }
        /* if(dtoForm == null) throw new IllegalArgumentException("dtoForm can  not be null");
        Product product = new Product(dtoForm.getId(), dtoForm.getType(), dtoForm.getPrice(), dtoForm.getExpired());
        Product savedProduct = productRepository.save(product);
        return ProductDTOView.builder()
                .id(savedProduct.getId())
                .price(savedProduct.getPrice())
                .type(savedProduct.getType())
                .creationDate(savedProduct.getCreationDate())
                .expired(savedProduct.getExpired())
                .build();*/



    /*@Override
    @Transactional
    public ProductDTOView createUser(ProductDTOForm dtoForm, User user) {
        if(dtoForm == null) throw new IllegalArgumentException("dtoForm can  not be null");
        Product product = new Product(dtoForm.getId(), dtoForm.getType(), dtoForm.getPrice(),dtoForm.getExpired());
        product.setUser(user);
        Product savedProduct = productRepository.save(product);
        return ProductDTOView.builder()
                .id(savedProduct.getId())
                .price(savedProduct.getPrice())
                .type(savedProduct.getType())
                .expired(savedProduct.getExpired())
                .build();

    }*/

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
    public ProductDTOView addProductToUser(String email, ProductDTOForm dtoForm) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new DataNotFoundException("User not found"));

        //update the productDTOForm with the retrieved user
        dtoForm.setUser(
                UserDTOForm.builder()
                        .email(user.getEmail())
                        .username(user.getName())
                        .build());
        return create(dtoForm);
    }

    @Override
    @Transactional
    public boolean deleteProductByExpired() {
        List<Product> expiredProducts = productRepository.findAllByExpiredAfter(LocalDate.now());
        productRepository.deleteAll(expiredProducts);
        return true;

    }


}
