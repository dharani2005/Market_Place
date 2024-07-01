package org.example.market_place.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String email;
    @Column
    private String name;
    @Column
    private String password;

    @OneToMany(mappedBy = "user")
    private List<Product> product = new ArrayList<>();

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }


    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;

    }

    public void addProduct(Product[] products) {
        if (Objects.requireNonNull(products).length == 0)
            throw new IllegalArgumentException("products is empty");
        for (Product product : products) {
            this.product.add(product);
            if (product.getUser() != null) {
                product.setUser(this);

            }

        }
    }
        public void removeProduct(Product... products) {
            if (Objects.requireNonNull(products).length == 0)
                throw new IllegalArgumentException("products is empty");
            for (Product product : products) {
                if (this.product.remove(product) && product.getUser() == this) {
                    product.setUser(null);
                }

            }
        }

    }

