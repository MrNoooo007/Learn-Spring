package com.example.learnspring.service;

import com.example.learnspring.entity.Category;
import com.example.learnspring.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    public void save(Category category) {
        categoryRepository.save(category);
    }

    public Category get(Integer id) {
        return categoryRepository.findById(id).get();
    }

    public void delete(Integer id) {
        categoryRepository.deleteById(id);
    }
}
