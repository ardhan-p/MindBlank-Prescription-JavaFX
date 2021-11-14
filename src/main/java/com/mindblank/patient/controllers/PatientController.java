package com.mindblank.patient.controllers;

import com.mindblank.entities.Patient;

public class PatientController {
    protected Patient pat;

    public PatientController(Patient pat)
    {
        this.pat=pat;
    }

    // returns true if patient's nric is valid and in database
    public boolean validatePatient(String patientIC) {
        return pat.getPatientIC(patientIC);
    }

    // returns true if token is valid and in database
    public boolean validateToken(String tokenNo)
    {
    return pat.getToken(tokenNo);
    }

    // returns patient object from nric
    public Patient fetchPatientInfo(String NRIC) {
        return pat.setPatientInfoFromDB(NRIC);
    }

}
