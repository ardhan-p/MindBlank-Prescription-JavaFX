package com.mindblank.doctor.controllers;

import com.mindblank.entities.Doctor;
import com.mindblank.entities.Medication;
import com.mindblank.entities.Patient;
import com.mindblank.entities.Prescription;
import javafx.collections.ObservableList;

public class DoctorViewPrescriptionController extends DoctorController {
    public DoctorViewPrescriptionController(Doctor doc) {
        super(doc);
    }

    public void fetchUserPrescriptions(String patientIC, ObservableList<Prescription> presObservableList) {
        doc.viewAllPrescriptions(patientIC, presObservableList);
    }

    public void fetchSelectedMedicationInPrescription(String tokenString, ObservableList<Medication> medicationObservableList) {
        doc.viewMedicationsInPrescription(tokenString, medicationObservableList);
    }

    public Patient fetchPatientInfoInPrescription(String tokenString) {
        Patient patient = doc.viewPatientData(tokenString);
        return patient;
    }

    public String fetchPrescriptionDate(String tokenString) {
        return doc.getPrescriptionDate(tokenString);
    }
}
