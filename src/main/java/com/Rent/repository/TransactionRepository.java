package com.Rent.repository;
import java.util.List;
import com.Rent.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {
    public List<Transaction>  findByOrderId(int orderId);
}
