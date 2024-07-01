package org.example.market_place.converter;

import org.example.market_place.domain.dto.ProductDTOForm;
import org.example.market_place.domain.dto.ProductDTOView;
import org.example.market_place.domain.entity.Product;

import java.util.List;

public interface ProductConverter {

 ProductDTOView toProductView(Product entity);
 Product toProductEntity(ProductDTOView dto);
 List<ProductDTOView> toProductViews(List<Product> entities);
 public Product toProductEntity(ProductDTOForm dto);
 public List<Product> toProductEntities(List<ProductDTOForm> dtos);
}
