package com.springProject.FirstProject.service;

import com.springProject.FirstProject.payloads.CategoryDto;
import java.util.*;

public interface CategoryService {

  //create category
  CategoryDto  createCategory(CategoryDto categoryDto);

  //update
  CategoryDto updateCategory(CategoryDto categoryDto,Integer categoryId);

  //delete
  void deleteCategory(Integer categoryId);

  //getSingle Category
  CategoryDto getSingleCategory(Integer categoryId);

  //getAll Category
  List<CategoryDto> getAllCategory();
    
} 