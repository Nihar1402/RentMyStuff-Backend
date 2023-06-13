package com.Rent.repository;

import com.Rent.models.Order_Table;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order_Table,Integer> {
    Order_Table findByBookingId(int id);
    Order_Table findById(int id);
  //  List< Order_Table> findByBookingId(int id);
}
