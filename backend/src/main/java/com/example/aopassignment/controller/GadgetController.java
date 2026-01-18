package com.example.aopassignment.controller;

import com.example.aopassignment.model.Gadget;
import com.example.aopassignment.service.GadgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/gadgets")
@CrossOrigin(origins = "http://localhost:3000") // Allow Frontend access
public class GadgetController {

    private final GadgetService gadgetService;

    @Autowired
    public GadgetController(GadgetService gadgetService) {
        this.gadgetService = gadgetService;
    }

    @GetMapping
    public List<Gadget> getAllGadgets() {
        return gadgetService.getAllGadgets();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Gadget> getGadgetById(@PathVariable Long id) {
        return gadgetService.getGadgetById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Gadget createGadget(@RequestBody Gadget gadget) {
        return gadgetService.createGadget(gadget);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Gadget> updateGadget(@PathVariable Long id, @RequestBody Gadget gadgetDetails) {
        try {
            return ResponseEntity.ok(gadgetService.updateGadget(id, gadgetDetails));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Gadget> patchGadget(@PathVariable Long id, @RequestBody Gadget gadgetDetails) {
        try {
            return ResponseEntity.ok(gadgetService.patchGadget(id, gadgetDetails));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGadget(@PathVariable Long id) {
        gadgetService.deleteGadget(id);
        return ResponseEntity.ok().build();
    }
}
