package com.github.walkgs.springtests.entities;

import com.github.walkgs.springtests.utils.Applicable;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "products")
@NoArgsConstructor
public class Product implements Serializable, Applicable<Product> {

    @Serial
    private static final long serialVersionUID = -543572516856906366L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @Column(columnDefinition = "TEXT")
    private String description;
    private double price;
    private String imgUrl;

    @CreatedDate
    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant date; //Moment created

    @Setter(AccessLevel.NONE)
    @ManyToMany
    @JoinTable(name = "products_category", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories = new HashSet<>(); // Set is conjunct and  not repeat

    public Product(Long id, String name, String description, Double price, String imgUrl, Instant date) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imgUrl = imgUrl;
        this.date = date;
    }

}
