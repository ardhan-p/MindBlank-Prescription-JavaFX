package com.mindblank.patient.controllers;

import com.mindblank.entities.Patient;

public class PatientController {
    protected Patient pat;

    public PatientController(Patient pat)
    {
        this.pat=pat;
    }

    public boolean validatePatient(String patientIC) {
        return pat.getPatientIC(patientIC);
    }

    public boolean validateToken(String tokenNo)
    {
    return pat.getToken(tokenNo);
    }

    public Patient fetchPatientInfo(String NRIC) {
        return pat.setPatientInfoFromDB(NRIC);
    }

}
