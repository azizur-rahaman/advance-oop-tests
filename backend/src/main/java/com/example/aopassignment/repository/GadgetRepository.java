package com.example.aopassignment.repository;

import com.example.aopassignment.model.Gadget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GadgetRepository extends JpaRepository<Gadget, Long> {
}
