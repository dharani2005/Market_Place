package org.example.market_place.domain.dto;

import lombok.*;

import java.util.List;

@Getter
 @Setter
 @AllArgsConstructor
 @NoArgsConstructor

 @Builder
 public class UserDTOView {
     private String email;
     private List<ProductDTOForm> products;
}
