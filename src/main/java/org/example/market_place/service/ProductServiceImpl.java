package org.example.market_place.service;

import org.example.market_place.domain.dto.ProductDTOForm;
import org.example.market_place.domain.dto.ProductDTOView;
import org.example.market_place.domain.dto.UserDTOForm;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service

public class ProductServiceImpl implements ProductService {
    @Override
    public ProductDTOView create(ProductDTOForm dtoForm) {
        //Todo implementation
        return null;
    }

    @Override
    public List<ProductDTOView> findProductsByPriceGreaterThanEqual(Long price) {
        //Todo implementation
        return List.of();
    }

    @Override
    public void deleteProductByExpired(LocalDate expired) {
        //Todo implementation
    }

    @Override
    public List<ProductDTOView> findByUserEmailAndPassword(UserDTOForm dtoForm) {
        //Todo implementation
        return List.of();
    }
}
