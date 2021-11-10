package com.mindblank.pharmacist.controllers;

import com.mindblank.entities.Medication;
import com.mindblank.entities.Patient;
import com.mindblank.entities.Pharmacist;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class PharmacistSearchPrescriptionController extends PharmacistController{
    public PharmacistSearchPrescriptionController(Pharmacist pharm) {
        super(pharm);
    }

    public ArrayList<Medication> fetchUserMedication(String token) {
        return pharm.getMedication(token);
    }

    public Patient fetchPatientInfoInPrescription(String token){
        Patient patient = pharm.viewPatientData(token);
        return patient;
    }

    public String fetchPrescriptionDate(String token) {
        return pharm.getPrescriptionDate(token);
    }

    //For updating collectedStatus in DB
    public boolean updatePrescriptionStatus(String token){
        return pharm.updateStatus(token);
    }
}
