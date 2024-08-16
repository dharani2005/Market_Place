package org.example.market_place.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder


@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String type;

    @Column
    private Long price;

    @Column
    private LocalDate creationDate;

    @Column
    private LocalDate expired;

  @ManyToOne
    @JoinColumn(name ="email")
    private User user;

    public Product(String type, Long price, LocalDate expired) {
        this.type = type;
        this.price = price;
        this.expired = expired;
    }

    public Product(Long id, String type, Long price, LocalDate expired) {
        this.id = id;
        this.type = type;
        this.price = price;
        this.expired = expired;
    }
    public void prePersist() {
        creationDate = LocalDate.now();
        expired = creationDate.plusDays(30);
    }
}
