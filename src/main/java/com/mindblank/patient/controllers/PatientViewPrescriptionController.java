package com.mindblank.patient.controllers;

import com.mindblank.entities.Medication;
import com.mindblank.entities.Patient;
import com.mindblank.entities.Prescription;

import java.util.ArrayList;

public class PatientViewPrescriptionController extends PatientController {
    public PatientViewPrescriptionController(Patient pat) {
        super(pat);
    }

    // returns arraylist of prescriptions from patient's nric
    public ArrayList<Prescription> fetchUserPrescriptions(String patientIC) {
        return pat.viewAllPastPrescriptions(patientIC);
    }

    // returns arraylist of medication from associated prescription
    public ArrayList<Medication> fetchSelectedMedicationInPrescription(String tokenString) {
        return pat.viewMedicationsInPrescription(tokenString);
    }

    // returns patient object from associated prescription
    public Patient fetchPatientInfoInPrescription(String tokenString) {
        Patient patient = pat.viewPatientData(tokenString);
        return patient;
    }

    // returns date from prescription
    public String fetchPrescriptionDate(String tokenString) {
        return pat.getPrescriptionDate(tokenString);
    }

}
