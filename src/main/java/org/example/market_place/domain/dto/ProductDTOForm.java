package org.example.market_place.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.example.market_place.domain.entity.User;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder

public class ProductDTOForm {

    @NotBlank(message = "Type is required..")
    @Size(min = 10, max = 100)
    private String type;

    private Long price;

    private LocalDate creationDate;

    private LocalDate expired;

    @NotNull(message = "User details are required.")
    private UserDTOForm user;

}
