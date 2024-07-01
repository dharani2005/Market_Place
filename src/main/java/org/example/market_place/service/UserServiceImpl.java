package org.example.market_place.service;

import org.example.market_place.converter.ProductConverter;
import org.example.market_place.converter.UserConverter;
import org.example.market_place.domain.dto.UserDTOForm;
import org.example.market_place.domain.dto.UserDTOView;
import org.example.market_place.domain.entity.Product;
import org.example.market_place.domain.entity.User;
import org.example.market_place.exception.DataDuplicateException;
import org.example.market_place.exception.DataNotFoundException;
import org.example.market_place.repository.ProductRepository;
import org.example.market_place.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;
    private ProductConverter productConverter;

    private UserConverter userConverter;

    private ProductRepository productRepository;
    @Autowired
    private ProductService productService;
@Autowired
    public UserServiceImpl(UserRepository userRepository, ProductConverter productConverter, UserConverter userConverter, ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.productConverter = productConverter;
        this.userConverter = userConverter;
        this.productRepository = productRepository;
    }

    @Override
    public UserDTOView register(UserDTOForm dtoForm) {
        //check parameters
        if(dtoForm == null) throw new IllegalArgumentException("dtoForm cannot be null");
        //check email exists in the database.
      Boolean emailExists =  userRepository.existsByEmail(dtoForm.getEmail());
      //convert from dtoForm to user
     User userCreate = User.builder().email(dtoForm.getEmail())
              .password(dtoForm.getPassword())
              .product(productConverter.toProductEntities(dtoForm.getProducts()))
             .build();
             if (emailExists)
             {
                Optional<User> verifyUser = userRepository.findByEmailAndPassword(dtoForm.getEmail(), dtoForm.getPassword());
                if (verifyUser.isPresent())
                {
                    User user = verifyUser.get();
                   // user.addProduct((Product[]) (productConverter.toProductEntities(dtoForm.getProducts())).toArray());
                    productService.createWithUser(dtoForm.getProducts(), user);
                    //User user1 = userRepository.UpdateByProducts(user.getProduct());
                /*    for (Product p:pp)
                    {
                        p.setUser(user);
                        productRepository.save(p);
                    }*/
                    return userConverter.toUserDto(user);

    /* return UserDTOView.builder()
             .email(user1.getEmail())
             .products(productConverter.toProductViews(user1.getProduct())).build();*/
                }
            }
            else{
                User user = new User(dtoForm.getEmail(), dtoForm.getPassword());
               User savedUser = userRepository.save(user);
               return UserDTOView.builder()
                       .email(savedUser.getEmail())
                       .build();
                }

     /* User.builder().email(dtoForm.getEmail())
                .password(dtoForm.getPassword())
                .product(dtoForm.getProducts())*/
        return null;
    }

    @Override
    public UserDTOView findProductsByUserEmailAndPassword(String email, String password) {
    //check the parameters
        if(email == null || password == null) throw new IllegalArgumentException("email and password cannot be null");
          if(userRepository.findByEmailAndPassword(email, password).isPresent()) {
              User user = userRepository.findByEmailAndPassword(email, password).get();
              return userConverter.toUserDto(user);
          }

          //Todo implementation
        return null;
    }

    @Override
    public UserDTOView getByEmail(String email) {
         User user = userRepository.findById(email).orElseThrow(() -> new DataNotFoundException("Email does not exists"));
        //Todo implementation
        return UserDTOView.builder()
                .email(user.getEmail())
                .build();
    }
}
