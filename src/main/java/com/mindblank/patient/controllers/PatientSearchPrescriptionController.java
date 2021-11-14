package com.mindblank.patient.controllers;

import com.mindblank.entities.Medication;
import com.mindblank.entities.Patient;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class PatientSearchPrescriptionController extends PatientController {
    public PatientSearchPrescriptionController(Patient pat) {
        super(pat);
    }

    // returns arraylist of medication from prescription token
    public ArrayList<Medication> fetchUserMedication(String token){
        return pat.viewMedicationsInPrescription(token);
    }

    // returns patient object from prescription token
    public Patient fetchPatientInfoInPrescription(String token){
        return pat.viewPatientData(token);
    }

    // returns date from prescription token
    public String fetchPrescriptionDate(String token) {
        return pat.getPrescriptionDate(token);
    }
}
