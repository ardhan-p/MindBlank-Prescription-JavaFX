package com.mindblank.pharmacist.controllers;

import com.mindblank.entities.Medication;
import com.mindblank.entities.Patient;
import com.mindblank.entities.Pharmacist;
import javafx.collections.ObservableList;

public class PharmacistSearchPrescriptionController extends PharmacistController{
    public PharmacistSearchPrescriptionController(Pharmacist pharm) {
        super(pharm);
    }

    public void fetchUserMedication(String token, ObservableList<Medication> medicationObservableList){
        pharm.getMedication(token, medicationObservableList);
    }

    public Patient fetchPatientInfoInPrescription(String token){
        Patient patient = pharm.viewPatientData(token);
        return patient;
    }

    public String fetchPrescriptionDate(String token) {
        return pharm.getPrescriptionDate(token);
    }

    //For updating collectedStatus in DB
    public void updatePrescriptionStatus(String token){
       pharm.updateStatus(token);
    }

    //To return collectedStatus value in DB
    public int updateStatusBoole(String token){
        return pharm.updateStatusBool(token);
    }


}
