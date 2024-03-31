package com.github.walkgs.springtests.services;

import com.github.walkgs.springtests.dto.CategoryDTO;
import com.github.walkgs.springtests.entities.Category;
import com.github.walkgs.springtests.repositories.CategoryRepository;
import com.github.walkgs.springtests.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CategoryService {

    private static final String CATEGORY_NOT_FOUND = "Category not found";

    @Autowired
    private CategoryRepository repository;

    @Transactional(readOnly = true)
    public Page<CategoryDTO> findAll(PageRequest pageRequest) {
        return repository.findAll(pageRequest).map(CategoryDTO::new);
        //return repository.findAll().stream().map(CategoryDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public CategoryDTO findById(long id) {
        final Optional<Category> entity = repository.findById(id);
        return new CategoryDTO(entity.orElseThrow(() ->  new ResourceNotFoundException(CATEGORY_NOT_FOUND)));
    }

    @Transactional
    public CategoryDTO insert(CategoryDTO dto) {
        final Category category = repository.save(new Category().apply(it -> it.setName(dto.getName())));
        return new CategoryDTO(category);
    }

    @Transactional
    public CategoryDTO update(long id, CategoryDTO dto) {
        final Category entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(CATEGORY_NOT_FOUND));
        return new CategoryDTO(repository.save(entity.apply(it -> it.setName(dto.getName()))));
    }

    public void delete(long id) {
        final Category entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(CATEGORY_NOT_FOUND));
        repository.delete(entity);
    }

}
