package com.mindblank.pharmacist.controllers;

import com.mindblank.entities.Pharmacist;

public class PharmacistController {
    protected Pharmacist pharm;

    public PharmacistController(Pharmacist pharm){
        this.pharm = pharm;
    }

    public boolean validateToken(String token) {
        return pharm.getToken(token);
    }
}
