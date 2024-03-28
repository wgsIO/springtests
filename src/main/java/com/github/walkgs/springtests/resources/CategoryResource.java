package com.github.walkgs.springtests.resources;

import com.github.walkgs.springtests.entities.Category;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/categories")
public class CategoryResource {

    @GetMapping
    public ResponseEntity<List<Category>> findAll() {
        final List<Category> categories = new ArrayList<>();
        categories.add(new Category(1, "Books"));
        categories.add(new Category(2, "Electronics"));
        return ResponseEntity.ok(categories);
    }

}
