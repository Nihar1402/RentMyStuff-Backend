package com.Rent.repository;

import com.Rent.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository  extends JpaRepository<Category,Integer> {

    Category findByCategoryTitle(String name);
}
