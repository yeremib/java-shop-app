package com.enigma.enigma_shop.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "m_product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price", nullable = false, columnDefinition = "BIGINT CHECK(price >= 0)")
    private Long price;

    @Column(name = "stock", nullable = false, columnDefinition = "INT CHECK(stock >= 0)")
    private Integer stock;
}
