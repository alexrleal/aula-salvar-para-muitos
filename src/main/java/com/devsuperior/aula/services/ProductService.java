package com.devsuperior.aula.services;

import com.devsuperior.aula.dto.CategoryDTO;
import com.devsuperior.aula.dto.ProductDTO;
import com.devsuperior.aula.entities.Category;
import com.devsuperior.aula.entities.Product;
import com.devsuperior.aula.repositories.CategoryRepository;
import com.devsuperior.aula.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional
    public ProductDTO insert(ProductDTO dto) {
        Product entityProduct = new Product();
        entityProduct.setName(dto.getName());
        entityProduct.setPrice(dto.getPrice());
        for (CategoryDTO c : dto.getCategories()) {
            // Category entityCategory = new Category(); -- instancinando category manualmente
            // entityCategory.setId(c.getId());
            Category entityCategory = categoryRepository.getOne(c.getId());
            entityProduct.getCategories().add(entityCategory);
        }
        entityProduct = productRepository.save(entityProduct);
        return new ProductDTO(entityProduct);
    }

}
