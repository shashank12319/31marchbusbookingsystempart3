



package com.wittybrains.busbookingsystem.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.wittybrains.busbookingsystem.dto.BookingDTO;
import com.wittybrains.busbookingsystem.exception.BookingNotFoundException;
import com.wittybrains.busbookingsystem.exception.TravelScheduleNotFoundException;
import com.wittybrains.busbookingsystem.exception.UserNotFoundException;
import com.wittybrains.busbookingsystem.service.BookingService;


@RestController
@RequestMapping(value = "/bookings")
public class BookingController {

	private final BookingService bookingService;
	private static final Logger LOGGER = LoggerFactory.getLogger(BookingController.class);

	public BookingController(BookingService bookingService) {
		this.bookingService = bookingService;
	}
	
	@PostMapping
	public ResponseEntity<Object> createBooking(@RequestBody BookingDTO bookingDTO) {
	    LOGGER.info("Received request to create a booking");

	    try {
	        BookingDTO createdBooking = bookingService.createBooking(bookingDTO);
	        LOGGER.info("Created booking");
	
	        return ResponseEntity.ok(createdBooking);
	    } catch (UserNotFoundException e) {
	        LOGGER.error("User not found for id: {}", bookingDTO.getUser().getId(), e);
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found for id: " + bookingDTO.getUser().getId());
	    } catch (TravelScheduleNotFoundException e) {
	        LOGGER.error("Travel schedule not found for id: {}", bookingDTO.getSchedule().getId(), e);
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Travel schedule not found for id: " + bookingDTO.getSchedule().getId());
	    }
	}
	
	@GetMapping("/{id}")
    public ResponseEntity<Object> getBooking(@PathVariable("id") Long id) {
        LOGGER.info("Received request to get booking with id: {}", id);

        try {
            BookingDTO booking = bookingService.getBookingById(id);
            LOGGER.info("Found booking with id: {}", id);

            return ResponseEntity.ok(booking);
        } catch (Exception e) {
            LOGGER.error("Error getting booking with id: {}", id, e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Booking not found for id: " + id);
        }
    }


    
	@PutMapping("/{id}")
    public ResponseEntity<Object> updateBooking(@PathVariable("id") Long id, @RequestBody BookingDTO bookingDTO) {
        LOGGER.info("Received request to update booking with id: {}", id);

        try {
            BookingDTO updatedBooking = bookingService.updateBooking(id, bookingDTO);
            LOGGER.info("Updated booking with id: {}", id);

            return ResponseEntity.ok(updatedBooking);
        } catch (BookingNotFoundException e) {
            LOGGER.error("Booking not found for id: {}", id, e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Booking not found for id: " + id);
        } catch (UserNotFoundException e) {
            LOGGER.error("User not found for id: {}", bookingDTO.getUser().getId(), e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found for id: " + bookingDTO.getUser().getId());
        } catch (TravelScheduleNotFoundException e) {
            LOGGER.error("Travel schedule not found for id: {}", bookingDTO.getSchedule().getId(), e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Travel schedule not found for id: " + bookingDTO.getSchedule().getId());
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteBooking(@PathVariable("id") Long id) {
        LOGGER.info("Received request to delete booking with id: {}", id);

        try {
            bookingService.deleteBooking(id);
            LOGGER.info("Deleted booking with id: {}", id);

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            LOGGER.error("Error deleting booking with id: {}", id, e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Booking not found for id: " + id);
        }
    }
    @GetMapping
    public ResponseEntity<Object> getAllBookings() {
        LOGGER.info("Received request to get all bookings");

        List<BookingDTO> bookings = bookingService.getAllBookings();

        if (bookings.isEmpty()) {
            LOGGER.info("No bookings found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No bookings found");
        }

        LOGGER.info("Found {} bookings", bookings.size());
        return ResponseEntity.ok(bookings);
    }
    
}










