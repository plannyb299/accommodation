package com.plannyb.accomodation.controller;//package com.planny.realestate.controller;
//import com.planny.realestate.dto.request.BookingRequest;
//import com.planny.realestate.dto.request.UserRequest;
//import com.planny.realestate.entity.Booking;
//import com.planny.realestate.entity.User;
//import com.planny.realestate.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Date;
//import java.util.List;
//
//@RestController
//@RequestMapping("/users")
//public class UserController {
//
//    private final UserService userService;
//
//    @Autowired
//    public UserController(UserService userService) {
//        this.userService = userService;
//    }
//
//    @PostMapping("/create")
//    public ResponseEntity<Object> createUser(@RequestBody User user) {
//        User createdUser = userService.createUser(user);
//        return ResponseEntity.ok(createdUser);
//    }
//
//    @PostMapping("/{id}/book-visit")
//    public ResponseEntity<String> bookVisit(@PathVariable String id, @RequestBody BookingRequest request) {
//        userService.bookVisit(request.getEmail(), id, request.getDate());
//        return ResponseEntity.ok("Visit booked successfully");
//    }
//
//    @GetMapping("/{email}/bookings")
//    public ResponseEntity<List<Booking>> getAllBookings(@PathVariable String email) {
//        List<Booking> bookings = userService.getAllBookings(email);
//        return ResponseEntity.ok(bookings);
//    }
//
//    @DeleteMapping("/{email}/cancel-booking/{id}")
//    public ResponseEntity<String> cancelBooking(@PathVariable String email, @PathVariable String id) {
//        userService.cancelBooking(email, id);
//        return ResponseEntity.ok("Booking cancelled successfully");
//    }
//
//    @PostMapping("/{rid}/add-to-favorites")
//    public ResponseEntity<String> addToFavorites(@PathVariable String rid, @RequestBody UserRequest request) {
//        userService.addToFavorites(request.getEmail(), rid);
//        return ResponseEntity.ok("Residency added to favorites successfully");
//    }
//
//    @GetMapping("/{email}/favorites")
//    public ResponseEntity<List<String>> getAllFavorites(@PathVariable String email) {
//        List<String> favorites = userService.getAllFavorites(email);
//        return ResponseEntity.ok(favorites);
//    }
//
//    // Additional request/response classes may be needed, such as BookingRequest, UserRequest, etc.
//}
