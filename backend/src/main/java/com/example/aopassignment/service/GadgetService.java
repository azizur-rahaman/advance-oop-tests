package com.example.aopassignment.service;

import com.example.aopassignment.model.Gadget;
import com.example.aopassignment.repository.GadgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GadgetService {

    private final GadgetRepository gadgetRepository;

    @Autowired
    public GadgetService(GadgetRepository gadgetRepository) {
        this.gadgetRepository = gadgetRepository;
    }

    public List<Gadget> getAllGadgets() {
        return gadgetRepository.findAll();
    }

    public Optional<Gadget> getGadgetById(Long id) {
        return gadgetRepository.findById(id);
    }

    public Gadget createGadget(Gadget gadget) {
        return gadgetRepository.save(gadget);
    }

    public Gadget updateGadget(Long id, Gadget gadgetDetails) {
        return gadgetRepository.findById(id).map(gadget -> {
            gadget.setName(gadgetDetails.getName());
            gadget.setBrand(gadgetDetails.getBrand());
            gadget.setType(gadgetDetails.getType());
            gadget.setPrice(gadgetDetails.getPrice());
            gadget.setStatus(gadgetDetails.getStatus());
            return gadgetRepository.save(gadget);
        }).orElseThrow(() -> new RuntimeException("Gadget not found with id " + id));
    }

    public Gadget patchGadget(Long id, Gadget gadgetDetails) {
        return gadgetRepository.findById(id).map(gadget -> {
            if (gadgetDetails.getName() != null)
                gadget.setName(gadgetDetails.getName());
            if (gadgetDetails.getBrand() != null)
                gadget.setBrand(gadgetDetails.getBrand());
            if (gadgetDetails.getType() != null)
                gadget.setType(gadgetDetails.getType());
            if (gadgetDetails.getPrice() != null)
                gadget.setPrice(gadgetDetails.getPrice());
            if (gadgetDetails.getStatus() != null)
                gadget.setStatus(gadgetDetails.getStatus());
            return gadgetRepository.save(gadget);
        }).orElseThrow(() -> new RuntimeException("Gadget not found with id " + id));
    }

    public void deleteGadget(Long id) {
        gadgetRepository.deleteById(id);
    }
}
