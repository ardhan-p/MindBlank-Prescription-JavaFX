package com.mindblank.patient.controllers;

import com.mindblank.entities.Medication;
import com.mindblank.entities.Patient;
import javafx.collections.ObservableList;

public class PatientSearchPrescriptionController extends PatientController
{
    public PatientSearchPrescriptionController(Patient pat) {
        super(pat);
    }

    public void fetchUserMedication(String token, ObservableList<Medication> medicationObservableList){
        pat.getMedication(token, medicationObservableList);
    }

    public Patient fetchPatientInfoInPrescription(String token){
        Patient patient = pat.viewPatientData(token);
        return patient;
    }

    public String fetchPrescriptionDate(String token) {
        return pat.getPrescriptionDate(token);
    }
}
