package com.github.walkgs.springtests.dto;

import com.github.walkgs.springtests.entities.Category;
import com.github.walkgs.springtests.utils.Applicable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO implements Serializable, Applicable<CategoryDTO> {

    @Serial
    private static final long serialVersionUID = 1L;

    private long id;
    private String name;
    private Instant createdAt;

    public CategoryDTO(Category entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.createdAt = entity.getCreatedAt();
    }

}
