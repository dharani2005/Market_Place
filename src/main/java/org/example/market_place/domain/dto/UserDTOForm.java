package org.example.market_place.domain.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

@Builder
public class UserDTOForm {
    private String email;
    private String password;
    private List<ProductDTOForm> products;

}
