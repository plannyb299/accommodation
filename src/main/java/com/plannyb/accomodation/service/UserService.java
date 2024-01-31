package com.plannyb.accomodation.service;//package com.planny.realestate.service;
//import com.planny.realestate.entity.Booking;
//import com.planny.realestate.entity.User;
//import com.planny.realestate.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.Date;
//import java.util.List;
//
//@Service
//public class UserService {
//
//    private final UserRepository userRepository;
//
//    @Autowired
//    public UserService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    public User createUser(User user) {
//        return userRepository.save(user);
//    }
//
//    public void bookVisit(String email, String id, Date date) {
//        User user = userRepository.findByEmail(email)
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        Booking booking = new Booking();
//        booking.setId(id);
//        booking.setDate(date);
//
//        user.getBookedVisits().add(booking);
//        userRepository.save(user);
//    }
//
//    public List<Booking> getAllBookings(String email) {
//        User user = userRepository.findByEmail(email)
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        return user.getBookedVisits();
//    }
//
//    public void cancelBooking(String email, String id) {
//        User user = userRepository.findByEmail(email)
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        user.getBookedVisits().removeIf(visit -> visit.getId().equals(id));
//        userRepository.save(user);
//    }
//
//    public void addToFavorites(String email, String rid) {
//        User user = userRepository.findByEmail(email)
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        List<String> favResidenciesID = user.getFavResidenciesID();
//
//        if (favResidenciesID.contains(rid)) {
//            favResidenciesID.remove(rid);
//        } else {
//            favResidenciesID.add(rid);
//        }
//
//        userRepository.save(user);
//    }
//
//    public List<String> getAllFavorites(String email) {
//        User user = userRepository.findByEmail(email)
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        return user.getFavResidenciesID();
//    }
//}
//
