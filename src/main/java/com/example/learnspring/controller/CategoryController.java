package com.example.learnspring.controller;

import com.example.learnspring.entity.Category;
import com.example.learnspring.response.ResponseObject;
import com.example.learnspring.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("api/v1/categories")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping
    public ResponseEntity<ResponseObject> index() {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("OK", "show data", categoryService.getAll())
        );
    }

    @PostMapping
    public ResponseEntity<ResponseObject> store(@RequestBody @Valid Category category) {
        categoryService.save(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ResponseObject("OK", "inserted !!!", category)
        );
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<ResponseObject> update(@RequestBody Category category, @PathVariable Integer categoryId) {
        try {
            Category categoryExisted = categoryService.get(categoryId);

            categoryExisted.setName(category.getName());

            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("OK", "updated", categoryExisted)
            );
        }
        catch (NoSuchElementException noSuchElementException) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("NOT FOUND", "Category not found", null)
            );
        }
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<ResponseObject> delete(@PathVariable Integer categoryId) {
        try {
            categoryService.delete(categoryId);

            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(
              new ResponseObject("No content", "Deteled category ID : " + categoryId, null)
            );
        }
        catch (NoSuchElementException noSuchElementException) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("NOT FOUND", "Category not found", null)
            );
        }
    }

}