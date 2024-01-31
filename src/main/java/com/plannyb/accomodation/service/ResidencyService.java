//package com.plannyb.accomodation.service;
//import com.planny.realestate.entity.Residency;
//import com.planny.realestate.repository.ResidencyRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class ResidencyService {
//
//    private final ResidencyRepository residencyRepository;
//
//    @Autowired
//    public ResidencyService(ResidencyRepository residencyRepository) {
//        this.residencyRepository = residencyRepository;
//    }
//
//    public Residency createResidency(Residency residency) {
//        return residencyRepository.save(residency);
//    }
//
//    public List<Residency> saveAllResidencies(List<Residency> residency) {
//        return residencyRepository.saveAll(residency);
//    }
//
//    public List<Residency> getAllResidencies() {
//        return residencyRepository.findAll();
//    }
//
//    public Residency getResidency(Long id) {
//        return residencyRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Residency not found"));
//    }
//}
