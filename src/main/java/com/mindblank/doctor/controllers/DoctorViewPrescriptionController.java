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
        ArrayList<Prescription> presArrayList = new ArrayList<Prescription>();
        presArrayList = doc.viewAllPrescriptions(patientIC);

        return presArrayList;
    }

    public ArrayList<Medication> fetchSelectedMedicationInPrescription(String tokenString) {
        ArrayList<Medication> medArrayList = new ArrayList<Medication>();
        medArrayList = doc.viewMedicationsInPrescription(tokenString);

        return medArrayList;
    }

    public Patient fetchPatientInfoInPrescription(String tokenString) {
        Patient patient = doc.viewPatientData(tokenString);
        return patient;
    }

    public String fetchPrescriptionDate(String tokenString) {
        return doc.getPrescriptionDate(tokenString);
    }
}
