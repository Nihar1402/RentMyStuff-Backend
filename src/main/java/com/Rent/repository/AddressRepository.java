package com.Rent.repository;

import com.Rent.models.Address;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Integer> {
    public Address findByUserId(int userId);
    }

