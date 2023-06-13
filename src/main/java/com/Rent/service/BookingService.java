package com.Rent.service;

import com.Rent.models.Booking;
import com.Rent.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
    public class BookingService {
        @Autowired
        private BookingRepository bookingRepository;

        public Booking getBookingById(int id) {
            Optional<Booking> optionalBooking = bookingRepository.findById(id);
            return optionalBooking.orElse(null);
        }
    }

