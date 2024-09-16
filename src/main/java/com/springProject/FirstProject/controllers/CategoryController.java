package com.springProject.FirstProject.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import com.springProject.FirstProject.payloads.ApiResponse;
import com.springProject.FirstProject.payloads.CategoryDto;
import com.springProject.FirstProject.service.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/")
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto){
        CategoryDto categoryDto2 =  categoryService.createCategory(categoryDto);
        return new ResponseEntity<>(categoryDto2,HttpStatus.CREATED);
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto,@PathVariable Integer categoryId){
      CategoryDto categoryDto2 =  categoryService.updateCategory(categoryDto, categoryId);
      return new ResponseEntity<>(categoryDto2,HttpStatus.OK);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<?> deleteCategory(@PathVariable Integer categoryId){
        categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>(new ApiResponse("Category deleted SuccessFully",true),HttpStatus.OK);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> getSingleCategory(@PathVariable Integer categoryId){
       CategoryDto categoryDto = categoryService.getSingleCategory(categoryId);
       return new ResponseEntity<>(categoryDto,HttpStatus.OK);
    } 
    
    @GetMapping("/all")
    public ResponseEntity<List<CategoryDto>> getAllCategory(){
        List<CategoryDto> categorys = categoryService.getAllCategory();
        return new ResponseEntity<List<CategoryDto>>(categorys,HttpStatus.OK);
    }

}
