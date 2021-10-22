package com.mindblank.doctor;

import com.mindblank.entities.Doctor;

public class DoctorController {
    private Doctor doc;

    public DoctorController(Doctor doc) {
        this.doc = doc;
    }

    // TODO: update BCE #23; add prescription will contact DoctorController instead of Prescription
    public boolean validatePatient(String patientIC) {
        return doc.getPatientIC(patientIC);
    }
}
