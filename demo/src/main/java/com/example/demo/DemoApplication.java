package com.example.demo;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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

        System.out.println("******************");
        patientRepository.findAll().forEach(p -> {
            System.out.println(p.toString());
        });
        System.out.println("******************");
        Patient patient = patientRepository.findById(3L).get();
        System.out.println(patient.toString());
        System.out.println("******************");
        List<Patient> patients = patientRepository.findByNameContains("De");
        patients.forEach(p -> {
            System.out.println(p.toString());
        });
        System.out.println("******************");
        List<Patient> patients2 = patientRepository.findBySick(true);
        patients2.forEach(p -> {
            System.out.println(p.toString());
        });
        System.out.println("******************");
        List<Patient> patients3 = patientRepository.findByNameContainsAndSick("F", false);
        patients3.forEach(p -> {
            System.out.println(p.toString());
        });

        patientRepository.deleteById(2L);
        System.out.println("******************");
        patientRepository.findAll().forEach(p -> {
            System.out.println(p.toString());
        });
    }

}
