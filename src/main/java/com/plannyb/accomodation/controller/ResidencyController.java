package com.plannyb.accomodation.controller;//package com.planny.realestate.controller;
//
//import com.planny.realestate.entity.Residency;
//import com.planny.realestate.service.ResidencyService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/residencies")
//public class ResidencyController {
//
//    private final ResidencyService residencyService;
//
//    @Autowired
//    public ResidencyController(ResidencyService residencyService) {
//        this.residencyService = residencyService;
//    }
//
//    @PostMapping("/create")
//    public ResponseEntity<Object> createResidency(@RequestBody Residency residency) {
//        Residency createdResidency = residencyService.createResidency(residency);
//        return ResponseEntity.ok(createdResidency);
//    }
//
//    @PostMapping("/saveAll")
//    public ResponseEntity<Object> createALlResidencies(@RequestBody List<Residency> residency) {
//        List<Residency> createdResidency = residencyService.saveAllResidencies(residency);
//        return ResponseEntity.ok(createdResidency);
//    }
//
//    @GetMapping("/all")
//    public ResponseEntity<List<Residency>> getAllResidencies() {
//        List<Residency> residencies = residencyService.getAllResidencies();
//        return ResponseEntity.ok(residencies);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Residency> getResidency(@PathVariable Long id) {
//        Residency residency = residencyService.getResidency(id);
//        return ResponseEntity.ok(residency);
//    }
//}
//
