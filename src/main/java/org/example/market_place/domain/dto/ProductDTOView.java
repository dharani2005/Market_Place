package org.example.market_place.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.market_place.domain.entity.User;

import java.time.LocalDate;

@Getter
@Setter
@Builder

public class ProductDTOView {
    private Long id;
    private String type;
    private Long price;
    private LocalDate expired;
    private User user;
}