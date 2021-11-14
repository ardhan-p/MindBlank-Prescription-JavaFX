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

    // returns an arraylist of prescriptions from the specified patient
    public ArrayList<Prescription> fetchUserPrescriptions(String patientIC) {
        return doc.viewPatientPrescriptions(patientIC);
    }

    // returns an arraylist of medication from the specified prescription
    public ArrayList<Medication> fetchSelectedMedicationInPrescription(String tokenString) {
        return doc.viewMedicationsInPatientPrescription(tokenString);
    }

    // returns patient object associated from prescription token
    public Patient fetchPatientInfoInPrescription(String tokenString) {
        Patient patient = doc.viewPatientData(tokenString);
        return patient;
    }

    // returns date of prescription
    public String fetchPrescriptionDate(String tokenString) {
        return doc.getPrescriptionDate(tokenString);
    }
}
