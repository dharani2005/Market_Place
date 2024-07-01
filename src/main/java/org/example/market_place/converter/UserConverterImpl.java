package org.example.market_place.converter;

import org.example.market_place.domain.dto.ProductDTOView;
import org.example.market_place.domain.dto.UserDTOView;
import org.example.market_place.domain.entity.Product;
import org.example.market_place.domain.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserConverterImpl implements UserConverter {
    @Autowired
    ProductConverter productConverter;
    @Override
    public UserDTOView toUserDto(User entity) {
       /* return UserDTOView.builder()
                .email(entity.getEmail())
                .products(entity.) */
        List<ProductDTOView> products = new ArrayList<>();
        for(Product p : entity.getProduct())
        {
         products.add(productConverter.toProductView(p));
        }
        return new UserDTOView(entity.getEmail(), products);
    }

    @Override
    public User toUserEntity(UserDTOView dto) {
        List<Product> products = new ArrayList<>();
        for(ProductDTOView p : dto.getProducts())
        {
            products.add(productConverter.toProductEntity(p));
        }
        return User.builder()
                .email(dto.getEmail())
                .product(products).build();
    }
}
