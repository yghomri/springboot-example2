package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entities.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    public List<Patient> findByNameContains(String name);
    public List<Patient> findBySick(boolean sick);
    public List<Patient> findByNameContainsAndSick(String name, boolean sick);
}
