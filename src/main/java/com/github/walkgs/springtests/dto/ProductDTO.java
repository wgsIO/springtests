package com.github.walkgs.springtests.dto;

import com.github.walkgs.springtests.entities.Category;
import com.github.walkgs.springtests.entities.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
public class ProductDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 8303933815361275510L;

    private long id;
    private String name;
    private String description;
    private double price;
    private String imgUrl;
    private Instant date;

    private List<CategoryDTO> categories = new ArrayList<>();

    public ProductDTO(long id, String name, String description, double price, String imgUrl, Instant date) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imgUrl = imgUrl;
        this.date = date;
    }

    public ProductDTO(Product entity){
        this(entity.getId(), entity.getName(), entity.getDescription(), entity.getPrice(), entity.getImgUrl(), entity.getDate());
    }

    public ProductDTO(Product entity, Set<Category> categories){
        this(entity);
        this.categories.addAll(categories.stream().map(CategoryDTO::new).toList());
    }

}
