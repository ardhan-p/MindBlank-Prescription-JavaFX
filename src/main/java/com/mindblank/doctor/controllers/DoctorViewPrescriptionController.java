package com.mindblank.doctor.controllers;

import com.mindblank.entities.Doctor;
import com.mindblank.entities.Medication;
import com.mindblank.entities.Patient;
import com.mindblank.entities.Prescription;

import java.util.ArrayList;

public class DoctorViewPrescriptionController extends DoctorController {
    public DoctorViewPrescriptionController(Doctor doc) {
        super(doc);
    }

    public ArrayList<Prescription> fetchUserPrescriptions(String patientIC) {
        return doc.viewAllPrescriptions(patientIC);
    }

    public ArrayList<Medication> fetchSelectedMedicationInPrescription(String tokenString) {
        return doc.viewMedicationsInPrescription(tokenString);
    }

    public Patient fetchPatientInfoInPrescription(String tokenString) {
        Patient patient = doc.viewPatientData(tokenString);
        return patient;
    }

    public String fetchPrescriptionDate(String tokenString) {
        return doc.getPrescriptionDate(tokenString);
    }
}
