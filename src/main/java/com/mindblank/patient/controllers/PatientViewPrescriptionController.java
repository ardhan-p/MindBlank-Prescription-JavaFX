package com.mindblank.patient.controllers;

import com.mindblank.entities.Medication;
import com.mindblank.entities.Patient;
import com.mindblank.entities.Prescription;
import javafx.collections.ObservableList;

public class PatientViewPrescriptionController extends PatientController {

    public PatientViewPrescriptionController(Patient pat)
    {
        super(pat);
    }
    public void fetchUserPrescriptions(String patientIC, ObservableList<Prescription> presObservableList) {
        pat.viewAllPrescriptions(patientIC, presObservableList);
    }

    public void fetchSelectedMedicationInPrescription(String tokenString, ObservableList<Medication> medicationObservableList) {
        pat.viewMedicationsInPrescription(tokenString, medicationObservableList);
    }

    public Patient fetchPatientInfoInPrescription(String tokenString) {
        Patient patient = pat.viewPatientData(tokenString);
        return patient;
    }

    public String fetchPrescriptionDate(String tokenString) {
        return pat.getPrescriptionDate(tokenString);
    }

}
