package com.mindblank.doctor.controllers;

import com.mindblank.entities.Doctor;
import com.mindblank.entities.Prescription;
import javafx.collections.ObservableList;

public class DoctorViewPrescriptionController extends DoctorController {
    public DoctorViewPrescriptionController(Doctor doc) {
        super(doc);
    }

    public void fetchUserPrescriptions(String patientIC, ObservableList<Prescription> presObservableList) {
        doc.viewPrescriptions(patientIC, presObservableList);
    }

    public void fetchSelectedPrescription(String tokenString) {

    }

}
