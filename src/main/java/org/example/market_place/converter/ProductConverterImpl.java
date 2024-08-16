package org.example.market_place.converter;

import org.example.market_place.domain.dto.ProductDTOForm;
import org.example.market_place.domain.dto.ProductDTOView;
import org.example.market_place.domain.entity.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductConverterImpl implements ProductConverter {
    @Override
    public List<ProductDTOView> toProductViews(List<Product> entities) {
        List<ProductDTOView> productDTOViews = new ArrayList<>();
        if (entities == null || entities.isEmpty()) throw new IllegalArgumentException("entities is null or empty");
        for (Product product : entities) {
            productDTOViews.add(toProductView(product));
        }
        return productDTOViews;
    }

    @Override
    public ProductDTOView toProductView(Product entity) {
        return ProductDTOView.builder()
                .id(entity.getId())
                .price(entity.getPrice())
                .type(entity.getType())
                .creationDate(entity.getCreationDate())
                .expired(entity.getExpired())
                .build();
        //return new ProductDTOView(entity.getId(), entity.getType(), entity.getPrice(), entity.getExpired());
    }

    @Override
    public Product toProductEntity(ProductDTOView dto) {
        return Product.builder()
                .id(dto.getId())
                .price(dto.getPrice())
                .type(dto.getType())
                .expired(dto.getExpired())
                .build();
        //return new Product(dto.getId(), dto.getType(), dto.getPrice(), dto.getExpired());
    }

    public Product toProductEntity(ProductDTOForm dto) {

        return Product.builder()
                .id(dto.getId())
                .price(dto.getPrice())
                .type(dto.getType())
                .expired(dto.getExpired())
                .build();

    }
    public List<Product> toProductEntities(List<ProductDTOForm> dtos) {
        List<Product> products = new ArrayList<>();
        if(dtos == null || dtos.isEmpty()) throw new IllegalArgumentException("dtos is null or empty");
        for(ProductDTOForm dto : dtos)
        {
          products.add(toProductEntity(dto));
        }
        return products;
    }
}
