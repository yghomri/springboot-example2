package com.example.demo;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.example.demo.entities.Patient;
import com.example.demo.repositories.PatientRepository;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    @Autowired
    private PatientRepository patientRepository;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        patientRepository.save(new Patient(null, "Freeman", new Date(), 100, false));
        patientRepository.save(new Patient(null, "Deniro", new Date(), 200, false));
        patientRepository.save(new Patient(null, "Rocky", new Date(), 300, true));
        patientRepository.save(new Patient(null, "Batman", new Date(), 400, true));
        patientRepository.save(new Patient(null, "superman", new Date(), 500, true));

        System.out.println("*********1*********");
        patientRepository.findAll().forEach(p -> {
            System.out.println(p.toString());
        });
        System.out.println("*********2*********");
        Patient patient = patientRepository.findById(3L).get();
        System.out.println(patient.toString());
        System.out.println("*********3*********");
        Page<Patient> patients = patientRepository.findByNameContains("a", PageRequest.of(0, 2));
        patients.forEach(p -> {
            System.out.println(p.toString());
        });
        System.out.println("*********4*********");
        List<Patient> patients2 = patientRepository.findBySick(true);
        patients2.forEach(p -> {
            System.out.println(p.toString());
        });
        System.out.println("*********5*********");
        List<Patient> patients3 = patientRepository.findByNameContainsAndSick("F", false);
        patients3.forEach(p -> {
            System.out.println(p.toString());
        });
        System.out.println("********6**********");
        Page<Patient> pagePatients = patientRepository.findAll(PageRequest.of(1, 3));
        List<Patient> listPatients = pagePatients.getContent();
        listPatients.forEach(p -> {
            System.out.println(p.toString());
        });
        System.out.println("*********7*********");
        patientRepository.deleteById(2L);
        patientRepository.findAll().forEach(p -> {
            System.out.println(p.toString());
        });
    }

}
