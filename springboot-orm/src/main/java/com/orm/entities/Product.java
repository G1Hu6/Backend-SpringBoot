package com.orm.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(
        name = "products",
        uniqueConstraints = {
                //@UniqueConstraint(name = "unique_sku", columnNames = {"sku"}),
                @UniqueConstraint(name = "unique_name_price", columnNames = {"name","price"})
        },
        indexes = {
                @Index(name = "my_sku", columnList = "sku")
        }
)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = true, length = 30)
    private String sku;

    private String name;

    private BigInteger price;

    private Integer quantity;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @CreationTimestamp
    private LocalDateTime updatedAt;
}
