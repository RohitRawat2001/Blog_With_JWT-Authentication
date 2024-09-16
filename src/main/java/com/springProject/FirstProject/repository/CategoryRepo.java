package com.springProject.FirstProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springProject.FirstProject.Entity.Category;

public interface CategoryRepo extends JpaRepository<Category,Integer>{

}
