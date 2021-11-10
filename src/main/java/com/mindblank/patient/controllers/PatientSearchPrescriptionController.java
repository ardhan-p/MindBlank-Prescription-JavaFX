package com.mindblank.patient.controllers;

import com.mindblank.entities.Medication;
import com.mindblank.entities.Patient;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class PatientSearchPrescriptionController extends PatientController
{
    public PatientSearchPrescriptionController(Patient pat) {
        super(pat);
    }

    public ArrayList<Medication> fetchUserMedication(String token){
        return pat.viewMedicationsInPrescription(token);
    }

    public Patient fetchPatientInfoInPrescription(String token){
        return pat.viewPatientData(token);
    }

    public String fetchPrescriptionDate(String token) {
        return pat.getPrescriptionDate(token);
    }
}
