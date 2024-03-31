package com.github.walkgs.springtests.services;

import com.github.walkgs.springtests.dto.CategoryDTO;
import com.github.walkgs.springtests.dto.ProductDTO;
import com.github.walkgs.springtests.entities.Category;
import com.github.walkgs.springtests.entities.Product;
import com.github.walkgs.springtests.repositories.CategoryRepository;
import com.github.walkgs.springtests.repositories.ProductRepository;
import com.github.walkgs.springtests.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@Service
public class ProductService {

    private static final String PRODUCT_NOT_FOUND = "Product not found";
    private static final String CATEGORY_NOT_FOUND = "Category not found";

    @Autowired
    private ProductRepository repository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public Page<ProductDTO> findAll(PageRequest pageRequest) {
        return repository.findAll(pageRequest).map(ProductDTO::new);
        //return repository.findAll().stream().map(ProductDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public ProductDTO findById(long id) {
        final Optional<Product> entity = repository.findById(id);
        final Product product = entity.orElseThrow(() -> new ResourceNotFoundException(PRODUCT_NOT_FOUND));
        return new ProductDTO(product, product.getCategories());
    }

    @Transactional
    public ProductDTO insert(ProductDTO dto) {
        final Product Product = repository.save(new Product().apply(it -> copyProductDtoToEntity(dto, it)));
        return new ProductDTO(Product);
    }

    @Transactional
    public ProductDTO update(long id, ProductDTO dto) {
        final Product entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(PRODUCT_NOT_FOUND));
        return new ProductDTO(repository.save(entity.apply(it -> copyProductDtoToEntity(dto, it))));
    }

    public void delete(long id) {
        final Product entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(PRODUCT_NOT_FOUND));
        repository.delete(entity);
    }

    private void copyProductDtoToEntity(ProductDTO dto, Product entity) {
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setPrice(dto.getPrice());
        entity.setImgUrl(dto.getImgUrl());
        entity.setDate(dto.getDate());
        final Set<Category> categories = entity.getCategories();
        categories.clear();
        for (final CategoryDTO categoryDTO : dto.getCategories()) {
            final Optional<Category> category = categoryRepository.findById(categoryDTO.getId());
            categories.add(category.orElseThrow(() -> new ResourceNotFoundException(CATEGORY_NOT_FOUND)));
        }
    }

}
