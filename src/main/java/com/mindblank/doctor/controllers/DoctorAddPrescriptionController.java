package com.mindblank.doctor.controllers;

import com.mindblank.entities.Doctor;
import com.mindblank.entities.Medication;

import java.util.ArrayList;
import java.util.Random;

public class DoctorAddPrescriptionController extends DoctorController {
    public DoctorAddPrescriptionController(Doctor doc) {
        super(doc);
    }

    // TODO: add this to BCE diagrams
    // randomly generates token and checks from database via doctor entity
    public String generateToken() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int stringLength = 8;
        Random random = new Random();
        String token = "";
        boolean valid = false;
        while (!valid) {
            token = random.ints(leftLimit, rightLimit + 1)
                    .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                    .limit(stringLength)
                    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                    .toString();
            if (doc.validateToken(token)) {
                valid = true;
            }
        }
        return token;
    }

    public boolean addPrescription(String patientIC, String date, ArrayList<Medication> medList) {
        return doc.addPrescription(patientIC, generateToken(), date, medList);
    }
}