package com.mindblank.doctor.controllers;

import com.mindblank.entities.Doctor;

public class DoctorController {
    protected Doctor doc;

    public DoctorController(Doctor doc) {
        this.doc = doc;
    }

    // TODO: update BCE #23; add prescription will contact DoctorController instead of Prescription
    public boolean validatePatient(String patientIC) {
        return doc.getPatientIC(patientIC);
    }
}
