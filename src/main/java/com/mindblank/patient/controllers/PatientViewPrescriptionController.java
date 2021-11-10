package com.mindblank.patient.controllers;

import com.mindblank.entities.Medication;
import com.mindblank.entities.Patient;
import com.mindblank.entities.Prescription;

import java.util.ArrayList;

public class PatientViewPrescriptionController extends PatientController {

    public PatientViewPrescriptionController(Patient pat) {
        super(pat);
    }

    public ArrayList<Prescription> fetchUserPrescriptions(String patientIC) {
        return pat.viewAllPastPrescriptions(patientIC);
    }

    public ArrayList<Medication> fetchSelectedMedicationInPrescription(String tokenString) {
        return pat.viewMedicationsInPrescription(tokenString);
    }

    public Patient fetchPatientInfoInPrescription(String tokenString) {
        Patient patient = pat.viewPatientData(tokenString);
        return patient;
    }

    public String fetchPrescriptionDate(String tokenString) {
        return pat.getPrescriptionDate(tokenString);
    }

}
