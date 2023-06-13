package com.Rent.repository;

import com.Rent.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    //  Optional<Product> findAllById(int userId);


    // List<Product> findAllById(int userId);

    List<Product> findByUser_Id(int id);

    List<Product> findAll();

    List<Product> findByAvailableTrue();

    Optional<Product> findById(int id);

    List<Product> findByPnameContainingIgnoreCase(String name);

    List<Product> findByPnameIgnoreCaseAndAvailableTrue(String productName);

    List<Product> findBycategoryCategoryId(int id);

    List<Product> findBycategoryCategoryIdAndAvailableTrue(int id);

}

