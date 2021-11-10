package com.mindblank.doctor.controllers;

import com.mindblank.entities.Doctor;

public class DoctorViewProfileController extends DoctorController{
    public DoctorViewProfileController(Doctor doc) {
        super(doc);
    }

    public Doctor fetchDoctorInfo(String NRIC) {
        doc.setDoctorInfoFromDB(NRIC);
        return doc;
    }
}

