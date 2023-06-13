package com.Rent.repository;

import com.Rent.models.Booking;
import com.Rent.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Integer> {



    @Service
    public interface ProductService {
        Product getProductById(int id);
    }
    List<Booking> findByUserId(int userId);
    List<Booking> findByOwnerId(int id);
    @Query("SELECT COUNT(*) > 0 FROM Booking WHERE product_id = :productId AND startDate = :startDate AND endDate = :endDate AND renter_id= :renterId")
    boolean checkIfBookingExists(@Param("productId") int productId, @Param("startDate")  Date startDate, @Param("endDate") Date endDate,@Param("renterId") int renter_Id);
    // List<Booking> findByUserId(int id);

    List<Booking> findByProductId(int productId);
   //
    Booking findByOrderTableId(int aid);
  //  List<Booking> findByRenterName(String name);

  //  List<Booking> findByProductId(int productId);
}
