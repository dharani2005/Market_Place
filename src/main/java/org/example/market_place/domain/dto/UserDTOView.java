package org.example.market_place.domain.dto;

import lombok.*;
import org.example.market_place.domain.entity.Product;

import java.util.List;

@Getter
 @Setter
 @AllArgsConstructor
 @NoArgsConstructor

 @Builder
 public class UserDTOView {
     private String email;
    private String username;

}
