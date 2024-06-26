package org.example.market_place.domain.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

@Builder
public class UserDTOForm {
    private String email;
    private String password;

}
