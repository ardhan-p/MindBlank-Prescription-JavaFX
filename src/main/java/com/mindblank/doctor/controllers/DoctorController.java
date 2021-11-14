package com.mindblank.doctor.controllers;

import com.mindblank.entities.Doctor;

public class DoctorController {
    protected Doctor doc;

    public DoctorController(Doctor doc) {
        this.doc = doc;
    }

    // returns true if patient's nric is valid and is in database
    public boolean validatePatient(String patientIC) {
        return doc.getPatientIC(patientIC);
    }
}
